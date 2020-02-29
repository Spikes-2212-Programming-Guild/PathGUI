package gui.bars.listeners;

import components.PathListener;
import gui.Action;
import gui.*;
import path.Gains;
import path.PathManipulator;

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
        if(Globals.FILE_CHOOSER.showDialog(context, "Export") == JFileChooser.APPROVE_OPTION) {
            PathManipulator pathManipulator = new PathManipulator(manipulator);
            if(!manipulator.isGenerated())
                pathManipulator.generatePath(Gains.getPreferences());
            pathManipulator.alignPath();
            pathManipulator.moveToOrigin();

            Path path = Globals.FILE_CHOOSER.getSelectedFile().toPath();
            pathManipulator.savePath(path);
            Globals.PREFS.put("WORKING_DIRECTORY", path.toAbsolutePath().toString());
            return null;
        }

        return Action.NO_ACTION;
    }
}
