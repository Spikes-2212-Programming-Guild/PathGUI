package gui;

import path.Waypoint;

/**
 * An action performed by the user.
 *
 * @author Eran Goldstein
 */
public class Action {
    /**
     * An object that represents no action.
     */
    public static final Action NO_ACTION = new Action(ActionType.ADD, null);

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
    private Waypoint destination;

    public Action(ActionType type, Waypoint destination) {
        this.type = type;
        this.destination = destination;
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

    public Waypoint getDestination() {
        return destination;
    }
}
