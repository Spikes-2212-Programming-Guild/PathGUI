package gui;

import gui.menus.PathMenuBar;
import gui.pane.PathPane;

import javax.swing.*;

/**
 * The main frame that holds the paths GUI.
 *
 * @author Eran Goldstein
 */
public class GUI extends JFrame {
    public GUI() {
        setContentPane(new PathPane(this));
        setJMenuBar(new PathMenuBar(this));
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setTitle("Spikes Path Drawing Tool");
        gui.setResizable(false);
        gui.pack();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}
