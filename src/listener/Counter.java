package listener;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class Counter {
    private int counter;

    /**
     * Constructor of the listener.Counter.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * @param number - adds this number to the counter.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * @param number - subtract this number from the counter.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * @return the current count of this counter.
     */
    public int getValue() {
        return this.counter;
    }
}