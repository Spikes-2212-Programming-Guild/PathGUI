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
     * @param x the x coordinate to select at
     * @param y the y coordinate to select at
     * @return `true` if the {@link Waypoint} was successfully selected, `false` otherwise
     */
    boolean select(double x, double y);
}
