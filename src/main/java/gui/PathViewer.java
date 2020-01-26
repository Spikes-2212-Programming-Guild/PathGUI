package gui;

import com.spikes2212.path.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PathViewer extends JPanel {
    private Path path;

    private double xoffset;
    private double yoffset;

    public PathViewer(java.nio.file.Path filepath, double xoffset, double yoffset) {
        path = PathIO.read(filepath);
        setSize(Constants.FIELD_WIDTH, Constants.FIELD_HEIGHT);
        this.xoffset = xoffset;
        this.yoffset = yoffset;
    }

    public PathViewer(java.nio.file.Path filepath) {
        this(filepath, 0, 0);
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
            if(prev != null)
                g.drawLine(xpx(prev), ypx(prev), xpx(waypoint), ypx(waypoint));

            prev = waypoint;
        }
    }

    private int xpx(Waypoint waypoint) {
        return (int)(Constants.M_TO_CM * (waypoint.getX() + xoffset));
    }

    private int ypx(Waypoint waypoint) {
        return Constants.FIELD_WIDTH - (int)(Constants.M_TO_CM * (waypoint.getY() + yoffset));
    }
}
