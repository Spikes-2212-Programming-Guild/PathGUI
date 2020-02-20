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
public class GainsDialog extends JDialog {
    /**
     * A number field input for the spacing between points on the generated path.
     */
    private PathNumberField spacing;

    /**
     * A number field input for how much to smooth the path.
     */
    private PathNumberField smoothWeight;

    /**
     * A number field input for the highest tolerable distance between the smoothed and original paths.
     */
    private PathNumberField tolerance;

    /**
     * A number field input the maximum velocity of the robot.
     */
    private PathNumberField maxVelocity;

    /**
     * A number field input the turning constant for the path generation algorithm.
     */
    private PathNumberField turningConstant;

    /**
     * A number field input for the maximum acceleration of the robot.
     */
    private PathNumberField maxAcceleration;

    /**
     * The value returned by this dialog.
     * <p>
     * The value is one of:
     * - GainsDialog.APPROVE_OPTION
     * - GainsDialog.CANCEL_OPTION
     */
    private int returnValue = CANCEL_OPTION;

    /**
     * The return value of {@code showDialog} when the user approves.
     */
    public static int APPROVE_OPTION = 0;

    /**
     * The return value of {@code showDialog} when the user cancels.
     */
    public static int CANCEL_OPTION = 1;

    public GainsDialog() {
        setLayout(new GridLayout(7, 2));

        spacing = new PathNumberField(Globals.PREFS.getDouble("SPACING", 0.075));
        smoothWeight = new PathNumberField(Globals.PREFS.getDouble("SMOOTH_WEIGHT", 0.6));
        tolerance = new PathNumberField(Globals.PREFS.getDouble("TOLERANCE", 0.6));
        maxVelocity = new PathNumberField(Globals.PREFS.getDouble("MAX_VELOCITY", 3.05));
        turningConstant = new PathNumberField(Globals.PREFS.getDouble("TURNING_CONSTANT", 3));
        maxAcceleration = new PathNumberField(Globals.PREFS.getDouble("MAX_ACCELERATION", 18));

        JButton preview = new JButton("Preview");
        preview.addActionListener(actionEvent -> {
            returnValue = APPROVE_OPTION;
            dispose();
        });

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(actionEvent -> {
            returnValue = CANCEL_OPTION;
            dispose();
        });

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
        add(cancel);
    }

    public Gains getGains() {
        Globals.PREFS.putDouble("SPACING", spacing.getNumber());
        Globals.PREFS.putDouble("SMOOTH_WEIGHT", smoothWeight.getNumber());
        Globals.PREFS.putDouble("TOLERANCE", tolerance.getNumber());
        Globals.PREFS.putDouble("MAX_VELOCITY", maxVelocity.getNumber());
        Globals.PREFS.putDouble("TURNING_CONSTANT", turningConstant.getNumber());
        Globals.PREFS.putDouble("MAX_ACCELERATION", maxAcceleration.getNumber());

        return new Gains(spacing.getNumber(), smoothWeight.getNumber(), tolerance.getNumber(),
                maxVelocity.getNumber(), turningConstant.getNumber(), maxAcceleration.getNumber());
    }

    /**
     * Show the gains dialog.
     *
     * @param context the context for the dialog
     * @return the gains from the user input
     */
    public int showDialog(GUI context) {
        returnValue = CANCEL_OPTION;

        setModal(true);
        setTitle("Gains | Spikes Path Drawing Tool");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(context);
        setVisible(true);

        Globals.PREFS.putDouble("SPACING", spacing.getNumber());
        Globals.PREFS.putDouble("SMOOTH_WEIGHT", smoothWeight.getNumber());
        Globals.PREFS.putDouble("TOLERANCE", tolerance.getNumber());
        Globals.PREFS.putDouble("MAX_VELOCITY", maxVelocity.getNumber());
        Globals.PREFS.putDouble("TURNING_CONSTANT", turningConstant.getNumber());
        Globals.PREFS.putDouble("MAX_ACCELERATION", maxAcceleration.getNumber());

        return returnValue;
    }
}
