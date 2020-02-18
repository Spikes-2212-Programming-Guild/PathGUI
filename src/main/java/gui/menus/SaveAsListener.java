package gui.menus;

import gui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

/**
 * The action listener for saving files.
 */
public class SaveAsListener implements ActionListener {
    /**
     * A user interface object.
     */
    private GUI gui;

    public SaveAsListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = gui.getFileChooser();
        int val = fileChooser.showSaveDialog(gui);

        if(val == JFileChooser.APPROVE_OPTION) {
            Path path = fileChooser.getSelectedFile().toPath();
            ((PathMaker)gui.getContentPane()).savePath(path);
            Globals.prefs.put("WORKING_DIRECTORY", path.toAbsolutePath().toString());
        }

        Globals.UNDO_STACK.push(Globals.Action.NONE);
    }
}
