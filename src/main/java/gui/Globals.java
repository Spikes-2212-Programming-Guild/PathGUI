package gui;

import java.awt.*;
import java.util.Stack;

/**
 * Class containing global project constants.
 */
public class Globals {
    /**
     * An path manipulation action that was performed within the UI.
     */
    public enum Action {
        ADD, MIRROR, GENERATE, NONE
    }

    /**
     * The actions performed on the current path, in order.
     */
    public static final Stack<Action> UNDO_STACK = new Stack<>();

    /**
     * The amount of character columns in text fields.
     */
    public static final int TEXTFIELD_COLUMNS = 20;

    /**
     * The width of the field in centimeters.
     * <p>
     * This constant needs to be changed when a new field is introduced.
     */
    public static final int FIELD_WIDTH = 799;

    /**
     * The height of the field in centimeters.
     * <p>
     * This constant needs to be changed when a new field is introduced.
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
     * The width of the path on the UI.
     */
    public static final int PATH_WIDTH = 4;

    /**
     * The radius of each path point on the UI.
     */
    public static final int POINT_RADIUS = 8;

    /**
     * The color of the path on the UI.
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
