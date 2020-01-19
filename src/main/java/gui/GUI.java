package gui;

import gui.menus.PathMenuBar;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI() {
        setContentPane(new PathMaker());
        setJMenuBar(new PathMenuBar(this));
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setPreferredSize(new Dimension(814, 830));
        gui.setResizable(false);
        gui.pack();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}
