const GObject = imports.gi.GObject;
const Gio     = imports.gi.Gio;
const Gtk     = imports.gi.Gtk;
const Lang    = imports.lang;

const Extension = imports.misc.extensionUtils.getCurrentExtension();

// Allowed Hot Corner actions:
const ACTIONS = {
    '[No Action]': 0,
    'Show Overview': 1,
    'Show Apps Grid': 2,
    'Show Desktop': 3,
    'Open Application...': 4,
    'Run Command...': 5,
    'GNOME Default': 6
};

// Grid positions for various UI elements:

const CORNERS = {
    'top-left-action': {x: 0, y: 2},
    'top-right-action': {x: 2, y: 2},
    'bottom-left-action': {x: 0, y: 9},
    'bottom-right-action': {x: 2, y: 9}
};

const ENTRIES = {
    'top-left-command': {x: 0, y: 3},
    'top-right-command': {x: 2, y: 3},
    'bottom-left-command': {x: 0, y: 8},
    'bottom-right-command': {x: 2, y: 8}
};

const CHOOSERS = {
    'top-left-app': {x: 0, y: 3},
    'top-right-app': {x: 2, y: 3},
    'bottom-left-app': {x: 0, y: 8},
    'bottom-right-app': {x: 2, y: 8}
};

let settings;

function init() {}

function initSettings() {
    let schemaSource = Gio.SettingsSchemaSource.new_from_directory(
        Extension.dir.get_child('settings').get_path(),
        Gio.SettingsSchemaSource.get_default(),
        false
    );
    let schema = schemaSource.lookup('org.gnome.shell.extensions.CustomCorner', true);
    return new Gio.Settings({settings_schema: schema});
}

const CustomCornerSettingsWidget = new GObject.Class({
    Name: 'CustomCorner.Prefs.CustomCornerSettingsWidget',
    GTypeName: 'CustomCornerSettingsWidget',
    Extends: Gtk.Grid,

    _init : function(params) {
        this.parent(params);

        settings = initSettings();

        this.margin = 24;
        this.row_spacing = 10;
        this.column_spacing = 10;
        this.orientation = Gtk.Orientation.VERTICAL;

        let msg = new Gtk.Label({label: '<b>Configure Hot Corner actions:</b>',
                                 use_markup: true});
        this.attach(msg, 1, 0, 1, 1);
        this.attach(new Gtk.Separator(), 0, 1, 3, 1);

        // Add file choosers and text entry fields for customizable actions.
        this._entries = {};
        for (var e in ENTRIES) {
            this._entries[e] = new Gtk.Entry({});

            let eSave = e;
            let that = this;
            this._entries[e].connect('changed', function() {
                settings.set_string(eSave, that._entries[eSave].get_text());
            });
        }

        this._choosers = {};
        for (var c in CHOOSERS) {
            this._choosers[c] = new Gtk.FileChooserButton({
                action: Gtk.FileChooserAction.OPEN
            });

            this._choosers[c].set_current_folder('/usr/share/applications/');

            let cSave = c;
            let that = this;
            this._choosers[c].connect('selection-changed', function() {
                settings.set_string(cSave, that._choosers[cSave].get_filename());
            });
        }

        // Add main action choosers.
        for (var corner in CORNERS) {
            let box = new Gtk.ComboBoxText({});

            for (let action in ACTIONS)
                // The default GNOME Hot Corner behavior only applies to the top left.
                if (action != 'GNOME Default' || corner === 'top-left-action')
                    box.append_text(action);

            let activeIndex = ACTIONS[settings.get_string(corner)];
            if (!activeIndex) activeIndex = 0;
            box.set_active(activeIndex);

            let cornerSave = corner;
            let that = this;
            box.connect('changed', function() {
                let item = box.get_active_text();
                settings.set_string(cornerSave, item);

                that._updateInputs(cornerSave, item);
            });

            this.attach(box, CORNERS[corner].x, CORNERS[corner].y, 1, 1);

            this._updateInputs(corner, box.get_active_text());
        }

        // Retrieve desktop background image:
        let bgschema = new Gio.Settings({schema_id: 'org.gnome.desktop.background'});
        // Strip file:// prefix.
        let bgfile = bgschema.get_string('picture-uri').replace(/^[^:]*:\/\//, '');
        let img = new Gtk.Image({file: bgfile});
        let pb = img.get_pixbuf();
        // Make sure it is an image file.
        // TODO: Parse .xml backgrounds to find current image.
        if (pb)
            img.set_from_pixbuf(pb.scale_simple(320, 180, 1));
        else {
            let failfile = Extension.dir.get_child('failbg.png').get_path();
            img = new Gtk.Image({file: failfile});
        }
        this.attach(img, 1, 2, 1, 8);
    },

    _updateInputs : function(corner, item) {
        let comKey = corner.replace(/action$/, 'command');
        let appKey = corner.replace(/action$/, 'app');
        // Remove exising input widgets.
        let children = this.get_children();
        if (children.indexOf(this._entries[comKey]) > -1) {
            this.remove(this._entries[comKey]);
            this._entries[comKey].hide();
        } else if (children.indexOf(this._choosers[appKey]) > -1) {
            this.remove(this._choosers[appKey]);
            this._choosers[appKey].hide();
        }
        // Add and initialize appropriate input widgets.
        if (item === 'Run Command...') {
            let pos = ENTRIES[comKey];
            this.attach(this._entries[comKey], pos.x, pos.y, 1, 1);
            this._entries[comKey].show();

            let currentVal = settings.get_string(comKey);
            if (currentVal)
                this._entries[comKey].set_text(currentVal);
        } else if (item === 'Open Application...') {
            let pos = CHOOSERS[appKey];
            this.attach(this._choosers[appKey], pos.x, pos.y, 1, 1);
            this._choosers[appKey].show();

            let currentVal = settings.get_string(appKey);
            if (currentVal)
                this._choosers[appKey].set_filename(currentVal);
        }
    }
});

function buildPrefsWidget() {
    let widget = new CustomCornerSettingsWidget();
    widget.show_all();
    return widget;
}

