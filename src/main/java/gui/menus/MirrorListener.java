package gui.menus;

import gui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The action listener for mirroring paths.
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
        ((PathMaker)gui.getContentPane()).mirrorPath();
        gui.getContentPane().repaint();
        Globals.UNDO_STACK.push(Globals.Action.MIRROR);
    }
}
