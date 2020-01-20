package gui;

import com.spikes2212.path.Path;
import com.spikes2212.path.Waypoint;

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
        path = Path.importFromCSV(filepath);
        setSize(799, 770);
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
        return (int)(100 * (waypoint.getX() + xoffset));
    }

    private int ypx(Waypoint waypoint) {
        return 799 - (int)(100 * (waypoint.getY() + yoffset));
    }
}
