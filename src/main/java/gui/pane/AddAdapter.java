package gui.pane;

import gui.Action;
import gui.Globals;
import path.Waypoint;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

/**
 * A mouse adapter that adds {@link Waypoint}s to an {@link Addable} when the left mouse button is clicked.
 *
 * @author Eran Goldstein
 */
public class AddAdapter extends MouseAdapter {
    /**
     * The {@link Addable} to add {@link Waypoint}s to.
     */
    private Addable addable;

    public AddAdapter(Addable addable) {
        this.addable = addable;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Waypoint waypoint = new Waypoint(e.getX() * Globals.CM_TO_M,
                (Globals.FIELD_WIDTH - e.getY()) * Globals.CM_TO_M);

        if(addable.select(waypoint))
            return;
        addable.add(waypoint);

        Globals.UNDO_STACK.push(new Action(Action.ActionType.ADD, waypoint));
        Globals.REDO_STACK = new Stack<>();
    }
}
