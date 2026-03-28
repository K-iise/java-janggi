package team.janggi.domain;

public record Position(
        int x,
        int y
) {

    private static final int MINIMUM_INDEX = 0;

    public Position {
        if (x < MINIMUM_INDEX || y < MINIMUM_INDEX) {
            throw new IllegalArgumentException("[ERROR] 음수는 허용되지 않습니다.");
        }
    }
}
