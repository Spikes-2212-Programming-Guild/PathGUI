package gui.menus;

import gui.GUI;
import gui.PathMaker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotateListener implements ActionListener {
    private GUI gui;

    public RotateListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(gui.getContentPane() instanceof PathMaker) {
            String angle = JOptionPane.showInputDialog(gui, "Rotation Angle in Degrees Clockwise:",
                    "rotate", JOptionPane.QUESTION_MESSAGE);

            try {
                ((PathMaker)gui.getContentPane()).rotatePath(Math.toRadians(Double.parseDouble(angle)));
            } catch(Exception ignored) {
            }
        }
    }
}
