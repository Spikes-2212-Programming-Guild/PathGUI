package gui.menus;

import gui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MirrorListener implements ActionListener {
    private GUI gui;

    public MirrorListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ((PathMaker)gui.getContentPane()).mirrorPath();
        gui.getContentPane().repaint();
        Constants.undoStack.push(Constants.Action.MIRROR);
    }
}
