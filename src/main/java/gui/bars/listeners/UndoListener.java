package gui.bars.listeners;

import gui.GUI;
import gui.Globals;

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
            switch(Globals.UNDO_STACK.pop()) {
                case ADD:
                    gui.getPathPane().removeLast();
                    break;
                case MIRROR:
                    gui.getPathPane().mirrorPath();
                    break;
                case GENERATE:
                    gui.getPathPane().ungeneratePath();
                    break;
            }
        }
    }
}
