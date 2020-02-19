package gui.bars.listeners;

import gui.GUI;
import gui.Globals;
import gui.gains.GainsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The action listener for generating paths.
 *
 * @author Eran Goldstein
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
        gui.getPathPane().generatePath(GainsPanel.showDialog(gui));
        Globals.UNDO_STACK.push(Globals.Action.GENERATE);
    }
}