package gui;

import gui.menus.PathMenuBar;

import javax.swing.*;

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

    public GUI() {
        setContentPane(new PathMaker());
        setJMenuBar(new PathMenuBar(this));

        fileChooser = new JFileChooser(Globals.prefs.get("WORKING_DIRECTORY", null));
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
}
