package gui.menus;

import gui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The action listener for creating a new file.
 *
 * @author Eran Goldstein
 */
public class NewListener implements ActionListener {
    /**
     * A user interface object.
     */
    private GUI gui;

    public NewListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ((PathMaker)gui.getContentPane()).newPath();
        gui.getContentPane().repaint();
        Globals.UNDO_STACK.push(Globals.Action.NONE);
    }
}
