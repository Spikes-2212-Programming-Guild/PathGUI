package gui.bars.listeners;

import gui.*;
import path.Gains;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The action listener for generating paths using the default gains.
 *
 * @author Eran Goldstein
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
        gui.getPathPane().generatePath(Gains.getPreferences());
        Globals.UNDO_STACK.push(new Action(Action.ActionType.GENERATE));
    }
}
