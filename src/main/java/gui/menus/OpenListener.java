package gui.menus;

import gui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

public class OpenListener implements ActionListener {
    private GUI gui;

    public OpenListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = gui.getFileChooser();
        int val = fileChooser.showOpenDialog(gui);

        if(val == JFileChooser.APPROVE_OPTION) {
            Path path = fileChooser.getSelectedFile().toPath();
            ((PathMaker)gui.getContentPane()).importPath(path);
            gui.getContentPane().repaint();
            gui.getPreferences().put("WORKING_DIRECTORY", path.toAbsolutePath().toString());
        }

        Constants.lastAction = Constants.Action.NONE;
    }
}
