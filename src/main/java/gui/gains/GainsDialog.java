package gui.gains;

import gui.Constants;
import gui.GUI;

import javax.swing.*;
import java.awt.*;

public class GainsDialog extends JPanel {
    private JTextField spacing;
    private JTextField smoothWeight;
    private JTextField tolerance;
    private JTextField maxVelocity;
    private JTextField turningConstant;
    private JTextField maxAcceleration;

    private JButton save;

    public GainsDialog(GUI gui) {
        setLayout(new GridLayout(7, 2));
        spacing = new JTextField(Constants.TEXTFIELD_COLUMNS);
        spacing.setText(gui.getPreferences().get("SPACING", "0.075"));
        smoothWeight = new JTextField(Constants.TEXTFIELD_COLUMNS);
        smoothWeight.setText(gui.getPreferences().get("SMOOTH_WEIGHT", "0.6"));
        tolerance = new JTextField(Constants.TEXTFIELD_COLUMNS);
        tolerance.setText(gui.getPreferences().get("TOLERANCE", "0.6"));
        maxVelocity = new JTextField(Constants.TEXTFIELD_COLUMNS);
        maxVelocity.setText(gui.getPreferences().get("MAX_VELOCITY", "3.05"));
        turningConstant = new JTextField(Constants.TEXTFIELD_COLUMNS);
        turningConstant.setText(gui.getPreferences().get("TURNING_CONSTANT", "3"));
        maxAcceleration = new JTextField(Constants.TEXTFIELD_COLUMNS);
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

        setSize(Constants.GAINS_WIDTH, Constants.GAINS_HEIGHT);
    }

    public static Gains showDialog(GUI gui) {
        GainsDialog dialog = new GainsDialog(gui);
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

        return new Gains(Double.parseDouble(dialog.spacing.getText()),
                Double.parseDouble(dialog.smoothWeight.getText()), Double.parseDouble(dialog.tolerance.getText()),
                Double.parseDouble(dialog.maxVelocity.getText()), Double.parseDouble(dialog.turningConstant.getText()),
                Double.parseDouble(dialog.maxAcceleration.getText()));
    }
}
