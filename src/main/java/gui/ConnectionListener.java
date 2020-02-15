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
    public void mouseReleased(MouseEvent e) {
        Point point = e.getPoint();
        if(connectible.select(point.getX() * Constants.CM_TO_M,
                (Constants.FIELD_WIDTH - point.getY()) * Constants.CM_TO_M))
            return;

        connectible.add(new Waypoint(point.getX() * Constants.CM_TO_M,
                (Constants.FIELD_WIDTH - point.getY()) * Constants.CM_TO_M));
        Constants.actions.push(Constants.Action.ADD);
    }
}
