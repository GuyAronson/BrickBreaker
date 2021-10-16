package arkanoid.observer;

/**
 * The HitNotifier interface indicate that objects send notifications when they are being hit.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl - the Hit listener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - the Hit listener.
     */
    void removeHitListener(HitListener hl);
}
