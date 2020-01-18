package gui;

import com.spikes2212.path.Waypoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

public class PathMaker extends JPanel implements MouseListener {
    private List<Waypoint> path;

    public PathMaker() {
        path = new LinkedList<>();
        setSize(1598, 821);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        path.add(new Waypoint(point.getX() / 100, point.getY() / 100));
        System.out.println("(" + point.getX() / 100 + ", " + point.getY() / 100 + ")");
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    protected void paintComponent(Graphics g) {
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
}
