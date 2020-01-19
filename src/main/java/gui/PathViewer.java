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

    public PathViewer(java.nio.file.Path filepath) {
        path = Path.importFromCSV(filepath);
        setSize(721, 694);
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

        Waypoint prev = null;

        for(Waypoint waypoint : path.getPoints()) {
            if(prev != null)
                g.drawLine(xpx(prev), ypx(prev), xpx(waypoint), ypx(waypoint));

            prev = waypoint;
        }
    }

    private int xpx(Waypoint waypoint) {
        return (int)(100 * waypoint.getX());
    }

    private int ypx(Waypoint waypoint) {
        return (int)(100 * waypoint.getY());
    }
}
