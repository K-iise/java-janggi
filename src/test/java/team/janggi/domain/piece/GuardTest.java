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

public class GuardTest {

    private BoardStatus boardStatus = new LocalMemoryBoardStatus();

    @BeforeEach
    void setUp() {
        new NormalBoardStrategy(EmptyLayoutStrategy.instance).initBoardStatus(boardStatus);
    }

    @ParameterizedTest
    @CsvSource({
            "5, 5, 5, 4",
            "5, 5, 5, 6",
            "5, 5, 4, 5",
            "5, 5, 5, 6"
    })
    void 사는_상하좌우로_한칸_이동_할_수_있다(int startX, int startY, int endX, int endY){
        // given
        Piece king = new Guard(Team.CHO);
        Position currentPosition = new Position(startX, startY);

        boardStatus.setPiece(currentPosition, king);

        Position definationPosition = new Position(endX, endY);

        // when & then
        Assertions.assertTrue(king.canMove(currentPosition, definationPosition, boardStatus.getBoardStatus()));

    }

    @ParameterizedTest
    @CsvSource({
            "5, 5, 6, 6",
            "5, 5, 6, 4",
            "5, 5, 4, 4",
            "5, 5, 4, 6"
    })
    void 사는_대각선으로_한칸_이동_할_수_있다(int startX, int startY, int endX, int endY){
        // given
        Piece king = new Guard(Team.CHO);
        Position currentPosition = new Position(startX, startY);

        boardStatus.setPiece(currentPosition, king);

        Position definationPosition = new Position(endX, endY);

        // when & then
        Assertions.assertTrue(king.canMove(currentPosition, definationPosition, boardStatus.getBoardStatus()));

    }
}
