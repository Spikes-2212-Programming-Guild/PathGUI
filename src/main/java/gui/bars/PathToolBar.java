package gui.bars;

import components.PathNumberField;
import gui.GUI;
import gui.bars.listeners.*;

import javax.swing.*;

/**
 * The tool bar for the path GUI.
 *
 * @author Eran Goldstein
 */
public class PathToolBar extends JToolBar {
    /**
     * The text field input for the x coordinate.
     */
    private PathNumberField xField;

    /**
     * The text field input for the x coordinate.
     */
    private PathNumberField yField;

    /**
     * Sets up the tool bar, menus and menu items.
     *
     * @param gui an interface object
     */
    public PathToolBar(GUI gui) {
        super("Tool Bar | Spikes Path Drawing Tool");

        JLabel xLabel = new JLabel(" X (m): ");
        xField = new PathNumberField();

        JLabel yLabel = new JLabel(" Y (m): ");
        yField = new PathNumberField();

        JButton move = new JButton("Move");
        move.addActionListener(actionEvent -> gui.getPathPane().moveSelected(xField.getNumber(), yField.getNumber()));

        JButton newFile = new JButton(new ImageIcon(getClass().getResource("/res/icon/new.png")));
        newFile.addActionListener(new NewListener(gui));

        JButton save = new JButton(new ImageIcon(getClass().getResource("/res/icon/save.png")));
        save.addActionListener(new SaveListener(gui));

        JButton open = new JButton(new ImageIcon(getClass().getResource("/res/icon/open.png")));
        open.addActionListener(new OpenListener(gui));

        JButton undo = new JButton(new ImageIcon(getClass().getResource("/res/icon/undo.png")));
        undo.addActionListener(new UndoListener(gui));

        JButton mirror = new JButton(new ImageIcon(getClass().getResource("/res/icon/mirror.png")));
        mirror.addActionListener(new MirrorListener(gui));

        add(newFile);
        add(save);
        add(open);
        addSeparator();
        add(undo);
        add(mirror);
        addSeparator();
        add(xLabel);
        add(xField);
        add(yLabel);
        add(yField);
        add(move);
    }

    /**
     * Set the coordinates displayed on the tool bar's text fields.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void setCoordinates(double x, double y) {
        xField.setNumber(x);
        yField.setNumber(y);
    }
}
