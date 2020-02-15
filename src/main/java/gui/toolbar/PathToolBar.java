package gui.toolbar;

import gui.Constants;

import javax.swing.*;
import java.text.NumberFormat;

public class PathToolBar extends JToolBar {
    public PathToolBar() {
        super("Tool Bar | Spikes Path Drawing Tool");

        JLabel xLabel = new JLabel(" X (cm): ");
        JFormattedTextField x = new JFormattedTextField(NumberFormat.getNumberInstance());
        x.setColumns(Constants.TEXTFIELD_COLUMNS);

        JLabel yLabel = new JLabel(" Y (cm): ");
        JFormattedTextField y = new JFormattedTextField(NumberFormat.getNumberInstance());
        y.setColumns(Constants.TEXTFIELD_COLUMNS);

        JButton update = new JButton("Update");

        add(xLabel);
        add(x);
        add(yLabel);
        add(y);
        add(update);
    }
}
