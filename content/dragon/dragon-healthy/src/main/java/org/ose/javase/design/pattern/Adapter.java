// The adapter implements legacy class's interface
// input: legacy input
// output: advanced output

package org.ose.javase.design.pattern;

import java.sql.Time;

public class Adapter implements LegacyVideoController {
    private AdvancedVideoController advancedVideoController;

    public Adapter(AdvancedVideoController advancedVideoController) {
        this.advancedVideoController = advancedVideoController;
    }

    @Override
    public void startPlayback(long startTimeTicks) {
        advancedVideoController.seek(new Time(startTimeTicks));
        advancedVideoController.play();
    }
}

interface LegacyVideoController {
    public void startPlayback(long startTimeTicks);
}

interface AdvancedVideoController {
    public void seek(Time time);

    public void play();
}
