package gui;

import path.Waypoint;

/**
 * An action performed by the user.
 *
 * @author Eran Goldstein
 */
public class Action {
    /**
     * Types of actions the user may perform.
     */
    public enum ActionType {
        ADD, MIRROR, GENERATE
    }

    /**
     * The type of the action.
     */
    private ActionType type;

    /**
     * The destination the action was directed towards.
     */
    private Waypoint to;

    public Action(ActionType type, Waypoint to) {
        this.type = type;
        this.to = to;
    }

    /**
     * Constructor for actions with no destination.
     */
    public Action(ActionType type) {
        this(type, null);
    }

    public ActionType getType() {
        return type;
    }

    public Waypoint getTo() {
        return to;
    }
}
