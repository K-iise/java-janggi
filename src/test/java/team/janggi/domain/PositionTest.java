package team.janggi.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionTest {

    @Test
    public void 좌표_값은_양수를_허용한다() {
        // given
        int x = 1;
        int y = 1;

        // when & then
        Assertions.assertDoesNotThrow(() -> new Position(x, y));
    }

    @Test
    public void 좌표_값은_음수를_허용하지않는다() {
        // given
        int x = -1;
        int y = -1;

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Position(x, y));
    }

    @ParameterizedTest
    @CsvSource({
            "9, 0",
            "0, 10",
            "-1, 3",
            "4, -1"
    })
    public void 장기_보드판_범위를_벗어나는_좌표는_예외_발생한다(int x, int y){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Position(x, y));
    }

}
