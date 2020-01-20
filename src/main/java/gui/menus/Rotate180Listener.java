package gui.menus;

import gui.GUI;
import gui.PathMaker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rotate180Listener implements ActionListener {
    private GUI gui;

    public Rotate180Listener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(gui.getContentPane() instanceof PathMaker) {
            ((PathMaker)gui.getContentPane()).rotatePath(Math.PI);
        }
    }
}
