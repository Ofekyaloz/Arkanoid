package animation;

import biuoop.DrawSurface;
import listener.Counter;
import java.awt.Color;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class LoseAnimation implements Animation {
    private Counter score;

    /**
     * Creates the lose animation and saves score of the game.
     * @param score - counter that contains the score of the game.
     */
    public LoseAnimation(Counter score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(170, 250, "Game Over. Your Score is: " + this.score.getValue(), 30);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
