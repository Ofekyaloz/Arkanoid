package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import collidable.Block;
import collidable.Collidable;
import levels.LevelInformation;
import listener.BlockRemover;
import listener.Counter;
import listener.BallRemover;
import listener.ScoreTrackingListener;
import listener.ScoreIndicator;
import listener.BallAdder;
import shapes.Ball;
import shapes.Point;
import sprite.LevelName;
import sprite.Paddle;
import sprite.Sprite;
import sprite.SpriteCollection;
import sprite.Lives;
import java.awt.Color;
import java.util.Random;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class GameLevel implements Animation {
    private static final int WIDTH = 800, HEIGHT = 600, RADIUS = 5, ZERO = 0, BORDER_WIDTH = 8;
    private KeyboardSensor keyboardSensor;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private BlockRemover blockRemover;
    private Counter blocksCounter, ballsCounter, scoreCounter, liveCounter;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation level;
    private Paddle paddle;


    /**
     * Constructor of a game - sets a new sprite.SpriteCollection and game.GameEnvironment.
     * @param level - the level information.
     * @param ar - animation runner.
     * @param keyboardSensor - keyboard sensor.
     * @param scoreCounter - score of the game.
     */

    /**
     * Constructor of a game - sets a new sprite.SpriteCollection and game.GameEnvironment.
     * @param level - the level information.
     * @param ar - animation runner.
     * @param keyboardSensor - keyboard sensor.
     * @param scoreCounter - score of the game.
     * @param livesCounter - count the lives.
     */
    public GameLevel(LevelInformation level, AnimationRunner ar, KeyboardSensor keyboardSensor, Counter scoreCounter,
                     Counter livesCounter) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter();
        this.blockRemover = new BlockRemover(this, this.blocksCounter);
        this.ballsCounter = new Counter();
        this.scoreCounter = scoreCounter;
        this.liveCounter = livesCounter;
        this.runner = ar;
        this.level = level;
        this.keyboardSensor = keyboardSensor;
        this.paddle = new Paddle(this.keyboardSensor, this.level.paddleWidth(), this.level.paddleSpeed());
    }

    /**
     * adds a collidable object to the list.
     * @param c - the new collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * removes a collidable object from the list.
     * @param c - the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * adds a sprite.Sprite to the list.
     * @param s - the new sprite.Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * removes a sprite.Sprite from the list.
     * @param s - the sprite.Sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the blocks and shapes.Ball and sprite.Paddle and add them to the game.
     */
    public void initialize() {
        this.addSprite(this.level.getBackground());
        if (this.level.spriteBackground() != null) {
            for (Sprite s: this.level.spriteBackground()) {
                this.addSprite(s);
            }
        }
        Block topBackground = new Block(new Point(ZERO, ZERO), WIDTH, 20);
        topBackground.getCollisionRectangle().setColor(Color.gray);
        this.addSprite(topBackground);
        LevelName levelName = new LevelName(this.level.levelName());
        this.sprites.addSprite(levelName);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter);
        this.sprites.addSprite(scoreIndicator);
        ScoreTrackingListener score = new ScoreTrackingListener(this.scoreCounter);
        this.sprites.addSprite(new Lives(this.liveCounter));

        // creates the blocks.
        int specialAdder = -1, specialRemover = -1;
        if (this.level.specialBlocks()) {
            Random random = new Random();
            specialAdder = random.nextInt(this.level.numberOfBlocksToRemove());
            do {
                specialRemover = random.nextInt(this.level.numberOfBlocksToRemove());
            } while (specialRemover == specialAdder);
        }
        java.util.List<Block> blocksList = this.level.blocks();
        for (int i = 0; i < this.level.numberOfBlocksToRemove(); i++) {
            Block block = blocksList.get(i);
            block.addToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(score);
            if (i == specialAdder) {
                block.addHitListener(new BallAdder(this, this.ballsCounter));
            } else if (i == specialRemover) {
                block.addHitListener(new BallRemover(this, this.ballsCounter));
            }
        }
        this.blocksCounter.increase(this.level.numberOfBlocksToRemove());
        this.setBoarders();
        this.setBalls();
        this.paddle.addToGame(this);
    }

    /**
     * Sets the boarders of the game - top, bottom, left and right.
     */
    private void setBoarders() {
        Block b1 = new Block(new Point(ZERO, 20), WIDTH, BORDER_WIDTH);
        Block b2 = new Block(new Point(ZERO, HEIGHT - BORDER_WIDTH), WIDTH, BORDER_WIDTH);
        Block b3 = new Block(new Point(ZERO, ZERO), BORDER_WIDTH, HEIGHT);
        Block b4 = new Block(new Point(WIDTH - BORDER_WIDTH, ZERO), BORDER_WIDTH, HEIGHT);
        b1.addToGame(this);
        b2.addToGame(this);
        b3.addToGame(this);
        b4.addToGame(this);
        b1.getCollisionRectangle().setColor(Color.DARK_GRAY);
        b2.getCollisionRectangle().setColor(Color.DARK_GRAY);
        b3.getCollisionRectangle().setColor(Color.DARK_GRAY);
        b4.getCollisionRectangle().setColor(Color.DARK_GRAY);
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        b2.addHitListener(ballRemover);
    }

    /**
     * Creates the balls and add them to the game.
     */
    public void setBalls() {
        double ballX = 400, ballY = 560;
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Color color = this.level.ballsColor()[i % this.level.ballsColor().length];
            if (i < this.level.ballsColor().length) {
                color = this.level.ballsColor()[i];
            }
            Ball ball = new Ball(new Point(ballX, ballY), RADIUS, color);
            ball.setVelocity(this.level.initialBallVelocities().get(i));
            ball.addToGame(this);
            ball.setGameEnvironment(this.environment);
        }
        this.ballsCounter.increase(this.level.numberOfBalls());
    }

    /**
     * Resets the paddle the balls of the game.
     */
    public void reset() {
        this.setBalls();
        this.paddle.resetPaddle();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        GUI gui = this.runner.getGui();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            d.setColor(Color.black);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > ZERO) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

            // updates the score and shows the new score.
            if (this.blocksCounter.getValue() == 0) {
                this.scoreCounter.increase(100);
            }

            // end the game if there is no more blocks or balls.
            if (this.blocksCounter.getValue() == 0 || this.ballsCounter.getValue() == 0) {
                this.running = false;
                return;
            }
            if (this.keyboardSensor.isPressed("p")) {
                Animation pause = new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new PauseScreen());
                this.runner.run(pause);
            }
        }
    }

    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(3, 3, this.sprites, this.runner));
        this.runner.run(this);
    }

    /**
     * @return the number of the blocks that remain in the game.
     */
    public int getNumBlocks() {
        return this.blocksCounter.getValue();
    }

    /**
     * @return the number of the balls that remain in the game.
     */
    public int getNumBalls() {
        return this.ballsCounter.getValue();
    }
}