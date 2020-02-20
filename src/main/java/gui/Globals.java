package gui;

import gui.gains.GainsDialog;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;
import java.util.prefs.Preferences;

/**
 * Class containing global project constants.
 *
 * @author Eran Goldstein
 */
public class Globals {
    /**
     * A path manipulation action that was performed within the UI.
     */
    public enum Action {
        ADD, MIRROR, GENERATE
    }

    /**
     * The actions performed on the current path, in order.
     */
    public static Stack<Action> UNDO_STACK = new Stack<>();

    /**
     * The preferences object that saves the preferences for default directory and path generation gains.
     */
    public static final Preferences PREFS = Preferences.userNodeForPackage(GUI.class);

    /**
     * The file chooser used when opening and saving path files.
     * <p>
     * Using one global file chooser allows us to keep directory preference without constant {@link Preferences} calls.
     */
    public static final JFileChooser FILE_CHOOSER = new JFileChooser(PREFS.get("WORKING_DIRECTORY", null));

    /**
     * The gains dialog used when getting user input for the gains for path generation.
     * <p>
     * Using one global gains dialog allows conservation of memory.
     */
    public static final GainsDialog GAINS_DIALOG = new GainsDialog();

    /**
     * The amount of character columns in number fields.
     */
    public static final int TEXTFIELD_COLUMNS = 12;

    /**
     * The width of the game field in centimeters.
     * <p>
     * This constant needs to be changed whenever a new game field is introduced.
     */
    public static final int FIELD_WIDTH = 799;

    /**
     * The height of the game field in centimeters.
     * <p>
     * This constant needs to be changed whenever a new game field is introduced.
     */
    public static final int FIELD_HEIGHT = 770;

    /**
     * Converts meters to centimeters when multiplied by.
     */
    public static final int M_TO_CM = 100;

    /**
     * Converts centimeters to meters when multiplied by.
     */
    public static final double CM_TO_M = 0.01;

    /**
     * The width of each path segment on the UI.
     */
    public static final int PATH_WIDTH = 4;

    /**
     * The radius of each path point on the UI.
     */
    public static final int POINT_RADIUS = 8;

    /**
     * The radius of each path point of a generated path on the UI.
     */
    public static final int GENERATED_RADIUS = 4;

    /**
     * The color of each path segment on the UI.
     */
    public static final Color PATH_COLOR = Color.RED;

    /**
     * The color of each path point on the UI.
     */
    public static final Color POINT_COLOR = Color.RED;

    /**
     * The color of the selected path point on the UI.
     */
    public static final Color SELECTION_COLOR = Color.YELLOW;
}
