package gui;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI() {
        add(new PathMaker());

        JMenuBar menuBar = new PathMenuBar();
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setPreferredSize(new Dimension(1598, 821));
        gui.setResizable(false);
        gui.pack();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}
