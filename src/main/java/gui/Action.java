package gui;

import path.Waypoint;

public class Action {
    public enum ActionType {
        ADD, MIRROR, GENERATE
    }

    private ActionType type;
    private Waypoint to;

    public Action(ActionType type, Waypoint to) {
        this.type = type;
        this.to = to;
    }

    public Action(ActionType type) {
        this(type, new Waypoint(0, 0));
    }

    public ActionType getType() {
        return type;
    }

    public Waypoint getTo() {
        return to;
    }
}
