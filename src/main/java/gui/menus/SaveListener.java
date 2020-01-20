package gui.menus;

import com.spikes2212.path.Waypoint;
import gui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveListener implements ActionListener {
    private GUI gui;

    public SaveListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        int val = fileChooser.showSaveDialog(null);

        if(val == JFileChooser.APPROVE_OPTION && gui.getContentPane() instanceof PathMaker) {
            Waypoint first = ((PathMaker)gui.getContentPane()).getFirst();
            ((PathMaker)gui.getContentPane()).moveToOrigin();
            ((PathMaker)gui.getContentPane()).alignPath();
            ((PathMaker)gui.getContentPane()).exportPath(fileChooser.getSelectedFile().toPath());
            gui.setContentPane(new PathViewer(fileChooser.getSelectedFile().toPath(), first.getX(), first.getY()));
        }
    }
}
