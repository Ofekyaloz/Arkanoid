package animation;

import biuoop.DrawSurface;
import listener.Counter;
import java.awt.Color;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class WinAnimation implements Animation {
    private Counter score;

    /**
     * Creates win animation and saves the game score.
     * @param score - the counter that contains the score of the game.
     */
    public WinAnimation(Counter score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(170, 250, "You Win! Your Score is: " + this.score.getValue(), 30);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
