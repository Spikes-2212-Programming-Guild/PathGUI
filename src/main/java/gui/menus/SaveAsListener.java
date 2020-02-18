package gui.menus;

import gui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

/**
 * The action listener for saving files.
 *
 * @author Eran Goldstein
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
        int val = Globals.FILE_CHOOSER.showSaveDialog(gui);

        if(val == JFileChooser.APPROVE_OPTION) {
            Path path = Globals.FILE_CHOOSER.getSelectedFile().toPath();
            ((PathMaker)gui.getContentPane()).savePath(path);
            Globals.PREFS.put("WORKING_DIRECTORY", path.toAbsolutePath().toString());
        }

        Globals.UNDO_STACK.push(Globals.Action.NONE);
    }
}
