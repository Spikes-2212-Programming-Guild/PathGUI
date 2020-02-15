package gui;

import com.spikes2212.path.*;
import gui.gains.Gains;
import gui.gains.GainsDialog;
import gui.toolbar.PathToolBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class PathMaker extends JPanel implements Connectible {
    private Path path;
    private Stack<Path> ungeneratedPaths = new Stack<>();
    private Waypoint selected;
    private PathToolBar toolBar;

    public PathMaker() {
        super(new BorderLayout());
        path = new Path();
        setPreferredSize(new Dimension(Constants.FIELD_WIDTH, Constants.FIELD_HEIGHT));
        toolBar = new PathToolBar();
        add(toolBar, BorderLayout.PAGE_END);
        addMouseListener(new ConnectionListener(this));
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
            ((Graphics2D)g).setStroke(new BasicStroke(Constants.PATH_WIDTH));
        } catch(Exception ignored) {
        }

        Waypoint prev = null;

        for(Waypoint waypoint : path.getPoints()) {
            g.setColor(waypoint == selected ? Color.YELLOW : Color.RED);
            g.fillOval(xpx(waypoint) - Constants.POINT_RADIUS, ypx(waypoint) - Constants.POINT_RADIUS,
                    Constants.POINT_RADIUS * 2, Constants.POINT_RADIUS * 2);

            g.setColor(Color.RED);
            if(prev != null)
                g.drawLine(xpx(prev), ypx(prev), xpx(waypoint), ypx(waypoint));
            prev = waypoint;
        }
    }

    private int xpx(Waypoint waypoint) {
        return (int)(Constants.M_TO_CM * waypoint.getX());
    }

    private int ypx(Waypoint waypoint) {
        return Constants.FIELD_WIDTH - (int)(Constants.M_TO_CM * waypoint.getY());
    }

    @Override
    public void add(Waypoint waypoint) {
        path.add(waypoint);
        selected = waypoint;
        toolBar.setCoordinates((int)(waypoint.getX() * Constants.M_TO_CM),
                (int)(waypoint.getY() * Constants.M_TO_CM));
        repaint();
    }

    public void removeLast() {
        path.remove(path.size() - 1);
    }

    @Override
    public boolean select(double x, double y) {
        for(Waypoint waypoint : path.getPoints())
            if(Math.abs(waypoint.getX() - x) < Constants.POINT_RADIUS * Constants.CM_TO_M
                    && Math.abs(waypoint.getY() - y) < Constants.POINT_RADIUS * Constants.CM_TO_M) {
                selected = waypoint;
                toolBar.setCoordinates((int)(waypoint.getX() * Constants.M_TO_CM),
                        (int)(waypoint.getY() * Constants.M_TO_CM));
                repaint();
                return true;
            }

        return false;
    }

    public void ungeneratedPath() {
        path = ungeneratedPaths.pop();
    }

    public void newPath() {
        path = new Path();
    }

    public void importPath(java.nio.file.Path filepath) {
        path = PathIO.read(filepath);
    }

    public void generatePath(GUI gui) {
        Path ungeneratedPath = new Path();
        for(Waypoint waypoint : path.getPoints())
            ungeneratedPath.add(waypoint);
        ungeneratedPaths.push(ungeneratedPath);

        Gains gains = GainsDialog.showDialog(gui);

        path.generate(gains.getSpacing(), gains.getSmoothWeight(), gains.getTolerance(),
                gains.getMaxVelocity(), gains.getTurningConstant(), gains.getMaxAcceleration());
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
            waypoint.setCoordinates(Constants.FIELD_WIDTH * Constants.CM_TO_M - waypoint.getX(), waypoint.getY());
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
    }
}
