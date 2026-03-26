package team.janggi.domain.piece;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.janggi.domain.BoardStatus;
import team.janggi.domain.EmptyLayoutStrategy;
import team.janggi.domain.LocalMemoryBoardStatus;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.strategy.boardstruct.NormalBoardStrategy;

public class SoldierTest {

    private BoardStatus boardStatus = new LocalMemoryBoardStatus();

    @BeforeEach
    void setUp() {
        new NormalBoardStrategy(EmptyLayoutStrategy.instance).initMapStatus(boardStatus);
    }

    @Test
    void 초의_졸은_전진_할_수_있다() {
        // given
        Soldier soldier = new Soldier(Team.CHO);
        Position currentPosition = new Position(5, 5);

        boardStatus.setPiece(currentPosition, soldier);

        Position definationPosition = new Position(5, 4);

        // when & then
        Assertions.assertTrue(soldier.canMove(currentPosition, definationPosition, boardStatus.getBoardStatus()));
    }


    @Test
    void 한의_졸은_전진_할_수_있다() {
        // given
        Soldier soldier = new Soldier(Team.HAN);
        Position currentPosition = new Position(5, 5);

        boardStatus.setPiece(currentPosition, soldier);

        Position definationPosition = new Position(5, 6);

        // when & then
        Assertions.assertTrue(soldier.canMove(currentPosition, definationPosition, boardStatus.getBoardStatus()));
    }


    @Test
    void 초의_졸은_후퇴_할_수_없다() {
        // given
        Soldier soldier = new Soldier(Team.CHO);
        Position currentPosition = new Position(5, 5);

        boardStatus.setPiece(currentPosition, soldier);

        Position definationPosition = new Position(5, 6);

        // when & then
        Assertions.assertFalse(soldier.canMove(currentPosition, definationPosition, boardStatus.getBoardStatus()));

    }

    @Test
    void 한의_졸은_후퇴_할_수_없다() {
        // given
        Soldier soldier = new Soldier(Team.HAN);
        Position currentPosition = new Position(5, 5);

        boardStatus.setPiece(currentPosition, soldier);

        Position definationPosition = new Position(5, 4);

        // when & then
        Assertions.assertFalse(soldier.canMove(currentPosition, definationPosition, boardStatus.getBoardStatus()));

    }

    @Test
    void 초의_졸은_좌으로_이동할_수_있다() {
        var soldier = new Soldier(Team.CHO);
        var currentPosition = new Position(5, 5);

        boardStatus.setPiece(currentPosition, soldier);

        var definationPosition = new Position(4, 5);
        // when & then
        Assertions.assertTrue(soldier.canMove(currentPosition, definationPosition, boardStatus.getBoardStatus()));
    }

    @Test
    void 한의_졸은_좌으로_이동할_수_있다() {
        var soldier = new Soldier(Team.HAN);
        var currentPosition = new Position(5, 5);

        boardStatus.setPiece(currentPosition, soldier);

        var definationPosition = new Position(6, 5);

        // when & then
        Assertions.assertTrue(soldier.canMove(currentPosition, definationPosition, boardStatus.getBoardStatus()));
    }

    @Test
    void 초의_졸은_우로_이동할_수_있다() {
        var soldier = new Soldier(Team.CHO);
        var currentPosition = new Position(5, 5);

        boardStatus.setPiece(currentPosition, soldier);

        var definationPosition = new Position(6, 5);

        // when & then
        Assertions.assertTrue(soldier.canMove(currentPosition, definationPosition, boardStatus.getBoardStatus()));
    }

    @Test
    void 한의_졸은_우로_이동할_수_있다() {
        var soldier = new Soldier(Team.HAN);
        var currentPosition = new Position(5, 5);

        boardStatus.setPiece(currentPosition, soldier);

        var definationPosition = new Position(4, 5);

        // when & then
        Assertions.assertTrue(soldier.canMove(currentPosition, definationPosition, boardStatus.getBoardStatus()));

    }
}
