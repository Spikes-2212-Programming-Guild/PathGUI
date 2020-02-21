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
                    gui.getPathPane().add(lastAction.getTo());
                    break;
                case MIRROR:
                    gui.getPathPane().mirrorPath();
                    break;
                case GENERATE:
                    gui.getPathPane().generatePath(Gains.getPreferences());
                    break;
            }

            Globals.UNDO_STACK.push(lastAction);
        }
    }
}
