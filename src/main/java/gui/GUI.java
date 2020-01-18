package gui;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

public class GUI extends JFrame {
    public static void main(String[] args)  {
        GUI gui = new GUI();
        gui.setPreferredSize(new Dimension(1598, 821));
        gui.setResizable(false);
        gui.pack();
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public GUI() {
        try {
            setContentPane(new PathViewer(Paths.get("D:/paths/path.csv")));
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, e.toString(), "Oops", JOptionPane.ERROR_MESSAGE);
        }
    }
}
