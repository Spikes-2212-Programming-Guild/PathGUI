package gui;

import com.spikes2212.path.Waypoint;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConnectionListener extends MouseAdapter {
    private Connectible connectible;

    public ConnectionListener(Connectible connectible) {
        this.connectible = connectible;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        connectible.add(new Waypoint(point.getX() / 100, point.getY() / 100));
        connectible.update();
    }
}
