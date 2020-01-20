package gui;

import com.spikes2212.path.Path;
import com.spikes2212.path.Waypoint;
import gui.constants.Constants;
import gui.constants.ConstantsDialog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class PathMaker extends JPanel implements Connectible {
    private List<Waypoint> path;

    public PathMaker() {
        path = new LinkedList<>();
        setSize(799, 770);
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

        g.setColor(Color.RED);
        try {
            ((Graphics2D)g).setStroke(new BasicStroke(4));
        } catch(Exception ignored) {
            ignored.printStackTrace();
        }

        Waypoint prev = null;

        for(Waypoint waypoint : path) {
            g.fillOval(xpx(waypoint) - 4, ypx(waypoint) - 4, 8, 8);

            if(prev != null)
                g.drawLine(xpx(prev), ypx(prev), xpx(waypoint), ypx(waypoint));
            prev = waypoint;
        }
    }

    private int xpx(Waypoint waypoint) {
        return (int)(100 * waypoint.getX());
    }

    private int ypx(Waypoint waypoint) {
        return 799 - (int)(100 * waypoint.getY());
    }

    @Override
    public void add(Waypoint waypoint) {
        path.add(waypoint);
    }

    @Override
    public void removeLast() {
        path.remove(path.size() - 1);
    }

    @Override
    public void update() {
        repaint();
    }

    public void exportPath(java.nio.file.Path filepath) {
        Constants constants = ConstantsDialog.showDialog();

        Path spikesPath = new Path(constants.getSpacing(), constants.getSmoothWeight(), constants.getTolerance(),
                constants.getMaxVelocity(), constants.getTurningConstant(), constants.getMaxAcceleration(),
                path.toArray(new Waypoint[]{}));
        spikesPath.exportToCSV(filepath);
    }

    public void rotatePath(double radians) {
        List<Waypoint> temp = new LinkedList<>();
        double xoff = path.get(0).getX();
        double yoff = path.get(0).getY();

        for(Waypoint waypoint : path) {
            double x = (waypoint.getX() - xoff) * Math.cos(radians) - (waypoint.getY() - yoff) * Math.sin(radians);
            double y = (waypoint.getX() - xoff) * Math.sin(radians) + (waypoint.getY() - yoff) * Math.cos(radians);

            temp.add(new Waypoint(x + xoff, y + yoff));
        }

        path = temp;
        repaint();
    }

    public void alignPath() {
        Waypoint first = path.get(0);
        Waypoint second = path.get(1);

        double m = (first.getY() - second.getY()) / (first.getX() - second.getX());
        double angle = Math.atan(m);

        rotatePath((Math.PI / 2) - angle);
    }

    public void mirrorPath() {
        List<Waypoint> temp = new LinkedList<>();

        for(Waypoint waypoint : path) {
            temp.add(new Waypoint(7.99 - waypoint.getX(), waypoint.getY()));
        }

        path = temp;
        repaint();
    }

    public void moveToOrigin() {
        List<Waypoint> temp = new LinkedList<>();
        double xoff = path.get(0).getX();
        double yoff = path.get(0).getY();

        for(Waypoint waypoint : path) {
            temp.add(new Waypoint(waypoint.getX() - xoff, waypoint.getY() - yoff));
        }

        path = temp;
        repaint();
    }
}
