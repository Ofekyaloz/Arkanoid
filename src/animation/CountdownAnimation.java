package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import sprite.SpriteCollection;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class CountdownAnimation implements Animation {
    private AnimationRunner ar;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;

    /**
     * Creates countdown animation that contains the spriteCollection of the game, sets animation runner
     * the number of seconds that the animation will run and the number that start countdown starts with.
     * @param numOfSeconds - the number of second of the animation.
     * @param countFrom - the number that the countdown starts with.
     * @param gameScreen - the sprite collection of the game.
     * @param ar - the animation runner that will run the animation.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, AnimationRunner ar) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.ar = ar;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        GUI gui = this.ar.getGui();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            d = gui.getDrawSurface();
            this.gameScreen.drawAllOn(d);
            String s = String.valueOf(this.countFrom);
            d.drawText(400, 350, s, 100);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            sleeper.sleepFor(1000);
            this.countFrom += -1;
            if (this.shouldStop()) {
                break;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.countFrom == 0;
    }
}
