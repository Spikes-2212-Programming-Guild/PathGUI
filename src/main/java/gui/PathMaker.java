package gui;

import gui.gains.Gains;
import gui.toolbar.PathToolBar;
import path.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

/**
 * The panel that the user clicks on to add points to a path.
 *
 * @author Eran Goldstein
 */
public class PathMaker extends JPanel implements Addable {
    /**
     * The path the points get added to.
     */
    private Path path;

    /**
     * The old versions of the path, before generation.
     */
    private Stack<Path> oldPaths = new Stack<>();

    /**
     * The path point currently selected by the user.
     */
    private Waypoint selected;

    /**
     * The {@link PathToolBar} in to the panel.
     */
    private PathToolBar toolBar;

    public PathMaker() {
        super(new BorderLayout());
        path = new Path();
        setPreferredSize(new Dimension(Globals.FIELD_WIDTH, Globals.FIELD_HEIGHT));
        toolBar = new PathToolBar(this);
        add(toolBar, BorderLayout.PAGE_END);
        addMouseListener(new AddAdapter(this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage img;
        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/HalfField.png")));
            g.drawImage(img, 0, 0, null);
        } catch(IOException ignored) {
            // If the field image cannot be found, do not draw a background.
        }

        ((Graphics2D)g).setStroke(new BasicStroke(Globals.PATH_WIDTH));

        Waypoint prev = null;

        for(Waypoint waypoint : path.getPoints()) {
            g.setColor(waypoint == selected ? Globals.SELECTION_COLOR : Globals.POINT_COLOR);
            g.fillOval(xpx(waypoint) - Globals.POINT_RADIUS, ypx(waypoint) - Globals.POINT_RADIUS,
                    Globals.POINT_RADIUS * 2, Globals.POINT_RADIUS * 2);

            g.setColor(Globals.PATH_COLOR);
            if(prev != null)
                g.drawLine(xpx(prev), ypx(prev), xpx(waypoint), ypx(waypoint));
            prev = waypoint;
        }
    }

    private int xpx(Waypoint waypoint) {
        return (int)(Globals.M_TO_CM * waypoint.getX());
    }

    private int ypx(Waypoint waypoint) {
        return Globals.FIELD_WIDTH - (int)(Globals.M_TO_CM * waypoint.getY());
    }

    @Override
    public void add(Waypoint waypoint) {
        path.add(waypoint);
        selected = waypoint;
        toolBar.setCoordinates(waypoint.getX(), waypoint.getY());
        repaint();
    }

    /**
     * Removes the last point from the path.
     */
    public void removeLast() {
        path.remove(path.size() - 1);
        repaint();
    }

    @Override
    public boolean select(double x, double y) {
        for(Waypoint waypoint : path.getPoints())
            if(Math.abs(waypoint.getX() - x) < Globals.POINT_RADIUS * Globals.CM_TO_M
                    && Math.abs(waypoint.getY() - y) < Globals.POINT_RADIUS * Globals.CM_TO_M) {
                selected = waypoint;
                toolBar.setCoordinates(waypoint.getX(), waypoint.getY());
                repaint();
                return true;
            }

        return false;
    }

    /**
     * Reverts to before the last time the path was generated.
     */
    public void ungeneratePath() {
        path = oldPaths.pop();
        repaint();
    }

    /**
     * Creates a new path, losing all unsaved changes to the old one.
     */
    public void newPath() {
        path = new Path();
    }

    /**
     * Imports a path from a csv file.
     *
     * @param filepath the path of the file to import
     */
    public void importPath(java.nio.file.Path filepath) {
        path = PathIO.read(filepath);
    }

    /**
     * Generate the path.
     *
     * @param gains the gains object containing the gains for the path generation
     */
    public void generatePath(Gains gains) {
        Path oldPath = new Path();
        for(Waypoint waypoint : path.getPoints())
            oldPath.add(waypoint);
        oldPaths.push(oldPath);

        path.generate(gains.getSpacing(), gains.getSmoothWeight(), gains.getTolerance(),
                gains.getMaxVelocity(), gains.getTurningConstant(), gains.getMaxAcceleration());

        repaint();
    }

    /**
     * Save the path to a file.
     *
     * @param filepath the path of the file.
     */
    public void savePath(java.nio.file.Path filepath) {
        PathIO.write(filepath, path);
    }

    /**
     * Rotate the path by an arbitrary amount.
     *
     * @param radians the amount by which to rotate the path
     */
    private void rotatePath(double radians) {
        double xoff = path.get(0).getX();
        double yoff = path.get(0).getY();

        for(Waypoint waypoint : path.getPoints()) {
            double x = (waypoint.getX() - xoff) * Math.cos(radians) - (waypoint.getY() - yoff) * Math.sin(radians);
            double y = (waypoint.getX() - xoff) * Math.sin(radians) + (waypoint.getY() - yoff) * Math.cos(radians);

            waypoint.setCoordinates(x, y);
        }

        repaint();
    }

    /**
     * Align the path so that the robot is facing forward at the beginning of it.
     */
    public void alignPath() {
        Waypoint first = path.get(0);
        Waypoint second = path.get(1);

        double m = (first.getY() - second.getY()) / (first.getX() - second.getX());
        double angle = Math.atan(m);

        rotatePath((Math.PI / 2) - angle);

        first = path.get(0);
        second = path.get(1);
        if(second.getY() < first.getY()) {
            rotatePath(Math.PI);
        }
    }

    /**
     * Mirror the path.
     */
    public void mirrorPath() {
        for(Waypoint waypoint : path.getPoints()) {
            waypoint.setCoordinates(Globals.FIELD_WIDTH * Globals.CM_TO_M - waypoint.getX(), waypoint.getY());
        }

        repaint();
    }

    /**
     * Move the path to the origin.
     */
    public void moveToOrigin() {
        double xoff = path.get(0).getX();
        double yoff = path.get(0).getY();

        for(Waypoint waypoint : path.getPoints()) {
            waypoint.setCoordinates(waypoint.getX() - xoff, waypoint.getY() - yoff);
        }

        repaint();
    }

    /**
     * Move the selected point to the supplied coordinates.
     *
     * @param x the x coordinate
     * @param y the x coordinate
     */
    public void moveSelected(double x, double y) {
        selected.setCoordinates(x, y);
        repaint();
    }
}
