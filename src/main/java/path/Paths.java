package path;

import java.util.Arrays;
import java.util.List;

/**
 * This class contains algorithms that operate on paths.
 *
 * @author T
 */
public class Paths {
    /**
     * Rotates the path by an arbitrary amount.
     *
     * @param rad the amount by which to rotate the path, in radians
     */
    public static void rotate(double rad, List<Waypoint> path) {
        double xOffset = path.get(0).getX();
        double yOffset = path.get(0).getY();

        for(Waypoint point : path)
            point.setCoordinates((point.getX() - xOffset) * Math.cos(rad) - (point.getY() - yOffset) * Math.sin(rad),
                    (point.getX() - xOffset) * Math.sin(rad) + (point.getY() - yOffset) * Math.cos(rad));
    }

    /**
     * Generates the path.
     *
     * @param gains the path generation gains
     */
    public static void generate(Gains gains, List<Waypoint> path) {
        fill(gains.getSpacing(), path);
        smooth(gains.getSmoothWeight(), gains.getTolerance(), path);
        calculateDistances(path);
        calculateCurvatures(path);
        calculateMaxVelocities(gains.getMaxVelocity(), gains.getTurningConstant(), path);
        smoothVelocities(gains.getMaxAcceleration(), path);
    }

    private static void fill(double spacing, List<Waypoint> path) {
        for(int i = 0; i < path.size() - 1; i++) {
            Waypoint first = path.get(i);
            double length = first.distance(path.get(i + 1));
            Waypoint vector = new Waypoint((path.get(i + 1).getX() - path.get(i).getX()) * (spacing / length),
                    (path.get(i + 1).getY() - path.get(i).getY()) * (spacing / length));
            for(int j = 0; j < (int)(length / spacing); j++, i++)
                path.add(i + 1, new Waypoint(first.getX() + vector.getX() * (j + 1),
                        first.getY() + vector.getY() * (j + 1)));
        }
    }

    private static void smooth(double smoothWeight, double tolerance, List<Waypoint> path) {
        double dataWeight = 1 - smoothWeight;
        double[][] newPath = new double[path.size()][2];
        for(int i = 0; i < path.size(); i++)
            newPath[i] = path.get(i).toArray();
        double[][] ogPath = Arrays.copyOf(newPath, newPath.length);
        double change = tolerance;
        while(change >= tolerance) {
            change = 0;
            for(int i = 1; i < newPath.length - 1; i++) {
                for(int j = 0; j < newPath[i].length; j++) {
                    double aux = newPath[i][j];
                    newPath[i][j] += dataWeight * (ogPath[i][j] - newPath[i][j])
                            + smoothWeight * (newPath[i - 1][j] + newPath[i + 1][j] - 2 * newPath[i][j]);
                    change += Math.abs(aux - newPath[i][j]);
                }
            }
        }

        for(int i = 0; i < newPath.length; i++)
            path.set(i, new Waypoint(newPath[i][0], newPath[i][1]));
    }

    private static void calculateDistances(List<Waypoint> path) {
        double previousDistance = 0;
        path.get(0).setD(0);
        for(int i = 1; i < path.size(); i++)
            path.get(i).setD(previousDistance += path.get(i).distance(path.get(i - 1)));
    }

    private static void calculateCurvatures(List<Waypoint> path) {
        for(int i = 1; i < path.size() - 1; i++) {
            double x1 = path.get(i).getX();
            double y1 = path.get(i).getY();
            double x2 = path.get(i - 1).getX();
            double y2 = path.get(i - 1).getY();
            double x3 = path.get(i + 1).getX();
            double y3 = path.get(i + 1).getY();
            if(x1 == x2) x2 += 0.000001;
            if(y1 == y2 && y1 == y3) y2 += 0.000001;
            double k1 = 0.5 * (x1 * x1 + y1 * y1 - x2 * x2 - y2 * y2) / (x1 - x2);
            double k2 = (y1 - y2) / (x1 - x2);
            double b = 0.5 * (x2 * x2 - 2 * x2 * k1 + y2 * y2 - x3 * x3 + 2 * x3 * k1 - y3 * y3) / (x3 * k2 - y3 + y2 - x2 * k2);
            double a = k1 - k2 * b;
            double r = Math.sqrt((x1 - a) * (x1 - a) + (y1 - b) * (y1 - b));
            path.get(i).setCurvature(1 / r);
        }
    }

    private static void calculateMaxVelocities(double maxVelocity, double turningConstant, List<Waypoint> path) {
        for(Waypoint p : path)
            p.setV(Math.min(maxVelocity, turningConstant / p.getCurvature()));
    }

    private static void smoothVelocities(double maxAcceleration, List<Waypoint> path) {
        path.get(path.size() - 1).setV(0);
        for(int i = path.size() - 2; i >= 0; i--)
            path.get(i).setV(Math.min(path.get(i).getV(),
                    Math.sqrt(Math.pow(path.get(i + 1).getV(), 2) + 2 * maxAcceleration * path.get(i).distance(path.get(i + 1)))));
    }
}
