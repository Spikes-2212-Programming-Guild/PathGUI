package gui.bars.listeners;

import gui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

/**
 * The action listener for mirroring paths.
 *
 * @author Eran Goldstein
 */
public class MirrorListener implements ActionListener {
    /**
     * A user interface object.
     */
    private GUI gui;

    public MirrorListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        gui.getPathPane().mirrorPath();
        Globals.UNDO_STACK.push(new Action(Action.ActionType.MIRROR));
        Globals.REDO_STACK = new Stack<>();
    }
}
