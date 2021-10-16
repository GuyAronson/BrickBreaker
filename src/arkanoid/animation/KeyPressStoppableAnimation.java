package arkanoid.animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class is a decorator-class that will wrap an existing animation,
 * and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {

    //Members:
    private final Animation animation;
    private final KeyboardSensor keyboard;
    private final String pressedKey;
    private boolean stop;
    private boolean isAlreadyPressed;


    /**
     * Constructor for the keyPress Animations.
     * @param sensor - the keyboard sensor
     * @param key - the key to insert
     * @param anim - animation to run.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation anim) {
        this.animation = anim;
        this.keyboard = sensor;
        this.pressedKey = key;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.isAlreadyPressed && this.keyboard.isPressed(this.pressedKey)) {
            this.isAlreadyPressed = true;
            this.stop = true;
        } else {
            this.isAlreadyPressed = false;
        }
        this.animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}