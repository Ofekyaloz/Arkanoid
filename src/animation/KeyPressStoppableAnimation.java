package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Sets the parameters - Animation, key sensor and the key that stops the animation.
     * @param sensor - the keyboard sensor.
     * @param key - the key that stop the animation.
     * @param animation - the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = true;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.sensor.isPressed(this.key) && !isAlreadyPressed) {
            this.stop = false;
            return;
        }
        if (this.sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        this.animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return !this.stop;
    }
}