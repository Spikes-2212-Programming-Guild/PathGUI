package path;

import gui.Globals;

import java.util.*;

/**
 * Holds and manipulates a path.
 *
 * @author Eran Goldstein
 */
public class PathManipulator {
    /**
     * The path manipulated by this PathManipulator.
     */
    private List<Waypoint> path;

    /**
     * The old versions of the path, before generation.
     */
    private Stack<List<Waypoint>> oldPaths;

    public PathManipulator() {
        this.path = new LinkedList<>();
        oldPaths = new Stack<>();
    }

    public PathManipulator(PathManipulator pathManipulator) {
        this.path = new LinkedList<>(pathManipulator.path);
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
        path.remove(path.size() - 1);
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
            oldPaths.push(new LinkedList<>(path));
            Paths.generate(gains, path);
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
     * Align the path so that the robot is facing forward at the beginning of it.
     */
    public void alignPath() {
        Waypoint first = path.get(0);
        Waypoint second = path.get(1);

        double m = (first.getY() - second.getY()) / (first.getX() - second.getX());
        double angle = Math.atan(m);

        Paths.rotate((Math.PI / 2) - angle, path);

        if(second.getY() < first.getY())
            Paths.rotate(Math.PI, path);
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
            waypoint.setCoordinates(waypoint.getX() - path.get(0).getX(),
                    waypoint.getY() - path.get(0).getY());
    }

    public boolean contains(Waypoint waypoint) {
        return path.contains(waypoint);
    }

    public List<Waypoint> getPath() {
        return path;
    }

    public boolean isGenerated() {
        return !oldPaths.isEmpty();
    }
}
