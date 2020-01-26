package gui;

import com.spikes2212.path.*;
import gui.constants.Constants;
import gui.constants.ConstantsDialog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PathMaker extends JPanel implements Connectible {
    private Path path;

    public PathMaker() {
        path = new Path();
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
        }

        Waypoint prev = null;

        for(Waypoint waypoint : path.getPoints()) {
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

    public Waypoint getFirst() {
        return path.get(0);
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

    public void exportPath(java.nio.file.Path filepath, GUI gui) {
        Constants constants = ConstantsDialog.showDialog(gui);

        path.generate(constants.getSpacing(), constants.getSmoothWeight(), constants.getTolerance(),
                constants.getMaxVelocity(), constants.getTurningConstant(), constants.getMaxAcceleration());
        PathIO.write(filepath, path);
    }

    public void rotatePath(double radians) {
        double xoff = path.get(0).getX();
        double yoff = path.get(0).getY();

        for(int i = 0; i < path.size(); i++) {
            double x = (path.get(i).getX() - xoff) * Math.cos(radians) - (path.get(i).getY() - yoff) * Math.sin(radians);
            double y = (path.get(i).getX() - xoff) * Math.sin(radians) + (path.get(i).getY() - yoff) * Math.cos(radians);

            path.set(i, new Waypoint(x + xoff, y + yoff));
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
        for(int i = 0; i < path.size(); i++) {
            path.set(i, new Waypoint(7.99 - path.get(i).getX(), path.get(i).getY()));
        }

        repaint();
    }

    public void moveToOrigin() {
        double xoff = path.get(0).getX();
        double yoff = path.get(0).getY();

        for(int i = 0; i < path.size(); i++) {
            path.set(i, new Waypoint(path.get(i).getX() - xoff, path.get(i).getY() - yoff));
        }

        repaint();
    }
}
