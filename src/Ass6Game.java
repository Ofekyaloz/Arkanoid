import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import game.GameFlow;
import levels.Level3;
import levels.Level2;
import levels.Level4;
import levels.Level1;
import java.util.ArrayList;
import java.util.List;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class Ass6Game {

    /**
     * creates a game, initialize it and runs the game.
     * @param args - none.
     */
    public static void main(String[] args) {
        AnimationRunner ar = new AnimationRunner();
        KeyboardSensor keyboard = ar.getGui().getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(ar, keyboard);
        List levels = new ArrayList();
        if (args.length != 0) {
            for (String str : args) {
                if (str.equals("1")) {
                    levels.add(new Level1());
                } else if (str.equals("2")) {
                    levels.add(new Level2());
                } else if (str.equals("3")) {
                    levels.add(new Level3());
                } else if (str.equals("4")) {
                    levels.add(new Level4());
                }
            }
        }
        if (levels.size() == 0) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        }
        gameFlow.runLevels(levels);
    }
}