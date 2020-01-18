package gui;

import com.spikes2212.path.Waypoint;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class PathMaker extends JPanel implements Connectible {
    private List<Waypoint> path;

    public PathMaker() {
        path = new LinkedList<>();
        setSize(1598, 821);
        addMouseListener(new ConnectionListener(this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Waypoint prev = null;

        for(Waypoint waypoint : path) {
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
}
