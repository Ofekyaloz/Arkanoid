package sprite;

import biuoop.DrawSurface;
import java.awt.Color;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class LevelName implements Sprite {
    private String str;

    /**
     * Creates sprite that prints the name of the level.
     * @param name - the name of the level.
     */
    public LevelName(String name) {
        this.str = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(555, 17, "Level: " + this.str, 20);
    }

    @Override
    public void timePassed() {

    }
}
