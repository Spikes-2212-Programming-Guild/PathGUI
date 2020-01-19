package gui.menus;

import gui.GUI;
import gui.PathMaker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OriginListener implements ActionListener {
    GUI gui;

    public OriginListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(gui.getContentPane() instanceof PathMaker) {
            ((PathMaker)gui.getContentPane()).moveToOrigin();
        }
    }
}
