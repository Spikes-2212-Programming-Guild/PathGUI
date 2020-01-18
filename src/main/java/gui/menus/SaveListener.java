package gui.menus;

import gui.GUI;
import gui.PathMaker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveListener implements ActionListener {
    private GUI gui;

    public SaveListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        int val = fileChooser.showSaveDialog(null);

        if(val == JFileChooser.APPROVE_OPTION && gui.getContentPane() instanceof PathMaker) {
            ((PathMaker)gui.getContentPane()).exportPath(fileChooser.getSelectedFile().toPath());
        }
    }
}