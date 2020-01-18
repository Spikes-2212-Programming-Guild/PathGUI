package gui.constants;

import javax.swing.*;
import java.awt.*;

public class ConstantsDialog extends JPanel {
    private JTextField spacing;
    private JTextField smoothWeight;
    private JTextField tolerance;
    private JTextField maxVelocity;
    private JTextField turningConstant;
    private JTextField maxAcceleration;

    private JButton save;

    public ConstantsDialog() {
        setLayout(new GridLayout(7, 2));
        spacing = new JTextField(20);
        smoothWeight = new JTextField(20);
        tolerance = new JTextField(20);
        maxVelocity = new JTextField(20);
        turningConstant = new JTextField(20);
        maxAcceleration = new JTextField(20);
        save = new JButton("Save");

        add(new JLabel("Spacing:"));
        add(spacing);
        add(new JLabel("Smooth Weight:"));
        add(smoothWeight);
        add(new JLabel("Tolerance:"));
        add(tolerance);
        add(new JLabel("Max Velocity:"));
        add(maxVelocity);
        add(new JLabel("Turning Constant:"));
        add(turningConstant);
        add(new JLabel("Max Acceleration:"));
        add(maxAcceleration);
        add(save);

        setSize(640, 480);
    }

    public static Constants showDialog() {
        ConstantsDialog dialog = new ConstantsDialog();
        JDialog frame = new JDialog();

        dialog.save.addActionListener(new CloseListener(frame));

        frame.setModal(true);
        frame.setContentPane(dialog);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        return new Constants(Double.parseDouble(dialog.spacing.getText()),
                Double.parseDouble(dialog.smoothWeight.getText()), Double.parseDouble(dialog.tolerance.getText()),
                Double.parseDouble(dialog.maxVelocity.getText()), Double.parseDouble(dialog.turningConstant.getText()),
                Double.parseDouble(dialog.maxAcceleration.getText()));
    }
}
