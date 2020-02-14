package gui.menus;

import gui.GUI;
import gui.PathMaker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

public class SaveListener implements ActionListener {
    private GUI gui;

    public SaveListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = gui.getFileChooser();
        int val = fileChooser.showSaveDialog(gui);

        if(val == JFileChooser.APPROVE_OPTION && gui.getContentPane() instanceof PathMaker) {
            Path path = fileChooser.getSelectedFile().toPath();
            ((PathMaker)gui.getContentPane()).savePath(path);
            gui.getPreferences().put("WORKING_DIRECTORY", path.toAbsolutePath().toString());
        }
    }
}
