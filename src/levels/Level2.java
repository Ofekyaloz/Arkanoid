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
public class Level2 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public Color[] ballsColor() {
        Color[] colors = {Color.RED, Color.orange, Color.yellow, Color.orange, new Color(222, 147, 11)};
        return colors;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocityList.add(Velocity.fromAngleAndSpeed((-10 + i * 5) % 50, 5));
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 3;
    }

    @Override
    public int paddleWidth() {
        return 200;
    }

    @Override
    public String levelName() {
        return "Mine";
    }

    @Override
    public Sprite getBackground() {
        Block background = new Block(new Point(0, 0), 800, 600);
        background.getCollisionRectangle().setColor(new Color(154, 123, 11));
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList();
        Color[] colors = new Color[] {new Color(179, 143, 0), new Color(128, 102, 0),
        new Color(77, 61, 0)};
        for (int j = 0; j < 15; j++) {
            Block block = new Block(new Point(8 + j * 53, 80), 53, 25);
            block.getCollisionRectangle().setColor(colors[j % 3]);
            blocksList.add(block);
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
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(5) + 1;
            Ball gold = new Ball(new Point(30 + i * 150, 60), 5 * rand, Color.ORANGE);
            gold.setVelocity(0, 0);
            sprites.add(gold);
        }
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(5) + 1;
            Ball coal = new Ball(new Point(30 + i * 140, 180 - i * 5), 10 * rand, Color.BLACK);
            coal.setVelocity(0, 0);
            sprites.add(coal);
        }
        for (int i = 0; i < 5; i++) {
            int rand = random.nextInt(5) + 1;
            Ball rock = new Ball(new Point(30 + i * 190, 490 + i * 10), 10 * rand, Color.gray);
            rock.setVelocity(0, 0);
            sprites.add(rock);
        }
        for (int i = 0; i < 5; i++) {
            Ball rock = new Ball(new Point(30 + i * 190, 300), 20 - i * 2, Color.gray);
            rock.setVelocity(0, 0);
            sprites.add(rock);
        }
        Block block = new Block(new Point(50, 550), 80, 40);
        block.getCollisionRectangle().setColor(new Color(85, 76, 4));
        sprites.add(block);
        Ball b1 = new Ball(new Point(60, 585), 10, Color.black);
        b1.setVelocity(0, 0);
        Ball b2 = new Ball(new Point(120, 585), 10, Color.black);
        b2.setVelocity(0, 0);
        sprites.add(b1);
        sprites.add(b2);
        return sprites;
    }

    @Override
    public boolean specialBlocks() {
        return false;
    }
}
