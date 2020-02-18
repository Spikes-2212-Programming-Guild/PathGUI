package gui.menus;

import gui.*;
import gui.gains.Gains;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The action listener for generating paths using the default gains.
 */
public class QuickGenerationListener implements ActionListener {
    /**
     * A user interface object.
     */
    private GUI gui;

    public QuickGenerationListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ((PathMaker)gui.getContentPane()).generatePath(Gains.getPreferences());
        Globals.UNDO_STACK.push(Globals.Action.GENERATE);
    }
}
