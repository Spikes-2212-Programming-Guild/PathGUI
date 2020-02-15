package gui;

import com.spikes2212.path.Waypoint;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddListener extends MouseAdapter {
    private Addable addable;

    public AddListener(Addable addable) {
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
