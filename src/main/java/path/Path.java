package path;

import java.util.*;

/**
 * This class represents a path.
 *
 * @author T
 */
public class Path {

    /**
     * The points on the path.
     */
    private List<Waypoint> points;

    public Path() {
        this.points = new LinkedList<>();
    }

    public void add(Waypoint waypoint) {
        points.add(waypoint);
    }

    public Waypoint get(int index) {
        return points.get(index);
    }

    public void remove(int index) {
        points.remove(index);
    }

    public int size() {
        return points.size();
    }

    public List<Waypoint> getPoints() {
        return points;
    }

    public void generate(double spacing, double smooth_weight, double tolerance,
                         double maxVelocity, double k, double maxAcceleration) {
        fill(spacing);
        smooth(smooth_weight, tolerance);
        calculateDistances();
        calculateCurvatures();
        calculateMaxVelocities(maxVelocity, k);
        smoothVelocities(maxAcceleration);
    }

    private void fill(double spacing) {
        for(int i = 0; i < points.size() - 1; i++) {
            Waypoint startPoint = points.get(i);
            double length = points.get(i).distance(points.get(i + 1));
            int pointsThatFit = (int)(length / spacing);
            Waypoint vector = new Waypoint((points.get(i + 1).getX() - points.get(i).getX()) * (spacing / length),
                    (points.get(i + 1).getY() - points.get(i).getY()) * (spacing / length));
            for(int j = 0; j < pointsThatFit; j++, i++) {
                points.add(i + 1, new Waypoint(
                        startPoint.getX() + vector.getX() * (j + 1),
                        startPoint.getY() + vector.getY() * (j + 1)
                ));
            }
        }
    }

    private void smooth(double smooth_weight, double tolerance) {
        double data_weight = 1 - smooth_weight;
        double[][] path = new double[points.size()][2];
        for(int i = 0; i < points.size(); i++) {
            path[i] = points.get(i).toArray();
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
            points.set(i, new Waypoint(path[i][0], path[i][1]));
        }
    }

    private void calculateDistances() {
        double previousDistance = 0;
        points.get(0).setD(0);
        for(int i = 1; i < points.size(); i++) {
            previousDistance += points.get(i).distance(points.get(i - 1));
            points.get(i).setD(previousDistance);
        }
    }

    private void calculateCurvatures() {
        for(int i = 1; i < points.size() - 1; i++) {
            double x1 = points.get(i).getX();
            double y1 = points.get(i).getY();
            double x2 = points.get(i - 1).getX();
            double y2 = points.get(i - 1).getY();
            double x3 = points.get(i + 1).getX();
            double y3 = points.get(i + 1).getY();
            if(x1 == x2) x2 += 0.000001;
            double k1 = 0.5 * (x1 * x1 + y1 * y1 - x2 * x2 - y2 * y2) / (x1 - x2);
            double k2 = (y1 - y2) / (x1 - x2);
            double b = 0.5 * (x2 * x2 - 2 * x2 * k1 + y2 * y2 - x3 * x3 + 2 * x3 * k1 - y3 * y3) / (x3 * k2 - y3 + y2 - x2 * k2);
            double a = k1 - k2 * b;
            double r = Math.sqrt((x1 - a) * (x1 - a) + (y1 - b) * (y1 - b));
            points.get(i).setCurvature(1 / r);
        }
    }

    private void calculateMaxVelocities(double maxVelocity, double k) {
        for(Waypoint p : points) {
            p.setV(Math.min(maxVelocity, k / p.getCurvature()));
        }
    }

    private void smoothVelocities(double maxAcceleration) {
        points.get(points.size() - 1).setV(0);
        for(int i = points.size() - 2; i >= 0; i--) {
            double distance;
            distance = points.get(i).distance(points.get(i + 1));
            points.get(i).setV(Math.min(points.get(i).getV(),
                    Math.sqrt(Math.pow(points.get(i + 1).getV(), 2) + 2 * maxAcceleration * distance)));
        }
    }
}
