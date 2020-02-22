package gui.bars.listeners;

import components.PathListener;
import gui.Action;
import gui.*;
import path.Gains;

import javax.swing.*;
import java.nio.file.Path;

/**
 * The action listener for exporting paths.
 *
 * @author Eran Goldstein
 */
public class ExportListener extends PathListener {
    public ExportListener(GUI context) {
        super(context);
    }

    @Override
    protected Action performAction() {
        super.performAction();

        if(Globals.FILE_CHOOSER.showDialog(context, "Export") == JFileChooser.APPROVE_OPTION) {
            if(!manipulator.isGenerated())
                manipulator.generatePath(Gains.getPreferences());
            manipulator.alignPath();
            manipulator.moveToOrigin();

            Path path = Globals.FILE_CHOOSER.getSelectedFile().toPath();
            manipulator.savePath(path);
            Globals.PREFS.put("WORKING_DIRECTORY", path.toAbsolutePath().toString());
        }

        return null;
    }
}
