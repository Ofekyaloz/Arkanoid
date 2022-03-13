package animation;

import biuoop.DrawSurface;
import java.awt.Color;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class PauseScreen implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillCircle(400, 250, 150);
        d.setColor(Color.white);
        d.fillCircle(400, 250, 140);
        d.setColor(Color.black);
        d.fillRectangle(350, 160, 25, 170);
        d.fillRectangle(430, 160, 25, 170);
        d.drawText(180,  500, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}