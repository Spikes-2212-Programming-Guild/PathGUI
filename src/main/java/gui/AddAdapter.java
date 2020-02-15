package gui;

import com.spikes2212.path.Waypoint;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * An adapter that adds {@link Waypoint}s to an {@link Addable} when the left mouse button is clicked.
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
        Point point = e.getPoint();
        if(addable.select(point.getX() * Globals.CM_TO_M,
                (Globals.FIELD_WIDTH - point.getY()) * Globals.CM_TO_M))
            return;

        addable.add(new Waypoint(point.getX() * Globals.CM_TO_M,
                (Globals.FIELD_WIDTH - point.getY()) * Globals.CM_TO_M));
        Globals.UNDO_STACK.push(Globals.Action.ADD);
    }
}