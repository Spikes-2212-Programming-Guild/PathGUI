package gui.toolbar;

import gui.Globals;
import gui.PathMaker;

import javax.swing.*;
import java.text.NumberFormat;

/**
 * The tool bar for the path GUI.
 */
public class PathToolBar extends JToolBar {
    /**
     * The text field input for the x coordinate.
     */
    private JFormattedTextField xField;

    /**
     * The text field input for the x coordinate.
     */
    private JFormattedTextField yField;

    /**
     * Sets up the tool bar, menus and menu items.
     *
     * @param pathMaker a path drawing interface object.
     */
    public PathToolBar(PathMaker pathMaker) {
        super("Tool Bar | Spikes Path Drawing Tool");

        JLabel xLabel = new JLabel(" X (cm): ");
        xField = new JFormattedTextField(NumberFormat.getNumberInstance());
        xField.setColumns(Globals.TEXTFIELD_COLUMNS);

        JLabel yLabel = new JLabel(" Y (cm): ");
        yField = new JFormattedTextField(NumberFormat.getNumberInstance());
        yField.setColumns(Globals.TEXTFIELD_COLUMNS);

        JButton update = new JButton("Update");
        update.addActionListener(actionEvent -> pathMaker
                .moveSelected(Double.parseDouble(xField.getText()) * Globals.CM_TO_M,
                        Double.parseDouble(yField.getText()) * Globals.CM_TO_M));

        add(xLabel);
        add(xField);
        add(yLabel);
        add(yField);
        add(update);
    }

    /**
     * Set the coordinates displayed on the tool bar's text fields.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void setCoordinates(int x, int y) {
        xField.setText(String.valueOf(x));
        yField.setText(String.valueOf(y));
    }
}
