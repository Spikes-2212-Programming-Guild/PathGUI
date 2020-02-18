package gui.gains;

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

    public Gains(double spacing, double smoothWeight, double tolerance, double maxVelocity, double turningConstant, double maxAcceleration) {
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
        return new Gains(Double.parseDouble(Globals.PREFS.get("SPACING", "0.075")),
                Double.parseDouble(Globals.PREFS.get("SMOOTH_WEIGHT", "0.6")),
                Double.parseDouble(Globals.PREFS.get("TOLERANCE", "0.6")),
                Double.parseDouble(Globals.PREFS.get("MAX_VELOCITY", "3.05")),
                Double.parseDouble(Globals.PREFS.get("TURNING_CONSTANT", "3")),
                Double.parseDouble(Globals.PREFS.get("MAX_ACCELERATION", "18")));
    }
}
