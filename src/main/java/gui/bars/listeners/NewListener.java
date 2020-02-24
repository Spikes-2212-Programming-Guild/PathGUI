package gui.bars.listeners;

import components.PathListener;
import gui.Action;
import gui.GUI;

/**
 * The action listener for creating a new file.
 *
 * @author Eran Goldstein
 */
public class NewListener extends PathListener {
    public NewListener(GUI context) {
        super(context);
    }

    @Override
    protected Action performAction() {
        context.getPathPane().newPath();
        return null;
    }
}
