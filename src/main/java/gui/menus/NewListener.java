package gui.menus;

import gui.GUI;
import gui.PathMaker;

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
        ((PathMaker)gui.getContentPane()).update();
    }
}
