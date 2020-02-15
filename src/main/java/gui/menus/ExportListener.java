package gui.menus;

import gui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

/**
 * The action listener for exporting paths.
 */
public class ExportListener implements ActionListener {
    /**
     * A user interface object.
     */
    private GUI gui;

    public ExportListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ((PathMaker)gui.getContentPane()).alignPath();
        ((PathMaker)gui.getContentPane()).moveToOrigin();

        JFileChooser fileChooser = gui.getFileChooser();
        int val = fileChooser.showSaveDialog(gui);

        if(val == JFileChooser.APPROVE_OPTION) {
            Path path = fileChooser.getSelectedFile().toPath();
            ((PathMaker)gui.getContentPane()).savePath(path);
            gui.getPreferences().put("WORKING_DIRECTORY", path.toAbsolutePath().toString());
        }

        Globals.UNDO_STACK.push(Globals.Action.NONE);
    }
}
