package gui.bars.listeners;

import gui.GUI;
import gui.Globals;
import gui.gains.GainsDialog;

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
        GainsDialog gainsDialog = new GainsDialog(gui);
        if(gainsDialog.showDialog(gui) == GainsDialog.APPROVE_OPTION) {
            gui.getPathPane().generatePath(gainsDialog.getGains());
            Globals.UNDO_STACK.push(Globals.Action.GENERATE);
        }
    }
}
