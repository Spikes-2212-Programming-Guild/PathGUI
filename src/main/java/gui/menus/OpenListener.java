package gui.menus;

import gui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

/**
 * The action listener for opening files.
 */
public class OpenListener implements ActionListener {
    /**
     * A user interface object.
     */
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
            Globals.prefs.put("WORKING_DIRECTORY", path.toAbsolutePath().toString());
        }

        Globals.UNDO_STACK.push(Globals.Action.NONE);
    }
}
