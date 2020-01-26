package gui;

import gui.menus.PathMenuBar;

import javax.swing.*;
import java.awt.*;
import java.util.prefs.Preferences;

public class GUI extends JFrame {
    private JFileChooser fileChooser;
    private Preferences preferences;

    public GUI() {
        setContentPane(new PathMaker());
        setJMenuBar(new PathMenuBar(this));

        preferences = Preferences.userNodeForPackage(this.getClass());
        String workingDirectory = preferences.get("WORKING_DIRECTORY", null);
        fileChooser = new JFileChooser(workingDirectory);
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        gui.setResizable(false);
        gui.pack();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public Preferences getPreferences() {
        return preferences;
    }
}
