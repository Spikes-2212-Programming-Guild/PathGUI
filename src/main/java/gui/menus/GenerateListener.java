package gui.menus;

import gui.*;
import gui.gains.GainsDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateListener implements ActionListener {
    private GUI gui;

    public GenerateListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ((PathMaker)gui.getContentPane()).generatePath(gui, GainsDialog.showDialog(gui));
        Constants.undoStack.push(Constants.Action.GENERATE);
    }
}
