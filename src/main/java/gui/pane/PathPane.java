package gui.pane;

import gui.GUI;
import gui.Globals;
import gui.bars.PathToolBar;
import path.PathManipulator;
import path.Waypoint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
        toolBar.setEnabled(pathManipulator.contains(selected));

        try {
            g.drawImage(ImageIO.read(getClass().getResource("/res/field.png")), 0, 0, null);
        } catch(IOException ioe) {
            JOptionPane.showMessageDialog(this, "Failed to load game field image.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        ((Graphics2D)g).setStroke(new BasicStroke(Globals.PATH_WIDTH));

        Waypoint prev = null;
        int radius = pathManipulator.isGenerated() ? Globals.GENERATED_RADIUS : Globals.POINT_RADIUS;
        for(Waypoint waypoint : pathManipulator.getPoints()) {
            g.setColor(waypoint == selected ? Globals.SELECTION_COLOR :
                    waypoint == pathManipulator.getPoints().get(0) ? Globals.FIRST_COLOR : Globals.POINT_COLOR);
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

    @Override
    public boolean select(Waypoint waypoint) {
        for(Waypoint wp : pathManipulator.getPoints()) {
            int radius = pathManipulator.isGenerated() ? Globals.GENERATED_RADIUS : Globals.POINT_RADIUS;
            if(Math.abs(wp.getX() - waypoint.getX()) < radius * Globals.CM_TO_M
                    && Math.abs(wp.getY() - waypoint.getY()) < radius * Globals.CM_TO_M) {
                selected = wp;
                toolBar.setCoordinates(wp.getX(), wp.getY());
                repaint();

                return true;
            }
        }

        return false;
    }

    /**
     * Creates a new path, losing all unsaved changes to the old one.
     */
    public void newPath() {
        pathManipulator = new PathManipulator();
        repaint();
    }

    /**
     * Move the selected point to the supplied coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void moveSelected(double x, double y) {
        selected.setCoordinates(x, y);
        repaint();
    }

    public PathManipulator getPathManipulator() {
        return pathManipulator;
    }
}
