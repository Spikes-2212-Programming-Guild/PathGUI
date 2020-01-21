package gui.constants;

import gui.GUI;

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

    public ConstantsDialog(GUI gui) {
        setLayout(new GridLayout(7, 2));
        spacing = new JTextField(20);
        spacing.setText(gui.getPreferences().get("SPACING", "0.075"));
        smoothWeight = new JTextField(20);
        smoothWeight.setText(gui.getPreferences().get("SMOOTH_WEIGHT", "0.6"));
        tolerance = new JTextField(20);
        tolerance.setText(gui.getPreferences().get("TOLERANCE", "0.6"));
        maxVelocity = new JTextField(20);
        maxVelocity.setText(gui.getPreferences().get("MAX_VELOCITY", "3.05"));
        turningConstant = new JTextField(20);
        turningConstant.setText(gui.getPreferences().get("TURNING_CONSTANT", "3"));
        maxAcceleration = new JTextField(20);
        maxAcceleration.setText(gui.getPreferences().get("MAX_ACCELERATION", "18"));
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

    public static Constants showDialog(GUI gui) {
        ConstantsDialog dialog = new ConstantsDialog(gui);
        JDialog frame = new JDialog();

        dialog.save.addActionListener(new CloseListener(frame));

        frame.setModal(true);
        frame.setContentPane(dialog);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        gui.getPreferences().put("SPACING", dialog.spacing.getText());
        gui.getPreferences().put("SMOOTH_WEIGHT", dialog.smoothWeight.getText());
        gui.getPreferences().put("TOLERANCE", dialog.tolerance.getText());
        gui.getPreferences().put("MAX_VELOCITY", dialog.maxVelocity.getText());
        gui.getPreferences().put("TURNING_CONSTANT", dialog.turningConstant.getText());
        gui.getPreferences().put("MAX_ACCELERATION", dialog.maxAcceleration.getText());

        return new Constants(Double.parseDouble(dialog.spacing.getText()),
                Double.parseDouble(dialog.smoothWeight.getText()), Double.parseDouble(dialog.tolerance.getText()),
                Double.parseDouble(dialog.maxVelocity.getText()), Double.parseDouble(dialog.turningConstant.getText()),
                Double.parseDouble(dialog.maxAcceleration.getText()));
    }
}
