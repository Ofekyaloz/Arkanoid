package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Creates animation runner and sets a new gui, sleeper.
     */
    public AnimationRunner() {
        this.gui = new GUI("Game", 800, 600);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * Runs the animation.
     * @param animation - the animation that the methods
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * @return this Gui.
     */
    public GUI getGui() {
        return this.gui;
    }
}