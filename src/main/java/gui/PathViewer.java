package gui;

import com.spikes2212.path.Path;
import com.spikes2212.path.Waypoint;

import javax.swing.*;
import java.awt.*;

public class PathViewer extends JPanel {
    Path path;

    public PathViewer(java.nio.file.Path filepath) {
        path = Path.importFromCSV(filepath);
        setSize(1598, 821);
    }

    @Override
    protected void paintComponent(Graphics g) {
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
        return (int)(100 * waypoint.getX());
    }
}
