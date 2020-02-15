package gui;

import com.spikes2212.path.Waypoint;

public interface Connectible {
    void add(Waypoint waypoint);

    void repaint();

    boolean select(double x, double y);
}
