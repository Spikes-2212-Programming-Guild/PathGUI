package gui;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI() {
        setContentPane(new PathMaker());
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setPreferredSize(new Dimension(1598, 821));
        gui.setResizable(false);
        gui.pack();
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
