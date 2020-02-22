package components;

import gui.*;
import path.PathManipulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

/**
 * An {@link ActionListener} that pushes the action into the {@code UNDO_STACK} and repaints the path pane.
 *
 * @author Eran Goldstein
 */
public abstract class PathListener implements ActionListener {
    /**
     * A graphical user interface object.
     */
    protected GUI context;

    /**
     * An object that manipulates a path.
     */
    protected PathManipulator manipulator;

    protected PathListener(GUI context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        manipulator = context.getPathPane().getPathManipulator();
        Action action = performAction();
        if(action != null)
            Globals.UNDO_STACK.push(action);
        else
            Globals.UNDO_STACK = new Stack<>();
        Globals.REDO_STACK = new Stack<>();

        context.repaint();
    }

    /**
     * Performs the action.
     *
     * @return an {@link Action} object representing the performed action
     */
    protected abstract Action performAction();
}
