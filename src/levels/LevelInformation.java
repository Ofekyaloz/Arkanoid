package levels;

import collidable.Block;
import shapes.Velocity;
import sprite.Sprite;
import java.awt.Color;
import java.util.List;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public interface LevelInformation {

    /**
     * @return the number of the balls.
     */
    int numberOfBalls();

    /**
     * @return - array of colors for the balls.
     */
    Color[] ballsColor();

    /**
     * @return a list of Velocity for the balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * @return the name of the level.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * @return the blocks of the level.
     */
    List<Block> blocks();

    /**
     * @return the number of the blocks.
     */
    int numberOfBlocksToRemove();

    /**
     * @return additional sprites for the background.
     */
    List<Sprite> spriteBackground();

    /**
     * @return if true, the game includes a block that adds a ball and a block that removes a ball.
     */
    boolean specialBlocks();
}