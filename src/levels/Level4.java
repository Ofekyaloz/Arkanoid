package levels;

import collidable.Block;
import shapes.Ball;
import shapes.Point;
import shapes.Velocity;
import sprite.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class Level4 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public Color[] ballsColor() {
        Color[] colors = {Color.white};
        return colors;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocityList.add(Velocity.fromAngleAndSpeed((-15 + i * 5) % 45, 4));
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Space";
    }

    @Override
    public Sprite getBackground() {
        Block background = new Block(new shapes.Point(0, 0), 800, 600);
        background.getCollisionRectangle().setColor(new Color(40, 40, 36));
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList();
        Color[] colors = new Color[] {new Color(80, 75, 75), new Color(161, 155, 155)};
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 10; j++) {
                Block block = new Block(new Point(8 + j * 80, 70 + i * 20), 80, 20);
                block.getCollisionRectangle().setColor(colors[i % 2]);
                blocksList.add(block);
            }
        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    @Override
    public List<Sprite> spriteBackground() {
        List<Sprite> sprites = new ArrayList<>();
        Ball moon = new Ball(100, 300, 60, Color.white);
        moon.setVelocity(0, 0);
        Ball sun = new Ball(600,  100, 90, Color.yellow);
        sun.setVelocity(0, 0);
        sprites.add(sun);
        sprites.add(moon);
        for (int i = 0; i < 800; i += 30) {
            for (int j = 0; j < 700; j += 33) {
                Ball ball = new Ball(i, j, 2, Color.white);
                ball.setVelocity(0, 0);
                sprites.add(ball);
            }
        }
        return sprites;
    }

    @Override
    public boolean specialBlocks() {
        return true;
    }
}
