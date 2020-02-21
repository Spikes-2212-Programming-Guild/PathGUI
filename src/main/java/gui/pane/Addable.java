package gui.pane;

import path.Waypoint;

/**
 * An object that you can add {@link Waypoint}s to.
 *
 * @author Eran Goldstein
 */
public interface Addable {
    /**
     * Adds a new {@link Waypoint} to this Addable.
     *
     * @param waypoint the {@link Waypoint} to add
     */
    void add(Waypoint waypoint);

    /**
     * Selects the {@link Waypoint} at the supplied coordinates.
     *
     * @param waypoint the waypoint to select
     * @return {@code true} if the {@link Waypoint} was successfully selected, {@code false} otherwise
     */
    boolean select(Waypoint waypoint);
}
