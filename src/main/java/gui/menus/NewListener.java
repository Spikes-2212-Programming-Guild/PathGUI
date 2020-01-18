package gui.menus;

import gui.GUI;
import gui.PathMaker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewListener implements ActionListener { // TODO bugfix
    GUI gui;

    public NewListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        gui.setContentPane(new PathMaker());
    }
}
