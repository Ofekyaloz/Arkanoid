package listener;

import biuoop.DrawSurface;
import sprite.Sprite;
import java.awt.Color;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    /**
     * Constructor of listener.ScoreIndicator which contains listener.Counter for the score.
     * @param scoreCounter - the listener.Counter.
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(340, 18, "Score: " + this.scoreCounter.getValue(), 22);
    }

    @Override
    public void timePassed() {

    }
}
