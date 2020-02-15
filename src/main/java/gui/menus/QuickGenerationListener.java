package gui.menus;

import gui.*;
import gui.gains.Gains;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuickGenerationListener implements ActionListener {
    private GUI gui;

    public QuickGenerationListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ((PathMaker)gui.getContentPane()).generatePath(gui, Gains.getPreferences(gui));
        Constants.undoStack.push(Constants.Action.GENERATE);
    }
}
