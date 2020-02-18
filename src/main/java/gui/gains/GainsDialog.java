package gui.gains;

import gui.GUI;
import gui.Globals;

import javax.swing.*;
import java.awt.*;

/**
 * The dialog for manual entry of path generation gains.
 */
public class GainsDialog extends JPanel {
    /**
     * A text field input for the spacing between points on the generated path.
     */
    private JTextField spacing;

    /**
     * A text field input for how much to smooth the path.
     */
    private JTextField smoothWeight;

    /**
     * A text field input for the highest tolerable distance between the smoothed and original paths.
     */
    private JTextField tolerance;

    /**
     * A text field input the maximum velocity of the robot.
     */
    private JTextField maxVelocity;

    /**
     * A text field input the turning constant for the path generation algorithm.
     */
    private JTextField turningConstant;

    /**
     * A text field input for the maximum acceleration of the robot.
     */
    private JTextField maxAcceleration;

    /**
     * A button that generates the path.
     */
    private JButton generate;

    public GainsDialog(GUI gui) {
        setLayout(new GridLayout(7, 2));
        spacing = new JTextField(gui.getPreferences().get("SPACING", "0.075"), Globals.TEXTFIELD_COLUMNS);
        smoothWeight = new JTextField(gui.getPreferences().get("SMOOTH_WEIGHT", "0.6"), Globals.TEXTFIELD_COLUMNS);
        tolerance = new JTextField(gui.getPreferences().get("TOLERANCE", "0.6"), Globals.TEXTFIELD_COLUMNS);
        maxVelocity = new JTextField(gui.getPreferences().get("MAX_VELOCITY", "3.05"), Globals.TEXTFIELD_COLUMNS);
        turningConstant = new JTextField(gui.getPreferences().get("TURNING_CONSTANT", "3"), Globals.TEXTFIELD_COLUMNS);
        maxAcceleration = new JTextField(gui.getPreferences().get("MAX_ACCELERATION", "18"), Globals.TEXTFIELD_COLUMNS);
        generate = new JButton("Generate");

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
        add(generate);
    }

    /**
     * Show the gains dialog.
     *
     * @param context the context for the dialog
     * @return the gains from the user input
     */
    public static Gains showDialog(GUI context) {
        GainsDialog dialog = new GainsDialog(context);
        JDialog frame = new JDialog();

        dialog.generate.addActionListener(new CloseListener(frame));

        frame.setModal(true);
        frame.setContentPane(dialog);
        frame.setTitle("Gains | Spikes Path Drawing Tool");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(context);
        frame.setVisible(true);

        context.getPreferences().put("SPACING", dialog.spacing.getText());
        context.getPreferences().put("SMOOTH_WEIGHT", dialog.smoothWeight.getText());
        context.getPreferences().put("TOLERANCE", dialog.tolerance.getText());
        context.getPreferences().put("MAX_VELOCITY", dialog.maxVelocity.getText());
        context.getPreferences().put("TURNING_CONSTANT", dialog.turningConstant.getText());
        context.getPreferences().put("MAX_ACCELERATION", dialog.maxAcceleration.getText());

        return new Gains(Double.parseDouble(dialog.spacing.getText()),
                Double.parseDouble(dialog.smoothWeight.getText()), Double.parseDouble(dialog.tolerance.getText()),
                Double.parseDouble(dialog.maxVelocity.getText()), Double.parseDouble(dialog.turningConstant.getText()),
                Double.parseDouble(dialog.maxAcceleration.getText()));
    }
}
