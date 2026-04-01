package team.janggi.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public record Position(
        int x,
        int y
) {

    private static final int MINIMUM_X_INDEX = 0;
    private static final int MAXIMUM_X_INDEX = 8;
    private static final int MINIMUM_Y_INDEX = 0;
    private static final int MAXIMUM_Y_INDEX = 9;

    private static final Map<Integer, Position> CACHE = new ConcurrentHashMap<>();

    static {
        for (int i = MINIMUM_X_INDEX; i <= MAXIMUM_X_INDEX; i++) {
            for (int j = MINIMUM_Y_INDEX; j <= MAXIMUM_Y_INDEX; j++) {
                int index = calculateIndex(i, j);
                CACHE.put(index, new Position(i, j));
            }
        }
    }

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

    public static Position of(int x, int y) {
        int index = calculateIndex(x, y);
        if (!CACHE.containsKey(index)) {
            throw new IllegalArgumentException("[ERROR] 범위를 벗어난 좌표입니다.");
        }
        return CACHE.get(index);
    }

    private static int calculateIndex(int x, int y) {
        return (x * 10) + y;
    }
}
