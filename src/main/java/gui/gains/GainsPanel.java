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
public class GainsPanel extends JPanel {
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
     * A button that generates the path.
     */
    private JButton preview;

    public GainsPanel() {
        setLayout(new GridLayout(7, 2));

        spacing = new PathNumberField(Globals.PREFS.getDouble("SPACING", 0.075));
        smoothWeight = new PathNumberField(Globals.PREFS.getDouble("SMOOTH_WEIGHT", 0.6));
        tolerance = new PathNumberField(Globals.PREFS.getDouble("TOLERANCE", 0.6));
        maxVelocity = new PathNumberField(Globals.PREFS.getDouble("MAX_VELOCITY", 3.05));
        turningConstant = new PathNumberField(Globals.PREFS.getDouble("TURNING_CONSTANT", 3));
        maxAcceleration = new PathNumberField(Globals.PREFS.getDouble("MAX_ACCELERATION", 18));

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
        GainsPanel dialog = new GainsPanel();
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

        Globals.PREFS.putDouble("SPACING", dialog.spacing.getNumber());
        Globals.PREFS.putDouble("SMOOTH_WEIGHT", dialog.smoothWeight.getNumber());
        Globals.PREFS.putDouble("TOLERANCE", dialog.tolerance.getNumber());
        Globals.PREFS.putDouble("MAX_VELOCITY", dialog.maxVelocity.getNumber());
        Globals.PREFS.putDouble("TURNING_CONSTANT", dialog.turningConstant.getNumber());
        Globals.PREFS.putDouble("MAX_ACCELERATION", dialog.maxAcceleration.getNumber());

        return new Gains(dialog.spacing.getNumber(), dialog.smoothWeight.getNumber(), dialog.tolerance.getNumber(),
                dialog.maxVelocity.getNumber(), dialog.turningConstant.getNumber(), dialog.maxAcceleration.getNumber());
    }
}
