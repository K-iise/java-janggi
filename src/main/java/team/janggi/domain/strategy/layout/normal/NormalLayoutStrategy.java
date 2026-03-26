package team.janggi.domain.strategy.layout.normal;

import team.janggi.domain.BoardStatus;
import team.janggi.domain.piece.Cannon;
import team.janggi.domain.piece.Chariot;
import team.janggi.domain.piece.Elephant;
import team.janggi.domain.piece.Guard;
import team.janggi.domain.piece.Horse;
import team.janggi.domain.piece.King;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.piece.Soldier;
import team.janggi.domain.strategy.layout.LayoutStrategy;

public class NormalLayoutStrategy implements LayoutStrategy {

    private final NormalSetup choSetup;
    private final NormalSetup hanSetup;

    // 초(CHO)는 이제 아래쪽(Y 번호가 큰 쪽)에 위치합니다.
    private static final int CHO_BACK_RANK_ROW = 9;    // 맨 아래
    private static final int CHO_KING_RANK_ROW = 8;    // 궁성 안
    private static final int CHO_CANNON_RANK_ROW = 7;  // 포 라인
    private static final int CHO_SOLDIER_RANK_ROW = 6; // 졸 라인

    // 한(HAN)은 이제 위쪽(Y 번호가 작은 쪽)에 위치합니다.
    private static final int HAN_BACK_RANK_ROW = 0;    // 맨 위
    private static final int HAN_KING_RANK_ROW = 1;    // 궁성 안
    private static final int HAN_CANNON_RANK_ROW = 2;  // 포 라인
    private static final int HAN_SOLDIER_RANK_ROW = 3; // 병 라인

    private static final int[] SOLDIER_COLUMNS = {0, 2, 4, 6, 8};

    public NormalLayoutStrategy(NormalSetup choSetup, NormalSetup hanSetup) {
        this.choSetup = choSetup;
        this.hanSetup = hanSetup;
    }

    @Override
    public void init(BoardStatus boardStatus) {
        initChoBatch(boardStatus);
        initHanBatch(boardStatus);
    }

    private void initChoBatch(BoardStatus boardStatus) {
        // 차
        boardStatus.setPiece(new Position(CHO_BACK_RANK_ROW, 0), new Chariot(Team.CHO));
        boardStatus.setPiece(new Position(CHO_BACK_RANK_ROW, 8), new Chariot(Team.CHO));

        // 포
        boardStatus.setPiece(new Position(CHO_CANNON_RANK_ROW, 1), new Cannon(Team.CHO));
        boardStatus.setPiece(new Position(CHO_CANNON_RANK_ROW, 7), new Cannon(Team.CHO));

        // 졸
        for (int col : SOLDIER_COLUMNS) {
            boardStatus.setPiece(new Position(CHO_SOLDIER_RANK_ROW, col), new Soldier(Team.CHO));
        }

        // 왕
        boardStatus.setPiece(new Position(CHO_KING_RANK_ROW, 4), new King(Team.CHO));

        // 사
        boardStatus.setPiece(new Position(CHO_BACK_RANK_ROW, 3), new Guard(Team.CHO));
        boardStatus.setPiece(new Position(CHO_BACK_RANK_ROW, 5), new Guard(Team.CHO));

        // 상/마 배치
        placeBySetup(boardStatus, choSetup, Team.CHO, CHO_BACK_RANK_ROW);
    }

    private void initHanBatch(BoardStatus boardStatus) {
        // 차
        boardStatus.setPiece(new Position(HAN_BACK_RANK_ROW, 0), new Chariot(Team.HAN));
        boardStatus.setPiece(new Position(HAN_BACK_RANK_ROW, 8), new Chariot(Team.HAN));

        // 포
        boardStatus.setPiece(new Position(HAN_CANNON_RANK_ROW, 1), new Cannon(Team.HAN));
        boardStatus.setPiece(new Position(HAN_CANNON_RANK_ROW, 7), new Cannon(Team.HAN));

        // 병
        for (int col : SOLDIER_COLUMNS) {
            boardStatus.setPiece(new Position(HAN_SOLDIER_RANK_ROW, col), new Soldier(Team.HAN));
        }

        // 왕
        boardStatus.setPiece(new Position(HAN_KING_RANK_ROW, 4), new King(Team.HAN));

        // 사
        boardStatus.setPiece(new Position(HAN_BACK_RANK_ROW, 3), new Guard(Team.HAN));
        boardStatus.setPiece(new Position(HAN_BACK_RANK_ROW, 5), new Guard(Team.HAN));

        // 상/마 배치
        placeBySetup(boardStatus, hanSetup, Team.HAN, HAN_BACK_RANK_ROW);
    }

    private void placeBySetup(BoardStatus boardStatus, NormalSetup setup, Team team, int row) {
        if (setup == NormalSetup.왼상차림) {
            boardStatus.setPiece(new Position(row, 1), new Elephant(team));
            boardStatus.setPiece(new Position(row, 2), new Horse(team));
            boardStatus.setPiece(new Position(row, 6), new Horse(team));
            boardStatus.setPiece(new Position(row, 7), new Elephant(team));
            return;
        }

        if (setup == NormalSetup.오른상차림) {
            boardStatus.setPiece(new Position(row, 1), new Horse(team));
            boardStatus.setPiece(new Position(row, 2), new Elephant(team));
            boardStatus.setPiece(new Position(row, 6), new Elephant(team));
            boardStatus.setPiece(new Position(row, 7), new Horse(team));
            return;
        }

        if (setup == NormalSetup.안상차림) {
            boardStatus.setPiece(new Position(row, 1), new Horse(team));
            boardStatus.setPiece(new Position(row, 2), new Elephant(team));
            boardStatus.setPiece(new Position(row, 6), new Horse(team));
            boardStatus.setPiece(new Position(row, 7), new Elephant(team));
            return;
        }

        if (setup == NormalSetup.바깥상차림) {
            boardStatus.setPiece(new Position(row, 1), new Elephant(team));
            boardStatus.setPiece(new Position(row, 2), new Horse(team));
            boardStatus.setPiece(new Position(row, 6), new Elephant(team));
            boardStatus.setPiece(new Position(row, 7), new Horse(team));
            return;
        }
    }
}