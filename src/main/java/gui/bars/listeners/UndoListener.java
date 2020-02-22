package gui.bars.listeners;

import gui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The action listener for undoing actions.
 *
 * @author Eran Goldstein
 */
public class UndoListener implements ActionListener {
    /**
     * A user interface object.
     */
    private GUI gui;

    public UndoListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(!Globals.UNDO_STACK.isEmpty()) {
            Action lastAction = Globals.UNDO_STACK.pop();
            switch(lastAction.getType()) {
                case ADD:
                    gui.getPathPane().getPathManipulator().removeLast();
                    break;
                case MIRROR:
                    gui.getPathPane().getPathManipulator().mirrorPath();
                    break;
                case GENERATE:
                    gui.getPathPane().getPathManipulator().ungeneratePath();
                    break;
            }

            Globals.REDO_STACK.push(lastAction);
        }
    }
}
