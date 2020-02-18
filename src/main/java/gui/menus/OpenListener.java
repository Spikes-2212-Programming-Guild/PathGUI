package gui.menus;

import gui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

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
        int val = Globals.FILE_CHOOSER.showOpenDialog(gui);

        if(val == JFileChooser.APPROVE_OPTION) {
            Path path = Globals.FILE_CHOOSER.getSelectedFile().toPath();
            ((PathMaker)gui.getContentPane()).importPath(path);
            gui.getContentPane().repaint();
            Globals.PREFS.put("WORKING_DIRECTORY", path.toAbsolutePath().toString());
        }

        Globals.UNDO_STACK.push(Globals.Action.NONE);
    }
}
