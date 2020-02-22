package gui;

import gui.bars.PathMenuBar;
import gui.bars.listeners.CSVFilter;
import gui.pane.PathPane;

import javax.swing.*;

/**
 * The main frame that holds the paths GUI.
 *
 * @author Eran Goldstein
 */
public class GUI extends JFrame {
    public GUI(String title) {
        super(title);
        setContentPane(new PathPane(this));
        setJMenuBar(new PathMenuBar(this));

        Globals.FILE_CHOOSER.addChoosableFileFilter(new CSVFilter());
        Globals.FILE_CHOOSER.setAcceptAllFileFilterUsed(false);
    }

    public static void main(String[] args) {
        GUI gui = new GUI("Spikes Path Drawing Tool");
        gui.setResizable(false);
        gui.pack();
        gui.setLocationRelativeTo(null);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }

    public PathPane getPathPane() {
        return (PathPane)getContentPane();
    }
}
