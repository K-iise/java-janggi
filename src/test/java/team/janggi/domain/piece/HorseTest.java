package team.janggi.domain.piece;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import team.janggi.domain.BoardStatus;
import team.janggi.domain.EmptyLayoutStrategy;
import team.janggi.domain.LocalMemoryBoardStatus;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.strategy.boardstruct.NormalBoardStrategy;

public class HorseTest {

    private BoardStatus boardStatus = new LocalMemoryBoardStatus();

    @BeforeEach
    void setUp() {
        new NormalBoardStrategy(EmptyLayoutStrategy.instance).initBoardStatus(boardStatus);
    }

    @ParameterizedTest
    @CsvSource({
            "5, 5, 7, 4",
            "5, 5, 7, 6",
            "5, 5, 6, 7",
            "5, 5, 6, 3",
            "5, 5, 4, 3",
            "5, 5, 3, 4",
            "5, 5, 3, 6",
            "5, 5, 4, 7"
    })
    void 마는_장애물이_없으면_날일자_경로로_이동할_수_있다(int startX, int startY, int endX, int endY){
        // given
        Piece horse = new Horse(Team.CHO);
        Position from = new Position(startX, startY);
        Position to = new Position(endX, endY);

        boardStatus.setPiece(from, horse);
        // when & then
        Assertions.assertTrue(horse.canMove(from, to, boardStatus.getBoardStatus()));
    }
    @ParameterizedTest
    @CsvSource({
            "5, 5, 7, 4, 6, 5",
            "5, 5, 7, 6, 6, 5",
            "5, 5, 6, 7, 5, 6"
    })
    void 마는_장애물이_존재하면_날일자_경로로_이동할_수_없다(int startX, int startY, int endX, int endY, int obstacleX, int obstacleY) {
        // given
        Piece horse = new Horse(Team.CHO);
        Position from = new Position(startX, startY);
        Position to = new Position(endX, endY);

        Piece obstacle = new Soldier(Team.CHO);
        Position obstaclePosition = new Position(obstacleX, obstacleY);
        boardStatus.setPiece(from, horse);
        boardStatus.setPiece(obstaclePosition, obstacle);

        // when & then
        Assertions.assertFalse(horse.canMove(from, to, boardStatus.getBoardStatus()));

    }
}
