package sprite;

import biuoop.DrawSurface;
import listener.Counter;
import java.awt.Color;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class Lives implements Sprite {
    private Counter lives;

    /**
     * Creates the Lives sprite that prints the lives of the game.
     * @param lives - counter of the lives in the game.
     */
    public Lives(Counter lives) {
        this.lives = lives;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(155, 17, "Lives: " + this.lives.getValue(), 20);
    }

    @Override
    public void timePassed() {

    }
}
