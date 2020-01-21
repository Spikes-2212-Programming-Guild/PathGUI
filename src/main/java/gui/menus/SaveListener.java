package gui.menus;

import com.spikes2212.path.Waypoint;
import gui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

public class SaveListener implements ActionListener {
    private GUI gui;

    public SaveListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = gui.getFileChooser();
        int val = fileChooser.showSaveDialog(null);

        if(val == JFileChooser.APPROVE_OPTION && gui.getContentPane() instanceof PathMaker) {
            Path path = fileChooser.getSelectedFile().toPath();
            Waypoint first = ((PathMaker)gui.getContentPane()).getFirst();

            ((PathMaker)gui.getContentPane()).moveToOrigin();
            ((PathMaker)gui.getContentPane()).alignPath();
            ((PathMaker)gui.getContentPane()).exportPath(path, gui);

            gui.setContentPane(new PathViewer(path, first.getX(), first.getY()));
            gui.getPreferences().put("WORKING_DIRECTORY", path.toAbsolutePath().toString());
        }
    }
}
