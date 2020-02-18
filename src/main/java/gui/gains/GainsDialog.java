package gui.gains;

import components.PathNumberField;
import gui.GUI;
import gui.Globals;
import path.Gains;

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
    private PathNumberField spacing;

    /**
     * A text field input for how much to smooth the path.
     */
    private PathNumberField smoothWeight;

    /**
     * A text field input for the highest tolerable distance between the smoothed and original paths.
     */
    private PathNumberField tolerance;

    /**
     * A text field input the maximum velocity of the robot.
     */
    private PathNumberField maxVelocity;

    /**
     * A text field input the turning constant for the path generation algorithm.
     */
    private PathNumberField turningConstant;

    /**
     * A text field input for the maximum acceleration of the robot.
     */
    private PathNumberField maxAcceleration;

    /**
     * A button that generates the path.
     */
    private JButton preview;

    public GainsDialog() {
        setLayout(new GridLayout(7, 2));

        spacing = new PathNumberField(Globals.PREFS.get("SPACING", "0.075"));
        smoothWeight = new PathNumberField(Globals.PREFS.get("SMOOTH_WEIGHT", "0.6"));
        tolerance = new PathNumberField(Globals.PREFS.get("TOLERANCE", "0.6"));
        maxVelocity = new PathNumberField(Globals.PREFS.get("MAX_VELOCITY", "3.05"));
        turningConstant = new PathNumberField(Globals.PREFS.get("TURNING_CONSTANT", "3"));
        maxAcceleration = new PathNumberField(Globals.PREFS.get("MAX_ACCELERATION", "18"));

        preview = new JButton("Preview");

        add(new JLabel("Spacing: "));
        add(spacing);
        add(new JLabel("Smooth Weight: "));
        add(smoothWeight);
        add(new JLabel("Tolerance: "));
        add(tolerance);
        add(new JLabel("Max Velocity: "));
        add(maxVelocity);
        add(new JLabel("Turning Constant: "));
        add(turningConstant);
        add(new JLabel("Max Acceleration: "));
        add(maxAcceleration);
        add(preview);
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

        dialog.preview.addActionListener(actionEvent -> frame.dispose());

        frame.setModal(true);
        frame.setContentPane(dialog);
        frame.setTitle("Gains | Spikes Path Drawing Tool");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(context);
        frame.setVisible(true);

        Globals.PREFS.put("SPACING", dialog.spacing.getText());
        Globals.PREFS.put("SMOOTH_WEIGHT", dialog.smoothWeight.getText());
        Globals.PREFS.put("TOLERANCE", dialog.tolerance.getText());
        Globals.PREFS.put("MAX_VELOCITY", dialog.maxVelocity.getText());
        Globals.PREFS.put("TURNING_CONSTANT", dialog.turningConstant.getText());
        Globals.PREFS.put("MAX_ACCELERATION", dialog.maxAcceleration.getText());

        return new Gains(dialog.spacing.getNumber(), dialog.smoothWeight.getNumber(), dialog.tolerance.getNumber(),
                dialog.maxVelocity.getNumber(), dialog.turningConstant.getNumber(), dialog.maxAcceleration.getNumber());
    }
}
