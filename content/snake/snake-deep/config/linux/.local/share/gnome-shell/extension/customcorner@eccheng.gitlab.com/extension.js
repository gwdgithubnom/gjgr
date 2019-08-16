const Main = imports.ui.main;

const St      = imports.gi.St;
const Meta    = imports.gi.Meta;
const GLib    = imports.gi.GLib;
const Gio     = imports.gi.Gio;
const Clutter = imports.gi.Clutter;

const Extension = imports.misc.extensionUtils.getCurrentExtension();
const initSettings = Extension.imports.prefs.initSettings;

const Layout = imports.ui.layout;

// Work out the positions on screen for the four hot corner areas.
const monitor = Main.layoutManager.primaryMonitor;
const CORNER_POS = {
    'top-left-action': {x: 0, y: 0},
    'top-right-action': {x: monitor.width-10, y: 0},
    'bottom-left-action': {x: 0, y: monitor.height-10},
    'bottom-right-action': {x: monitor.width-10, y: monitor.height-10}
};

let corners = {};
let _sig_enter_id = {}, _sig_leave_id = {};
let _oldShouldToggle;

let settings;
let errlog;
let actor;
let wrapLeft, wrapRight;

function init() {}

function _activateCorner(corner) {
    switch (settings.get_string(corner)) {
        case 'Show Overview':
        showOverview(false);
        break;
        case 'Show Apps Grid':
        showOverview(true);
        break;
        case 'Show Desktop':
        showDesktop();
        break;
        case 'Open Application...':
        openApp(settings.get_string(corner.replace(/action$/g, 'app')));
        break;
        case 'Run Command...':
        runCommand(settings.get_string(corner.replace(/action$/g, 'command')));
    }
}

function showOverview(showApps) {
    let appsButton = Main.overview.viewSelector._showAppsButton;
    if (Main.overview.visible && appsButton.checked === showApps) {
        // Already in desired state, so hide overview
        Main.overview.hide();
    } else {
        Main.overview.show();
        appsButton.checked = showApps;
    }
}

let _toRestore = {};
let _toActivate = {};

function showDesktop() {
    const wspace = global.screen.get_active_workspace();
    const windows = wspace.list_windows();

    // These are the window types which should be minimized in ordder to show
    // the desktop. The window manager treats certain special entities, such as
    // the desktop UI, as windows, and we want to leave those out.
    // 
    // TODO: Is this list correct? I'm not sure what to do about things such as
    //       tooltips and combobox popups. We would ideally be able to hide them,
    //       but calling .minimize() makes it difficult for the user to restore
    //       them manually. If the user manually unminimizes some windows and
    //       shows the desktop again, then there will be no way of restoring
    //       these elements except through the console.
    const WINDOW_TYPES = [
        Meta.WindowType.NORMAL,
        Meta.WindowType.DIALOG,
        Meta.WindowType.MODAL_DIALOG,
        Meta.WindowType.SPLASHSCREEN
    ];

    // Find all of the suitable windows which we should minimize.
    let toMinimize = [];
    for (let i = 0; i < windows.length; ++i) {
        if (!windows[i].minimized) {
            const wtype = windows[i].get_window_type();
            if (WINDOW_TYPES.indexOf(wtype) > -1)
                toMinimize.push(windows[i]);
        }
    }

    if (toMinimize.length > 0) {
        // Save visible windows.
        _toRestore[wspace] = toMinimize;
        // Find and save which window is focused:
        let active = global.display.focus_window;
        // Ignore focused window if it is of the wrong type, eg. it is the Desktop.
        if (toMinimize.indexOf(active) > -1)
            _toActivate[wspace] = active;

        for (let i = 0; i < toMinimize.length; ++i)
            toMinimize[i].minimize();
    } else if (_toRestore[wspace]) { // There are windows to restore for this space.
        for (let i = 0; i < _toRestore[wspace].length; ++i)
            // Make sure window still exists.
            if (windows.indexOf(_toRestore[wspace][i]) > -1)
                _toRestore[wspace][i].unminimize();

        if (_toActivate[wspace] && windows.indexOf(_toActivate[wspace]) > -1)
            _toActivate[wspace].activate(global.get_current_time());

        _toRestore[wspace] = [];
        _toActivate[wspace] = null;
    }
}

function openApp(app) {
    // Make sure app exists.
    let appFile = Gio.File.new_for_path(app);
    if (app && appFile.query_exists(new Gio.Cancellable())) {
        // Open app in correct way.
        if (!app.endsWith('.desktop')) runCommand(app);
        else {
            // There is no standardized, reliable utility for opening .desktop
            // files. This means we need to do some imperfect hacking to extract
            // the actual command which the .desktop file executes.
            let lines = appFile.load_contents(new Gio.Cancellable())[1];
            lines = lines.toString().split('\n');
            for (let i = 0; i < lines.length; ++i) {
                if (lines[i].startsWith('Exec=')) {
                    let command = lines[i].slice(5);
                    command = command.replace(/%./g, '').trim();
                    runCommand(command);
                    break;
                }
            }
        }
    }
}

function runCommand(command) {
    // We don't give the command full shell capabilities, but we do basic tilde
    // expansion. (This doesn't support the less-common ~user/ syntax.)
    let cmdString = command.replace(/~/g, GLib.get_home_dir()).trim();
    try {
        // Parse string into command array.
        let cmdArray = GLib.shell_parse_argv(cmdString)[1];
        if (!cmdArray.length) return;
        // Spawn the process.
        let proc = GLib.spawn_async_with_pipes(null, cmdArray, null,
                                               GLib.SpawnFlags.SEARCH_PATH, null);
        // Redirect the process' stderr to our log file.
        // TODO: OutputStream.splice() seems to crash the shell in 3.18?
        //       For now, we just disable logging on 3.18.
        let shellVer = getShellVersion();
        if (shellVer && shellVer < [3, 17, 0]) {
            let errRaw = new Gio.UnixInputStream({fd: proc[4], close_fd: true});
            let err = new Gio.DataInputStream({base_stream: errRaw});
            // TODO: Enum GOutputStreamSpliceFlags doesn't seem to be visible in JS.
            //       I'm assuming 0 is the value for G_OUTPUT_STREAM_SPLICE_NONE.
            errlog.splice(err, 0, null);
            errlog.write('\n', null);
            errlog.flush(null);
        }
    } catch (e) {
        errlog.write('\n'+e.toString(), null);
        errlog.flush(null);
    }
}

function getShellVersion() {
    // Query shell version.
    let proc = GLib.spawn_async_with_pipes(null, ['gnome-shell', '--version'], null,
                                           GLib.SpawnFlags.SEARCH_PATH, null);
    let outputRaw = new Gio.UnixInputStream({fd: proc[3], close_fd: true});
    let output = new Gio.DataInputStream({base_stream: outputRaw});
    // Extract the three important version numbers from the output.
    let version = output.read_line(null).toString().match(/^GNOME Shell ([0-9\.]+)/);
    if (version)
        return version[1].split('.').map(function(x) {return +x;});
}

function enable() {
    // Retrieve settings schema.
    settings = initSettings();

    // Prepare log file for recording errors in command execution.
    let logpath = Extension.dir.get_child('command_errors.log').get_path();
    let logfile = Gio.File.new_for_path(logpath);
    // TODO: Enum GFileCreateFlags doesn't seem to be visible in JS.
    //       I'm assuming 0 is the value for G_FILE_CREATE_NONE.
    errlog = logfile.append_to(0, null);

    // Create artificial hot corners
    for (let c in CORNER_POS) {
        corners[c] = new St.Bin({
            style_class: 'corner',
            reactive: true,
            can_focus: true,
            x_fill: true,
            y_fill: false,
            track_hover: true
        });
    }

    // Activate custom hot corners and place them on the screen.
    for (let c in corners) {
        Main.uiGroup.add_actor(corners[c]);
        corners[c].set_position(CORNER_POS[c].x, CORNER_POS[c].y);
    }

    // Create chrome actor for wrapping artificial hot corners on screen bottom
    // (otherwise their enter-events get intercepted by the windows below them).
    actor = new St.Widget({
        clip_to_allocation: true,
        layout_manager: new Clutter.BinLayout()
    });
    actor.add_constraint(new Layout.MonitorConstraint({primary: true, work_area: true}));
    Main.layoutManager.addChrome(actor, {affectsInputRegion: false});
    // When the actor has a clip, it somehow breaks drag-and-drop funcionality in
    // the overview. We remove the actor's clip to avoid this.
    actor.set_clip(0, 0, 0, 0);

    // Create 'wrappers' for bottom hot corners.
    wrapLeft = new St.Widget({x_expand: true, y_expand: true,
                              x_align: Clutter.ActorAlign.START,
                              y_align: Clutter.ActorAlign.END});
    wrapRight = new St.Widget({x_expand: true, y_expand: true,
                               x_align: Clutter.ActorAlign.END,
                               y_align: Clutter.ActorAlign.END});
    wrapLeft.set_size(10, 10);
    wrapRight.set_size(10, 10);

    // Deploy wrappers.
    actor.add_actor(wrapLeft);
    actor.add_actor(wrapRight);
    Main.layoutManager.trackChrome(wrapLeft, {affectsInputRegion: true});
    Main.layoutManager.trackChrome(wrapRight, {affectsInputRegion: true});

    if (settings.get_string('top-left-action') != 'GNOME Default') {
        // Remove the "Activities" button from the top bar.
        // Unfortunately, the new left-most button in the top bar will still appear
        // "flat" and will not curve downwards at the edge like the Activities
        // button does. This could be difficult to fix, considering we don't even
        // know what the new left-most button might be. 
        Main.panel.statusArea.activities.actor.hide();

        // Disable old hot corner by replacing the shouldToggle... function.
        // This function is called by the GNOME Shell to decide if it will ignore
        // hot corner activations, and normally returns false when the overview
        // animation is already in progress.
        // See: js/ui/overview.js in the gnome-shell source code.
        _oldShouldToggle = Main.overview.shouldToggleByCornerOrButton;
        Main.overview.shouldToggleByCornerOrButton = function() {return false;}
    }

    // The enter-event and leave-event signals are sent whenever the mouse
    // pointer enters or exits an artificial hot corner. The enter-event signal
    // seems to sometimes get sent multiple times, inappropriately. We use a
    // simple flag to ignore the repeated ones.
    for (let c in corners) {
        let cSave = c;
        corners[c].cantrigger = true;
        _sig_enter_id[c] = corners[c].connect("enter-event", function() {
            if (corners[cSave].cantrigger) {
                _activateCorner(cSave);
                corners[cSave].cantrigger = false;
            }
        });
        _sig_leave_id[c] = corners[c].connect("leave-event", function() {
            corners[cSave].cantrigger = true;
        });
    }
}

function disable() {
    // Remove corner wrapper widgets.
    Main.layoutManager.removeChrome(actor);
    Main.layoutManager.untrackChrome(wrapLeft);
    Main.layoutManager.untrackChrome(wrapRight);

    // Restore the "Activities" button in the top bar, if hidden.
    Main.panel.statusArea.activities.actor.show();

    if (_oldShouldToggle) {
        // Restore GNOME hot corner behavior.
        Main.overview.shouldToggleByCornerOrButton = _oldShouldToggle;
        _oldShouldToggle = null;
    }

    // Disconnect the signals which control our custom hot corners,
    // and remove the corners themselves.
    for (let c in corners) {
        if (_sig_enter_id[c]) {
            corners[c].disconnect(_sig_enter_id[c]);
            _sig_enter_id[c] = null;
        }
        if (_sig_leave_id[c]) {
            corners[c].disconnect(_sig_leave_id[c]);
            _sig_leave_id[c] = null;
        }
        corners[c].destroy();
    }
}

