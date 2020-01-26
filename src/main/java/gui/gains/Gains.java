package gui.gains;

public class Gains {
    private double spacing;
    private double smoothWeight;
    private double tolerance;
    private double maxVelocity;
    private double turningConstant;
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
}
