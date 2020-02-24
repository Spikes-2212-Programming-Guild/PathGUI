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
        JPanel gainsPanel = new JPanel(new GridLayout(0, 2, Globals.H_GAP, Globals.V_GAP));

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
        preview.setMnemonic('P');

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(actionEvent -> {
            returnValue = CANCEL_OPTION;
            dispose();
        });

        gainsPanel.add(new JLabel("Spacing:"));
        gainsPanel.add(spacing);
        gainsPanel.add(new JLabel("Smooth Weight:"));
        gainsPanel.add(smoothWeight);
        gainsPanel.add(new JLabel("Tolerance:"));
        gainsPanel.add(tolerance);
        gainsPanel.add(new JLabel("Max Velocity:"));
        gainsPanel.add(maxVelocity);
        gainsPanel.add(new JLabel("Turning Constant:"));
        gainsPanel.add(turningConstant);
        gainsPanel.add(new JLabel("Max Acceleration:"));
        gainsPanel.add(maxAcceleration);
        gainsPanel.add(preview);
        gainsPanel.add(cancel);

        gainsPanel.setBorder(BorderFactory
                .createEmptyBorder(Globals.V_GAP, Globals.H_GAP, Globals.V_GAP, Globals.H_GAP));
        add(gainsPanel);
    }

    public Gains getGains() {
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
        setIconImage(new ImageIcon(getClass().getResource("/res/spike.png")).getImage());
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
