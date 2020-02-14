package gui;

public class Constants {
    public enum Action {
        ADD, MIRROR, GENERATE, NONE
    }

    public static Action lastAction = Action.NONE;

    public static final int FRAME_WIDTH = 814;
    public static final int FRAME_HEIGHT = 830;

    public static final int TEXTFIELD_COLUMNS = 20;
    public static final int GAINS_WIDTH = 640;
    public static final int GAINS_HEIGHT = 480;

    public static final int FIELD_WIDTH = 799;
    public static final int FIELD_HEIGHT = 770;

    public static final int M_TO_CM = 100;
    public static final double CM_TO_M = 0.01;

    public static final int PATH_WIDTH = 4;
    public static final int POINT_RADIUS = 4;
}
