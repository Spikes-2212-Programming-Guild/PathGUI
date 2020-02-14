package gui.menus;

import gui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewListener implements ActionListener {
    private GUI gui;

    public NewListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ((PathMaker)gui.getContentPane()).newPath();
        gui.getContentPane().repaint();
        Constants.actions.push(Constants.Action.NONE);
    }
}
