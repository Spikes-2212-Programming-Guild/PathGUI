package gui.bars;

import components.DocumentAdapter;
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

        JButton newFile = new JButton(new ImageIcon(getClass().getResource("/res/icon/new.png")));
        newFile.addActionListener(new NewListener(gui));
        newFile.setToolTipText("New");

        JButton save = new JButton(new ImageIcon(getClass().getResource("/res/icon/save.png")));
        save.addActionListener(new SaveListener(gui));
        save.setToolTipText("Save");

        JButton open = new JButton(new ImageIcon(getClass().getResource("/res/icon/open.png")));
        open.addActionListener(new OpenListener(gui));
        open.setToolTipText("Open");

        JButton undo = new JButton(new ImageIcon(getClass().getResource("/res/icon/undo.png")));
        undo.addActionListener(new UndoListener(gui));
        undo.setToolTipText("Undo");

        JButton mirror = new JButton(new ImageIcon(getClass().getResource("/res/icon/mirror.png")));
        mirror.addActionListener(new MirrorListener(gui));
        mirror.setToolTipText("Mirror");

        DocumentAdapter documentAdapter = e -> gui.getPathPane().moveSelected(xField.getNumber(), yField.getNumber());

        JLabel xLabel = new JLabel(" X (m): ");
        xField = new PathNumberField();
        xField.getDocument().addDocumentListener(documentAdapter);

        JLabel yLabel = new JLabel(" Y (m): ");
        yField = new PathNumberField();
        yField.getDocument().addDocumentListener(documentAdapter);

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
