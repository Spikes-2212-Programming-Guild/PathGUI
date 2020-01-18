package gui.menus;

import gui.GUI;
import gui.PathMaker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewListener implements ActionListener { // TODO bugfix
    GUI gui;
    JMenuBar menuBar;

    public NewListener(GUI gui, JMenuBar menuBar) {
        this.gui = gui;
        this.menuBar = menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        gui.setContentPane(new PathMaker());
    }
}
