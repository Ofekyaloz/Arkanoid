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
public class Level1 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public Color[] ballsColor() {
        Color[] colors = {Color.blue, Color.CYAN, Color.white};
        return colors;
    }

    @Override
    public java.util.List<Velocity> initialBallVelocities() {
        java.util.List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(Velocity.fromAngleAndSpeed(0, 4));
        for (int i = 0; i < this.numberOfBalls() - 1; i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(45 - 90 * (i % 2), 3));
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 85;
    }

    @Override
    public String levelName() {
        return "UnderWater";
    }

    @Override
    public Sprite getBackground() {
        Block background = new Block(new shapes.Point(0, 0), 800, 600);
        background.getCollisionRectangle().setColor(new Color(35, 93, 241));
        return background;
    }

    @Override
    public java.util.List<Block> blocks() {
        java.util.List<Block> blocksList = new ArrayList();
        Block block = new Block(new Point(395, 90), 20, 20);
        block.getCollisionRectangle().setColor(new Color(21, 246, 203));
        blocksList.add(block);
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    @Override
    public java.util.List<Sprite> spriteBackground() {
        List<Sprite> sprites = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                Ball ball = new Ball(new Point(30 + i * 190, 600 - j * 10), 6, Color.white);
                ball.setVelocity(0, -1);
                sprites.add(ball);
            }
        }
        for (int i = 0; i < 20; i++) {
            Block block = new Block(new Point(20 + i * 50, 580), 5, 30);
            block.getCollisionRectangle().setColor(new Color(15, 144, 32));
            sprites.add(block);
        }
        for (int i = 0; i < 4; i++) {
            Block block = new Block(new Point(25 + i * 7, 500), 5, 30);
            block.getCollisionRectangle().setColor(Color.pink);
            sprites.add(block);
        }
        Ball jellyfish = new Ball(new Point(40, 500), 20, Color.pink);
        jellyfish.setVelocity(0, 0);
        sprites.add(jellyfish);
        Ball b1 = new Ball(new Point(32, 495),  4, Color.magenta);
        b1.setVelocity(0, 0);
        sprites.add(b1);
        Ball b2 = new Ball(new Point(45, 492),  4, Color.magenta);
        b2.setVelocity(0, 0);
        sprites.add(b2);
        Ball b3 = new Ball(new Point(45, 505),  4, Color.magenta);
        b3.setVelocity(0, 0);
        sprites.add(b3);
        Color[] colors = {Color.blue, Color.CYAN, Color.white};
        for (int i = 0; i < 3; i++) {
            Ball ball = new Ball(new Point(405, 100),  50 - 10 * i, colors[i % colors.length]);
            ball.setVelocity(0, 0);
            sprites.add(ball);
        }
        return sprites;
    }

    @Override
    public boolean specialBlocks() {
        return false;
    }
}
