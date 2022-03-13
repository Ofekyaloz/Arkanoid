package levels;

import collidable.Block;
import shapes.Ball;
import shapes.Point;
import shapes.Velocity;
import sprite.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class Level3 implements LevelInformation {
    private static final int WIDTH = 800, BLOCK_HEIGHT = 20, BLOCK_WIDTH = 50, ROWS = 5, NUM_BLOCKS = 10;

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public Color[] ballsColor() {
        return new Color[] {Color.BLUE, Color.yellow, Color.orange};
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        Velocity v1 = Velocity.fromAngleAndSpeed(45, 5);
        Velocity v2 = Velocity.fromAngleAndSpeed(20, 5);
        Velocity v3 = Velocity.fromAngleAndSpeed(-40, 5);
        velocityList.add(v1);
        velocityList.add(v2);
        velocityList.add(v3);
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 84;
    }

    @Override
    public String levelName() {
        return "SkyLine";
    }

    @Override
    public Sprite getBackground() {
        Block background = new Block(new Point(0, 0), 800, 600);
        background.getCollisionRectangle().setColor(Color.cyan);
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList();
        double x = WIDTH - NUM_BLOCKS * BLOCK_WIDTH;
        for (int i = 0; i < ROWS; i++) {
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color randomColor = new Color(r, g, b);
            for (int j = i; j < NUM_BLOCKS; j++) {
                Block block = new Block(new Point(x + j * BLOCK_WIDTH, x + i * BLOCK_HEIGHT - 100), BLOCK_WIDTH,
                        BLOCK_HEIGHT);
                block.getCollisionRectangle().setColor(randomColor);
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
        Block building = new Block(new Point(100, 445), 100, 150);
        building.getCollisionRectangle().setColor(Color.DARK_GRAY);
        sprites.add(building);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Block block = new Block(new Point(105 + j * 20, 450 + i * 30), 10, 10);
                block.getCollisionRectangle().setColor(Color.white);
                sprites.add(block);
            }
        }

        Block b1 = new Block(new Point(135, 370), 30, 75);
        b1.getCollisionRectangle().setColor(Color.gray);
        sprites.add(b1);
        Block b2 = new Block(new Point(145, 270), 10, 100);
        b2.getCollisionRectangle().setColor(Color.lightGray);
        sprites.add(b2);
        Ball ball1 = new Ball(new Point(150, 265),  10, Color.red);
        ball1.setVelocity(0, 0);
        sprites.add(ball1);
        Ball ball2 = new Ball(new Point(150, 265), 5, Color.orange);
        ball2.setVelocity(0, 0);
        sprites.add(ball2);
        Ball sun = new Ball(new Point(50, 90), 40, Color.yellow);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                Ball ball = new Ball(new Point(300 + i * 25, 90 + j * 25), 20, Color.white);
                ball.setVelocity(0, 0);
                sprites.add(ball);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                Ball ball = new Ball(new Point(500 + i * 25, 90 + j * 25), 20, Color.white);
                ball.setVelocity(0, 0);
                sprites.add(ball);
            }
        }
        sun.setVelocity(0, 0);
        sprites.add(sun);
        return sprites;
    }

    @Override
    public boolean specialBlocks() {
        return true;
    }
}
