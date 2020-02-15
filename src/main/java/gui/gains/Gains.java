package gui.gains;

import gui.GUI;

/**
 * The gains for the path generation algorithm.
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
     * @param gui a program object that holds the user preferences
     * @return the user-saved default gains
     */
    public static Gains getPreferences(GUI gui) {
        return new Gains(Double.parseDouble(gui.getPreferences().get("SPACING", "0.075")),
                Double.parseDouble(gui.getPreferences().get("SMOOTH_WEIGHT", "0.6")),
                Double.parseDouble(gui.getPreferences().get("TOLERANCE", "0.6")),
                Double.parseDouble(gui.getPreferences().get("MAX_VELOCITY", "3.05")),
                Double.parseDouble(gui.getPreferences().get("TURNING_CONSTANT", "3")),
                Double.parseDouble(gui.getPreferences().get("MAX_ACCELERATION", "18")));
    }
}
