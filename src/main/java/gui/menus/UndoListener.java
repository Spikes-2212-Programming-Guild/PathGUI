package gui.menus;

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
            Globals.Action lastAction = Globals.UNDO_STACK.pop();

            switch(lastAction) {
                case ADD:
                    ((PathMaker)gui.getContentPane()).removeLast();
                    break;
                case MIRROR:
                    ((PathMaker)gui.getContentPane()).mirrorPath();
                    break;
                case GENERATE:
                    ((PathMaker)gui.getContentPane()).ungeneratePath();
                    break;
                case NONE:
                default:
                    break;
            }
        }
    }
}
