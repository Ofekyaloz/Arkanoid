package game;

import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.LoseAnimation;
import animation.WinAnimation;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import listener.Counter;
import java.util.List;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor keyboardSensor;
    private Counter score, lives;


    /**
     * Sets the animation runner and the key sensor and sets new Counter for the score.
     * @param ar - the animation runner - runs the animation.
     * @param ks - the keyboard sensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.ar = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
        this.lives = new Counter();
        this.lives.increase(5);
    }

    /**
     * Runs the game levels from the list.
     * @param levels - a list of levels for the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ar, this.keyboardSensor, this.score, this.lives);
            level.initialize();
            while (this.lives.getValue() > 0 && level.getNumBlocks() > 0) {
                level.run();
                if (level.getNumBalls() == 0) {
                    this.lives.decrease(1);
                    level.reset();
                }
            }
        }
        // runs lose animation
        if (this.lives.getValue() == 0) {
            this.ar.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new LoseAnimation(this.score)));
            this.ar.getGui().close();
            return;
        }

        // runs win animation
        this.ar.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new WinAnimation(this.score)));
        this.ar.getGui().close();
    }
}