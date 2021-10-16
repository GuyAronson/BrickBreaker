package arkanoid.observer;

/**
 * A simple class for counting.
 */
public class Counter {

    //Members:
    private int counter;

    /**
     * Default constructor.
     * initializing the counter to zero.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Another counstructor.
     * intializing the counter to the number given.
     * @param amount - the number to initialize the counter.
     */
    public Counter(int amount) {
        this.counter = amount;
    }
    /**
     * add number to current count.
     * @param number - number to add to the counter
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     * @param number - number to decrease from the counter.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Accessor.
     * get current count.
     * @return - the counter.
     */
    public int getValue() {
        return this.counter;
    }
}
