package listener;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public interface HitNotifier {

    /**
     * Adds hl as a listener to hit events.
     * @param hl - the listenner.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes hl from the list of listeners to hit events.
     * @param hl - the listener.
     */
    void removeHitListener(HitListener hl);
}