package path;

import gui.Globals;

import java.util.List;
import java.util.Stack;

/**
 * Holds and manipulates a path.
 *
 * @author Eran Goldstein
 */
public class PathManipulator {
    /**
     * The path manipulated by this PathManipulator.
     */
    private Path path;

    /**
     * The old versions of the path, before generation.
     */
    private Stack<Path> oldPaths;

    public PathManipulator() {
        this.path = new Path();
        oldPaths = new Stack<>();
    }

    public PathManipulator(PathManipulator pathManipulator) {
        this.path = (Path)pathManipulator.path.clone();
        this.oldPaths = new Stack<>();
    }

    /**
     * Adds a {@link Waypoint} to the path.
     *
     * @param waypoint the {@link Waypoint} added
     */
    public void add(Waypoint waypoint) {
        path.add(waypoint);
    }

    /**
     * Removes the last point from the path.
     */
    public void removeLast() {
        path.removeLast();
    }

    /**
     * Reverts to before the last time the path was generated.
     */
    public void ungeneratePath() {
        path = oldPaths.pop();
    }

    /**
     * Generate the path.
     *
     * @param gains the gains object containing the gains for the path generation
     */
    public void generatePath(Gains gains) {
        if(!path.isEmpty()) {
            oldPaths.push((Path)path.clone());
            path.generate(gains);
        }
    }

    /**
     * Imports a path from a csv file.
     *
     * @param filepath the path of the file to import
     */
    public void importPath(java.nio.file.Path filepath) {
        path = PathIO.read(filepath);
        oldPaths = new Stack<>();
    }

    /**
     * Save the path to a file.
     *
     * @param filepath the path of the file.
     */
    public void savePath(java.nio.file.Path filepath) {
        PathIO.write(filepath, path);
        oldPaths = new Stack<>();
    }

    /**
     * Rotate the path by an arbitrary amount.
     *
     * @param radians the amount by which to rotate the path
     */
    private void rotatePath(double radians) {
        double xoff = path.getFirst().getX();
        double yoff = path.getFirst().getY();

        for(Waypoint waypoint : path) {
            double x = (waypoint.getX() - xoff) * Math.cos(radians) - (waypoint.getY() - yoff) * Math.sin(radians);
            double y = (waypoint.getX() - xoff) * Math.sin(radians) + (waypoint.getY() - yoff) * Math.cos(radians);

            waypoint.setCoordinates(x, y);
        }
    }

    /**
     * Align the path so that the robot is facing forward at the beginning of it.
     */
    public void alignPath() {
        Waypoint first = path.getFirst();
        Waypoint second = path.get(1);

        double m = (first.getY() - second.getY()) / (first.getX() - second.getX());
        double angle = Math.atan(m);

        rotatePath((Math.PI / 2) - angle);

        if(second.getY() < first.getY())
            rotatePath(Math.PI);
    }

    /**
     * Mirror the path.
     */
    public void mirrorPath() {
        for(Waypoint waypoint : path)
            waypoint.setCoordinates(Globals.FIELD_WIDTH * Globals.CM_TO_M - waypoint.getX(), waypoint.getY());
    }

    /**
     * Move the path to the origin.
     */
    public void moveToOrigin() {
        for(Waypoint waypoint : path)
            waypoint.setCoordinates(waypoint.getX() - path.getFirst().getX(),
                    waypoint.getY() - path.getFirst().getY());
    }

    public boolean contains(Waypoint waypoint) {
        return path.contains(waypoint);
    }

    public List<Waypoint> getPoints() {
        return path;
    }

    public boolean isGenerated() {
        return !oldPaths.isEmpty();
    }
}
