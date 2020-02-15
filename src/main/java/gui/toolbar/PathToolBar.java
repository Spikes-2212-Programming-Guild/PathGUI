package gui.toolbar;

import gui.Constants;

import javax.swing.*;
import java.text.NumberFormat;

public class PathToolBar extends JToolBar {

    private JFormattedTextField xField;
    private JFormattedTextField yField;

    public PathToolBar() {
        super("Tool Bar | Spikes Path Drawing Tool");

        JLabel xLabel = new JLabel(" X (cm): ");
        xField = new JFormattedTextField(NumberFormat.getNumberInstance());
        xField.setColumns(Constants.TEXTFIELD_COLUMNS);

        JLabel yLabel = new JLabel(" Y (cm): ");
        yField = new JFormattedTextField(NumberFormat.getNumberInstance());
        yField.setColumns(Constants.TEXTFIELD_COLUMNS);

        JButton update = new JButton("Update");

        add(xLabel);
        add(xField);
        add(yLabel);
        add(yField);
        add(update);
    }

    public void setCoordinates(int x, int y) {
        xField.setText(String.valueOf(x));
        yField.setText(String.valueOf(y));
    }
}
