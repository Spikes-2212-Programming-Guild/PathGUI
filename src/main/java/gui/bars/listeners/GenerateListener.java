package gui.bars.listeners;

import components.PathListener;
import gui.*;
import gui.gains.GainsDialog;

/**
 * The action listener for generating paths.
 *
 * @author Eran Goldstein
 */
public class GenerateListener extends PathListener {
    public GenerateListener(GUI context) {
        super(context);
    }

    @Override
    protected Action performAction() {
        super.performAction();
        if(Globals.GAINS_DIALOG.showDialog(context) == GainsDialog.APPROVE_OPTION)
            manipulator.generatePath(Globals.GAINS_DIALOG.getGains());
        return new Action(Action.ActionType.GENERATE);
    }
}
