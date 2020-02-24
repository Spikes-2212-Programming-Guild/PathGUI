package gui.bars.listeners;

import components.PathListener;
import gui.Action;
import gui.GUI;

/**
 * The action listener for mirroring paths.
 *
 * @author Eran Goldstein
 */
public class MirrorListener extends PathListener {
    public MirrorListener(GUI context) {
        super(context);
    }

    @Override
    protected Action performAction() {
        manipulator.mirrorPath();
        return new Action(Action.ActionType.MIRROR);
    }
}
