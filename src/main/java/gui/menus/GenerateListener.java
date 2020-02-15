package gui.menus;

import gui.*;
import gui.gains.GainsDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The action listener for generating paths.
 */
public class GenerateListener implements ActionListener {
    /**
     * A user interface object.
     */
    private GUI gui;

    public GenerateListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ((PathMaker)gui.getContentPane()).generatePath(gui, GainsDialog.showDialog(gui));
        Globals.UNDO_STACK.push(Globals.Action.GENERATE);
    }
}
