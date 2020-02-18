package gui.pane;

import gui.Globals;
import gui.toolbar.PathToolBar;
import path.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * The panel that the user clicks on to add points to a path.
 *
 * @author Eran Goldstein
 */
public class PathPane extends JPanel implements Addable {
    /**
     * The path manipulator object.
     */
    private PathManipulator pathManipulator;

    /**
     * The path point currently selected by the user.
     */
    private Waypoint selected;

    /**
     * The {@link PathToolBar} in to the panel.
     */
    private PathToolBar toolBar;

    public PathPane() {
        super(new BorderLayout());
        pathManipulator = new PathManipulator();
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

        for(Waypoint waypoint : pathManipulator.getPoints()) {
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
        pathManipulator.add(waypoint);
        selected = waypoint;
        toolBar.setCoordinates(waypoint.getX(), waypoint.getY());
        repaint();
    }

    /**
     * Removes the last point from the path.
     */
    public void removeLast() {
        pathManipulator.removeLast();
        repaint();
    }

    @Override
    public boolean select(double x, double y) {
        for(Waypoint waypoint : pathManipulator.getPoints())
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
        pathManipulator.ungeneratePath();
        repaint();
    }

    /**
     * Creates a new path, losing all unsaved changes to the old one.
     */
    public void newPath() {
        pathManipulator.newPath();
        repaint();
    }

    /**
     * Imports a path from a csv file.
     *
     * @param filepath the path of the file to import
     */
    public void importPath(java.nio.file.Path filepath) {
        pathManipulator.importPath(filepath);
        repaint();
    }

    /**
     * Generate the path.
     *
     * @param gains the gains object containing the gains for the path generation
     */
    public void generatePath(Gains gains) {
        pathManipulator.generatePath(gains);
        repaint();
    }

    /**
     * Save the path to a file.
     *
     * @param filepath the path of the file.
     */
    public void savePath(java.nio.file.Path filepath) {
        pathManipulator.savePath(filepath);
        repaint();
    }

    /**
     * Align the path so that the robot is facing forward at the beginning of it.
     */
    public void alignPath() {
        pathManipulator.alignPath();
        repaint();
    }

    /**
     * Mirror the path.
     */
    public void mirrorPath() {
        pathManipulator.mirrorPath();
        repaint();
    }

    /**
     * Move the path to the origin.
     */
    public void moveToOrigin() {
        pathManipulator.moveToOrigin();
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