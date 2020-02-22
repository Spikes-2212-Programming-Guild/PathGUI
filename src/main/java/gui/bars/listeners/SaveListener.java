package gui.bars.listeners;

import components.PathListener;
import gui.Action;
import gui.*;

import javax.swing.*;
import java.nio.file.Path;

/**
 * The action listener for saving files.
 *
 * @author Eran Goldstein
 */
public class SaveListener extends PathListener {
    public SaveListener(GUI context) {
        super(context);
    }

    @Override
    protected Action performAction() {
        if(Globals.FILE_CHOOSER.showSaveDialog(context) == JFileChooser.APPROVE_OPTION) {
            Path path = Globals.FILE_CHOOSER.getSelectedFile().toPath();
            manipulator.savePath(path);
            Globals.PREFS.put("WORKING_DIRECTORY", path.toAbsolutePath().toString());
        }

        return null;
    }
}
