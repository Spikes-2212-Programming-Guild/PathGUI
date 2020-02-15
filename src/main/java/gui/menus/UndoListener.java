package gui.menus;

import gui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoListener implements ActionListener {
    GUI gui;

    public UndoListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(!Constants.actions.isEmpty()) {
            switch(Constants.actions.pop()) {
                case ADD:
                    ((PathMaker)gui.getContentPane()).removeLast();
                    break;
                case MIRROR:
                    ((PathMaker)gui.getContentPane()).mirrorPath();
                    break;
                case GENERATE:
                    ((PathMaker)gui.getContentPane()).ungeneratedPath();
                    break;
                case NONE:
                default:
                    break;
            }

            gui.getContentPane().repaint();
        }
    }
}
