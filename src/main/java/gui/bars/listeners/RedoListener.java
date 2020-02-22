package gui.bars.listeners;

import gui.*;
import path.Gains;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The action listener for redoing actions.
 *
 * @author Eran Goldstein
 */
public class RedoListener implements ActionListener {
    /**
     * A user interface object.
     */
    private GUI gui;

    public RedoListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(!Globals.REDO_STACK.isEmpty()) {
            Action lastAction = Globals.REDO_STACK.pop();
            switch(lastAction.getType()) {
                case ADD:
                    gui.getPathPane().getPathManipulator().add(lastAction.getDestination());
                    break;
                case MIRROR:
                    gui.getPathPane().getPathManipulator().mirrorPath();
                    break;
                case GENERATE:
                    gui.getPathPane().getPathManipulator().generatePath(Gains.getPreferences());
                    break;
            }

            Globals.UNDO_STACK.push(lastAction);
            gui.repaint();
        }
    }
}
