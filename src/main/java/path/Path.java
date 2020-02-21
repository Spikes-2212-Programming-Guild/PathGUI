package path;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class represents a path.
 *
 * @author T
 */
public class Path extends LinkedList<Waypoint> {
    public Path() {
        super();
    }

    /**
     * Generates the path.
     *
     * @param gains the path generation gains
     */
    public void generate(Gains gains) {
        fill(gains.getSpacing());
        smooth(gains.getSmoothWeight(), gains.getTolerance());
        calculateDistances();
        calculateCurvatures();
        calculateMaxVelocities(gains.getMaxVelocity(), gains.getTurningConstant());
        smoothVelocities(gains.getMaxAcceleration());
    }

    private void fill(double spacing) {
        for(int i = 0; i < size() - 1; i++) {
            Waypoint startPoint = get(i);
            double length = get(i).distance(get(i + 1));
            int pointsThatFit = (int)(length / spacing);
            Waypoint vector = new Waypoint((get(i + 1).getX() - get(i).getX()) * (spacing / length),
                    (get(i + 1).getY() - get(i).getY()) * (spacing / length));
            for(int j = 0; j < pointsThatFit; j++, i++) {
                add(i + 1, new Waypoint(
                        startPoint.getX() + vector.getX() * (j + 1),
                        startPoint.getY() + vector.getY() * (j + 1)
                ));
            }
        }
    }

    private void smooth(double smooth_weight, double tolerance) {
        double data_weight = 1 - smooth_weight;
        double[][] path = new double[size()][2];
        for(int i = 0; i < size(); i++) {
            path[i] = get(i).toArray();
        }
        double[][] ogPath = Arrays.copyOf(path, path.length);
        double change = tolerance;
        while(change >= tolerance) {
            change = 0;
            for(int i = 1; i < path.length - 1; i++) {
                for(int j = 0; j < path[i].length; j++) {
                    double aux = path[i][j];
                    path[i][j] += data_weight * (ogPath[i][j] - path[i][j])
                            + smooth_weight * (path[i - 1][j] + path[i + 1][j] - 2 * path[i][j]);
                    change += Math.abs(aux - path[i][j]);
                }
            }
        }

        for(int i = 0; i < path.length; i++) {
            set(i, new Waypoint(path[i][0], path[i][1]));
        }
    }

    private void calculateDistances() {
        double previousDistance = 0;
        get(0).setD(0);
        for(int i = 1; i < size(); i++) {
            previousDistance += get(i).distance(get(i - 1));
            get(i).setD(previousDistance);
        }
    }

    private void calculateCurvatures() {
        for(int i = 1; i < size() - 1; i++) {
            double x1 = get(i).getX();
            double y1 = get(i).getY();
            double x2 = get(i - 1).getX();
            double y2 = get(i - 1).getY();
            double x3 = get(i + 1).getX();
            double y3 = get(i + 1).getY();
            if(x1 == x2) x2 += 0.000001;
            double k1 = 0.5 * (x1 * x1 + y1 * y1 - x2 * x2 - y2 * y2) / (x1 - x2);
            double k2 = (y1 - y2) / (x1 - x2);
            double b = 0.5 * (x2 * x2 - 2 * x2 * k1 + y2 * y2 - x3 * x3 + 2 * x3 * k1 - y3 * y3) / (x3 * k2 - y3 + y2 - x2 * k2);
            double a = k1 - k2 * b;
            double r = Math.sqrt((x1 - a) * (x1 - a) + (y1 - b) * (y1 - b));
            get(i).setCurvature(1 / r);
        }
    }

    private void calculateMaxVelocities(double maxVelocity, double k) {
        for(Waypoint p : this) {
            p.setV(Math.min(maxVelocity, k / p.getCurvature()));
        }
    }

    private void smoothVelocities(double maxAcceleration) {
        get(size() - 1).setV(0);
        for(int i = size() - 2; i >= 0; i--) {
            double distance;
            distance = get(i).distance(get(i + 1));
            get(i).setV(Math.min(get(i).getV(),
                    Math.sqrt(Math.pow(get(i + 1).getV(), 2) + 2 * maxAcceleration * distance)));
        }
    }
}
