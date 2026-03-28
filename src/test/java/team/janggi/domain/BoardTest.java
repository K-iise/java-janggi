package team.janggi.domain;

import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import team.janggi.domain.piece.Cannon;
import team.janggi.domain.piece.Chariot;
import team.janggi.domain.piece.Elephant;
import team.janggi.domain.piece.Guard;
import team.janggi.domain.piece.Horse;
import team.janggi.domain.piece.King;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.piece.Soldier;
import team.janggi.domain.strategy.boardstruct.NormalBoardStrategy;
import team.janggi.domain.strategy.layout.normal.NormalLayoutStrategy;
import team.janggi.domain.strategy.layout.normal.NormalSetup;

public class BoardTest {

    @Test
    void 기본_장기_보드_생성_테스트() {
        var layout = new NormalLayoutStrategy(NormalSetup.바깥상차림, NormalSetup.바깥상차림);
        var boardStruct = new NormalBoardStrategy(layout);

        Board board = new Board(boardStruct);
        board.initBoard();

        Assertions.assertEquals(90, board.getStatus().size());
    }

    @Test
    void 기본_장기_배치_테스트() {
        var layout = new NormalLayoutStrategy(NormalSetup.바깥상차림, NormalSetup.바깥상차림);
        var boardStruct = new NormalBoardStrategy(layout);

        Board board = new Board(boardStruct);
        board.initBoard();

        Map<Position, Piece> pieceMap = board.getStatus();
        // 초나라(CHO)가 아래쪽에 잘 배치되었는지 확인
        검증_초나라_기본_배치(pieceMap);
        // 한나라(HAN)가 위쪽에 잘 배치되었는지 확인
        검증_한나라_기본_배치(pieceMap);
    }

    private void 검증_초나라_기본_배치(Map<Position, Piece> pieceMap) {
        Assertions.assertAll(
                () -> Assertions.assertEquals(new Chariot(Team.CHO), pieceMap.get(new Position(9, 0))),
                () -> Assertions.assertEquals(new Chariot(Team.CHO), pieceMap.get(new Position(9, 8))),
                () -> Assertions.assertEquals(new Cannon(Team.CHO), pieceMap.get(new Position(7, 1))),
                () -> Assertions.assertEquals(new Cannon(Team.CHO), pieceMap.get(new Position(7, 7))),
                () -> Assertions.assertEquals(new Soldier(Team.CHO), pieceMap.get(new Position(6, 0))),
                () -> Assertions.assertEquals(new Soldier(Team.CHO), pieceMap.get(new Position(6, 2))),
                () -> Assertions.assertEquals(new Soldier(Team.CHO), pieceMap.get(new Position(6, 4))),
                () -> Assertions.assertEquals(new Soldier(Team.CHO), pieceMap.get(new Position(6, 6))),
                () -> Assertions.assertEquals(new Soldier(Team.CHO), pieceMap.get(new Position(6, 8))),
                () -> Assertions.assertEquals(new King(Team.CHO), pieceMap.get(new Position(8, 4))),
                () -> Assertions.assertEquals(new Guard(Team.CHO), pieceMap.get(new Position(9, 3))),
                () -> Assertions.assertEquals(new Guard(Team.CHO), pieceMap.get(new Position(9, 5)))
        );
    }

    private void 검증_한나라_기본_배치(Map<Position, Piece> pieceMap) {
        Assertions.assertAll(
                () -> Assertions.assertEquals(new Chariot(Team.HAN), pieceMap.get(new Position(0, 0))),
                () -> Assertions.assertEquals(new Chariot(Team.HAN), pieceMap.get(new Position(0, 8))),
                () -> Assertions.assertEquals(new Cannon(Team.HAN), pieceMap.get(new Position(2, 1))),
                () -> Assertions.assertEquals(new Cannon(Team.HAN), pieceMap.get(new Position(2, 7))),
                () -> Assertions.assertEquals(new Soldier(Team.HAN), pieceMap.get(new Position(3, 0))),
                () -> Assertions.assertEquals(new Soldier(Team.HAN), pieceMap.get(new Position(3, 2))),
                () -> Assertions.assertEquals(new Soldier(Team.HAN), pieceMap.get(new Position(3, 4))),
                () -> Assertions.assertEquals(new Soldier(Team.HAN), pieceMap.get(new Position(3, 6))),
                () -> Assertions.assertEquals(new Soldier(Team.HAN), pieceMap.get(new Position(3, 8))),
                () -> Assertions.assertEquals(new King(Team.HAN), pieceMap.get(new Position(1, 4))),
                () -> Assertions.assertEquals(new Guard(Team.HAN), pieceMap.get(new Position(0, 3))),
                () -> Assertions.assertEquals(new Guard(Team.HAN), pieceMap.get(new Position(0, 5)))
        );
    }

    @Test void 초_바깥_한_바깥() {
        runTest(NormalSetup.바깥상차림, NormalSetup.바깥상차림);
    }

    @Test void 초_바깥_한_안() {
        runTest(NormalSetup.바깥상차림, NormalSetup.안상차림);
    }

    @Test void 초_안_한_왼() {
        runTest(NormalSetup.안상차림, NormalSetup.왼상차림);
    }

    @Test void 초_왼_한_오른() {
        runTest(NormalSetup.왼상차림, NormalSetup.오른상차림);
    }

    private void runTest(NormalSetup choSetup, NormalSetup hanSetup) {
        var layout = new NormalLayoutStrategy(choSetup, hanSetup);
        var boardStruct = new NormalBoardStrategy(layout);

        Board board = new Board(boardStruct);
        board.initBoard();

        Map<Position, Piece> pieceMap = board.getStatus();

        // 고정 기물 검증
        검증_초나라_기본_배치(pieceMap);
        검증_한나라_기본_배치(pieceMap);

        // 상/마 차림 검증 (초는 9번 행, 한은 0번 행)
        assertSetup(pieceMap, 9, Team.CHO, choSetup);
        assertSetup(pieceMap, 0, Team.HAN, hanSetup);
    }

    private void assertSetup(Map<Position, Piece> pieceMap, int row, Team team, NormalSetup setup) {
        switch (setup) {
            case 왼상차림 -> Assertions.assertAll(
                    () -> Assertions.assertEquals(new Elephant(team), pieceMap.get(new Position(row, 1))),
                    () -> Assertions.assertEquals(new Horse(team), pieceMap.get(new Position(row, 2))),
                    () -> Assertions.assertEquals(new Horse(team), pieceMap.get(new Position(row, 6))),
                    () -> Assertions.assertEquals(new Elephant(team), pieceMap.get(new Position(row, 7)))
            );

            case 오른상차림 -> Assertions.assertAll(
                    () -> Assertions.assertEquals(new Horse(team), pieceMap.get(new Position(row, 1))),
                    () -> Assertions.assertEquals(new Elephant(team), pieceMap.get(new Position(row, 2))),
                    () -> Assertions.assertEquals(new Elephant(team), pieceMap.get(new Position(row, 6))),
                    () -> Assertions.assertEquals(new Horse(team), pieceMap.get(new Position(row, 7)))
            );

            case 안상차림 -> Assertions.assertAll(
                    () -> Assertions.assertEquals(new Horse(team), pieceMap.get(new Position(row, 1))),
                    () -> Assertions.assertEquals(new Elephant(team), pieceMap.get(new Position(row, 2))),
                    () -> Assertions.assertEquals(new Horse(team), pieceMap.get(new Position(row, 6))),
                    () -> Assertions.assertEquals(new Elephant(team), pieceMap.get(new Position(row, 7)))
            );

            case 바깥상차림 -> Assertions.assertAll(
                    () -> Assertions.assertEquals(new Elephant(team), pieceMap.get(new Position(row, 1))),
                    () -> Assertions.assertEquals(new Horse(team), pieceMap.get(new Position(row, 2))),
                    () -> Assertions.assertEquals(new Elephant(team), pieceMap.get(new Position(row, 6))),
                    () -> Assertions.assertEquals(new Horse(team), pieceMap.get(new Position(row, 7)))
            );
        }
    }


    @Test
    public void 기물_이동_테스트() {
        var boardStruct = new NormalBoardStrategy(EmptyLayoutStrategy.instance);
        var boardStatus = new LocalMemoryBoardStatus();
        var board = new Board(boardStatus, boardStruct);

        board.initBoard();

        // 졸 기물 세팅
        Soldier soldier = new Soldier(Team.CHO);
        boardStatus.setPiece(new Position(6, 5), soldier);

        // 초나라의 졸을 왼쪽으로 한칸 이동
        Position from = new Position(6, 5);
        Position to = new Position(5, 5);
        board.move(Team.CHO, from, to);

        // 이동 후 해당 위치에 병이 있는지 확인
        Map<Position, Piece> pieceMap = board.getStatus();
        Assertions.assertEquals(soldier, pieceMap.get(to));
    }
}