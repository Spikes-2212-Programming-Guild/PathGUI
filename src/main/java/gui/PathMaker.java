package gui;

import com.spikes2212.path.*;
import gui.gains.Gains;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class PathMaker extends JPanel implements Addable {
    private Path path;
    private Stack<Path> ungeneratedPaths = new Stack<>();
    private Waypoint selected;
    private PathToolBar toolBar;

    public PathMaker() {
        super(new BorderLayout());
        path = new Path();
        setPreferredSize(new Dimension(Globals.FIELD_WIDTH, Globals.FIELD_HEIGHT));
        toolBar = new PathToolBar(this);
        add(toolBar, BorderLayout.PAGE_END);
        addMouseListener(new AddListener(this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage img;
        try {
            img = ImageIO.read(new File("src/main/resources/HalfField.png"));
            g.drawImage(img, 0, 0, null);
        } catch(IOException ignored) {
        }

        try {
            ((Graphics2D)g).setStroke(new BasicStroke(Globals.PATH_WIDTH));
        } catch(Exception ignored) {
        }

        Waypoint prev = null;

        for(Waypoint waypoint : path.getPoints()) {
            g.setColor(waypoint == selected ? Globals.POINT_COLOR : Globals.SELECTION_COLOR);
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
        toolBar.setCoordinates((int)(waypoint.getX() * Globals.M_TO_CM),
                (int)(waypoint.getY() * Globals.M_TO_CM));
        repaint();
    }

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
                toolBar.setCoordinates((int)(waypoint.getX() * Globals.M_TO_CM),
                        (int)(waypoint.getY() * Globals.M_TO_CM));
                repaint();
                return true;
            }

        return false;
    }

    public void ungeneratePath() {
        path = ungeneratedPaths.pop();
        repaint();
    }

    public void newPath() {
        path = new Path();
    }

    public void importPath(java.nio.file.Path filepath) {
        path = PathIO.read(filepath);
    }

    public void generatePath(GUI gui, Gains gains) {
        Path ungeneratedPath = new Path();
        for(Waypoint waypoint : path.getPoints())
            ungeneratedPath.add(waypoint);
        ungeneratedPaths.push(ungeneratedPath);

        path.generate(gains.getSpacing(), gains.getSmoothWeight(), gains.getTolerance(),
                gains.getMaxVelocity(), gains.getTurningConstant(), gains.getMaxAcceleration());

        repaint();
    }

    public void savePath(java.nio.file.Path filepath) {
        PathIO.write(filepath, path);
    }

    public void rotatePath(double radians) {
        double xoff = path.get(0).getX();
        double yoff = path.get(0).getY();

        for(Waypoint waypoint : path.getPoints()) {
            double x = (waypoint.getX() - xoff) * Math.cos(radians) - (waypoint.getY() - yoff) * Math.sin(radians);
            double y = (waypoint.getX() - xoff) * Math.sin(radians) + (waypoint.getY() - yoff) * Math.cos(radians);

            waypoint.setCoordinates(x, y);
        }

        repaint();
    }

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

    public void mirrorPath() {
        for(Waypoint waypoint : path.getPoints()) {
            waypoint.setCoordinates(Globals.FIELD_WIDTH * Globals.CM_TO_M - waypoint.getX(), waypoint.getY());
        }

        repaint();
    }

    public void moveToOrigin() {
        double xoff = path.get(0).getX();
        double yoff = path.get(0).getY();

        for(Waypoint waypoint : path.getPoints()) {
            waypoint.setCoordinates(waypoint.getX() - xoff, waypoint.getY() - yoff);
        }

        repaint();
    }

    public void moveSelected(double x, double y) {
        selected.setCoordinates(x, y);
        repaint();
    }
}
