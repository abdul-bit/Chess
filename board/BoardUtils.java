package board;

public class BoardUtils {
    private BoardUtils() {
        throw new RuntimeException("You cannot instantiate this class");
    }

    public static boolean isValidSquareCoordinate(int coordinate) {

        return coordinate >= 0 && coordinate <= 64;

    }

    public static final boolean[] FIRST_COLUMN = null;
    public static final boolean[] SECOND_COLUMN = null;

    public static final boolean[] SEVENTH_COLUMN = null;

    public static final boolean[] EIGHTH_COLUMN = null;

}
