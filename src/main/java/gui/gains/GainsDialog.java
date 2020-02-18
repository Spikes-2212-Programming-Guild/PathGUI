package gui.gains;

import gui.GUI;
import gui.Globals;

import javax.swing.*;
import java.awt.*;

/**
 * The dialog for manual entry of path generation gains.
 *
 * @author Eran Goldstein
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

    public GainsDialog() {
        setLayout(new GridLayout(7, 2));

        spacing = new JTextField(Globals.prefs.get("SPACING", "0.075"), Globals.TEXTFIELD_COLUMNS);
        smoothWeight = new JTextField(Globals.prefs.get("SMOOTH_WEIGHT", "0.6"), Globals.TEXTFIELD_COLUMNS);
        tolerance = new JTextField(Globals.prefs.get("TOLERANCE", "0.6"), Globals.TEXTFIELD_COLUMNS);
        maxVelocity = new JTextField(Globals.prefs.get("MAX_VELOCITY", "3.05"), Globals.TEXTFIELD_COLUMNS);
        turningConstant = new JTextField(Globals.prefs.get("TURNING_CONSTANT", "3"), Globals.TEXTFIELD_COLUMNS);
        maxAcceleration = new JTextField(Globals.prefs.get("MAX_ACCELERATION", "18"), Globals.TEXTFIELD_COLUMNS);

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
        GainsDialog dialog = new GainsDialog();
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

        Globals.prefs.put("SPACING", dialog.spacing.getText());
        Globals.prefs.put("SMOOTH_WEIGHT", dialog.smoothWeight.getText());
        Globals.prefs.put("TOLERANCE", dialog.tolerance.getText());
        Globals.prefs.put("MAX_VELOCITY", dialog.maxVelocity.getText());
        Globals.prefs.put("TURNING_CONSTANT", dialog.turningConstant.getText());
        Globals.prefs.put("MAX_ACCELERATION", dialog.maxAcceleration.getText());

        return new Gains(Double.parseDouble(dialog.spacing.getText()),
                Double.parseDouble(dialog.smoothWeight.getText()), Double.parseDouble(dialog.tolerance.getText()),
                Double.parseDouble(dialog.maxVelocity.getText()), Double.parseDouble(dialog.turningConstant.getText()),
                Double.parseDouble(dialog.maxAcceleration.getText()));
    }
}
