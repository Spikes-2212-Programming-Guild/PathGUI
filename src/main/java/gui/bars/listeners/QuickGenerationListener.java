package gui.bars.listeners;

import components.PathListener;
import gui.Action;
import gui.GUI;
import path.Gains;

/**
 * The action listener for generating paths using the default gains.
 *
 * @author Eran Goldstein
 */
public class QuickGenerationListener extends PathListener {
    public QuickGenerationListener(GUI context) {
        super(context);
    }

    @Override
    protected Action performAction() {
        manipulator.generatePath(Gains.getPreferences());
        return new Action(Action.ActionType.GENERATE);
    }
}
