package gui.pane;

import gui.GUI;
import gui.Globals;
import gui.bars.PathToolBar;
import path.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    public PathPane(GUI gui) {
        super(new BorderLayout());
        pathManipulator = new PathManipulator();
        setPreferredSize(new Dimension(Globals.FIELD_WIDTH, Globals.FIELD_HEIGHT));
        toolBar = new PathToolBar(gui);
        add(toolBar, BorderLayout.PAGE_END);
        addMouseListener(new AddAdapter(this));
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            BufferedImage img = ImageIO.read(getClass().getResource("/res/HalfField.png"));
            g.drawImage(img, 0, 0, null);
        } catch(IOException ioe) {
            JOptionPane.showMessageDialog(this, "Failed to load game field image.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        ((Graphics2D)g).setStroke(new BasicStroke(Globals.PATH_WIDTH));

        Waypoint prev = null;
        int radius = pathManipulator.isGenerated() ? Globals.GENERATED_RADIUS : Globals.POINT_RADIUS;
        for(Waypoint waypoint : pathManipulator.getPoints()) {
            g.setColor(waypoint == selected ? Globals.SELECTION_COLOR : Globals.POINT_COLOR);
            g.fillOval(xpx(waypoint) - radius, ypx(waypoint) - radius, radius * 2, radius * 2);

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
        for(Waypoint waypoint : pathManipulator.getPoints()) {
            int radius = pathManipulator.isGenerated() ? Globals.GENERATED_RADIUS : Globals.POINT_RADIUS;
            if(Math.abs(waypoint.getX() - x) < radius * Globals.CM_TO_M
                    && Math.abs(waypoint.getY() - y) < radius * Globals.CM_TO_M) {
                selected = waypoint;
                toolBar.setCoordinates(waypoint.getX(), waypoint.getY());
                repaint();
                return true;
            }
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
