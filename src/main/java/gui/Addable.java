package gui;

import com.spikes2212.path.Waypoint;

public interface Addable {
    void add(Waypoint waypoint);

    boolean select(double x, double y);
}
