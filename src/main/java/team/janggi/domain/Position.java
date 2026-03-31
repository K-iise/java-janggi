package team.janggi.domain;

public record Position(
        int x,
        int y
) {

    private static final int MINIMUM_X_INDEX = 0;
    private static final int MAXIMUM_X_INDEX = 8;
    private static final int MINIMUM_Y_INDEX = 0;
    private static final int MAXIMUM_Y_INDEX = 9;

    public Position {
        validateRange(x, y);
    }

    private void validateRange(int x, int y) {
        if (x < MINIMUM_X_INDEX || x > MAXIMUM_X_INDEX) {
            throw new IllegalArgumentException(String.format("[ERROR] x 좌표는 %d ~ %d 사이여야 합니다.", MINIMUM_X_INDEX, MAXIMUM_X_INDEX));
        }
        if (y < MINIMUM_Y_INDEX || y > MAXIMUM_Y_INDEX) {
            throw new IllegalArgumentException(String.format("[ERROR] y 좌표는 %d ~ %d 사이여야 합니다.", MINIMUM_Y_INDEX, MAXIMUM_Y_INDEX));
        }
    }
}
