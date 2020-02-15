package gui;

import gui.menus.PathMenuBar;
import gui.toolbar.PathToolBar;

import javax.swing.*;
import java.util.prefs.Preferences;

/**
 * The main frame that holds the paths GUI.
 */
public class GUI extends JFrame {
    /**
     * The file chooser used when opening and saving path files.
     *
     * Having one global file chooser allows us to keep directory preferences without constant accesses to the
     * Preferences object.
     */
    private JFileChooser fileChooser;

    /**
     * The preferences object that saves the preferences for default directory and path generation gains.
     */
    private Preferences preferences;

    public GUI() {
        setContentPane(new PathMaker());
        setJMenuBar(new PathMenuBar(this));
        add(new PathToolBar());

        preferences = Preferences.userNodeForPackage(this.getClass());
        fileChooser = new JFileChooser(preferences.get("WORKING_DIRECTORY", null));
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setTitle("Spikes Path Drawing Tool");
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
