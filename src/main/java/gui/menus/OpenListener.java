package gui.menus;

import gui.GUI;
import gui.PathViewer;

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
        int val = fileChooser.showOpenDialog(null);

        if(val == JFileChooser.APPROVE_OPTION) {
            Path path = fileChooser.getSelectedFile().toPath();
            gui.setContentPane(new PathViewer(path));
            gui.getPreferences().put("WORKING_DIRECTORY", path.toAbsolutePath().toString());
        }
    }
}
