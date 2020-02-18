package gui.menus;

import gui.GUI;
import gui.Globals;
import gui.pane.PathPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

/**
 * The action listener for exporting paths.
 *
 * @author Eran Goldstein
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
        ((PathPane)gui.getContentPane()).alignPath();
        ((PathPane)gui.getContentPane()).moveToOrigin();

        if(Globals.FILE_CHOOSER.showSaveDialog(gui) == JFileChooser.APPROVE_OPTION) {
            Path path = Globals.FILE_CHOOSER.getSelectedFile().toPath();
            ((PathPane)gui.getContentPane()).savePath(path);
            Globals.PREFS.put("WORKING_DIRECTORY", path.toAbsolutePath().toString());
        }

        Globals.UNDO_STACK.push(Globals.Action.NONE);
    }
}
