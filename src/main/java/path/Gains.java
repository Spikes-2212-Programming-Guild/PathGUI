package path;

import gui.Globals;

/**
 * The gains for the path generation algorithm.
 *
 * @author Eran Goldstein
 */
public class Gains {
    /**
     * The spacing between points on the generated path.
     */
    private double spacing;

    /**
     * How much to smooth the path.
     */
    private double smoothWeight;

    /**
     * The highest tolerable distance between the smoothed and original paths.
     */
    private double tolerance;

    /**
     * The maximum velocity of the robot.
     */
    private double maxVelocity;

    /**
     * The turning constant for the path generation algorithm.
     */
    private double turningConstant;

    /**
     * The maximum acceleration of the robot.
     */
    private double maxAcceleration;

    public Gains(double spacing, double smoothWeight, double tolerance, double maxVelocity, double turningConstant,
                 double maxAcceleration) {
        this.spacing = spacing;
        this.smoothWeight = smoothWeight;
        this.tolerance = tolerance;
        this.maxVelocity = maxVelocity;
        this.turningConstant = turningConstant;
        this.maxAcceleration = maxAcceleration;
    }

    public double getSpacing() {
        return spacing;
    }

    public double getSmoothWeight() {
        return smoothWeight;
    }

    public double getTolerance() {
        return tolerance;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }

    public double getTurningConstant() {
        return turningConstant;
    }

    public double getMaxAcceleration() {
        return maxAcceleration;
    }

    /**
     * Returns the user-saved default gains.
     *
     * @return the user-saved default gains
     */
    public static Gains getPreferences() {
        return new Gains(Globals.PREFS.getDouble("SPACING", 0.075),
                Globals.PREFS.getDouble("SMOOTH_WEIGHT", 0.6),
                Globals.PREFS.getDouble("TOLERANCE", 0.6),
                Globals.PREFS.getDouble("MAX_VELOCITY", 3.05),
                Globals.PREFS.getDouble("TURNING_CONSTANT", 3),
                Globals.PREFS.getDouble("MAX_ACCELERATION", 18));
    }
}
