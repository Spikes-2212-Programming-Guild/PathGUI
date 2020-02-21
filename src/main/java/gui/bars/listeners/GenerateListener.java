package gui.bars.listeners;

import gui.*;
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
        if(Globals.GAINS_DIALOG.showDialog(gui) == GainsDialog.APPROVE_OPTION) {
            gui.getPathPane().generatePath(Globals.GAINS_DIALOG.getGains());
            Globals.UNDO_STACK.push(new Action(Action.ActionType.GENERATE));
        }
    }
}
