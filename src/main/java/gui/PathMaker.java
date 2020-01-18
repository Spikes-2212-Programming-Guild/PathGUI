package gui;

import com.spikes2212.path.Path;
import com.spikes2212.path.Waypoint;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class PathMaker extends JPanel implements Connectible {
    private List<Waypoint> path;

    public PathMaker() {
        path = new LinkedList<>();
        setSize(821, 1598 / 2);
        addMouseListener(new ConnectionListener(this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Waypoint prev = null;

        for(Waypoint waypoint : path) {
            g.fillOval(xpx(waypoint) - 2, ypx(waypoint) - 2, 4, 4);

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

    @Override
    public void add(Waypoint waypoint) {
        path.add(waypoint);
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
}
