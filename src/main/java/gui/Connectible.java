package gui;

import com.spikes2212.path.Waypoint;

public interface Connectible {
    void add(Waypoint waypoint);

    void removeLast();

    void update();
}
