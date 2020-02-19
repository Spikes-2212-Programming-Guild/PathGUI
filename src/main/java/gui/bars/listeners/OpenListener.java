package gui.bars.listeners;

import gui.GUI;
import gui.Globals;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.Stack;

/**
 * The action listener for opening files.
 *
 * @author Eran Goldstein
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
        if(Globals.FILE_CHOOSER.showOpenDialog(gui) == JFileChooser.APPROVE_OPTION) {
            Path path = Globals.FILE_CHOOSER.getSelectedFile().toPath();
            gui.getPathPane().importPath(path);
            Globals.PREFS.put("WORKING_DIRECTORY", path.toAbsolutePath().toString());
            Globals.UNDO_STACK = new Stack<>();
        }
    }
}
