package team.janggi.domain.board;

import team.janggi.domain.Position;
import team.janggi.domain.piece.Empty;
import team.janggi.domain.strategy.layout.normal.NormalLayoutStrategy;

public class BoardFactory {
    private static final int NORMAL_BOARD_ROW_SIZE = 10;
    private static final int NORMAL_BOARD_COL_SIZE = 9;

    private final NormalLayoutStrategy layout;

    public BoardFactory(NormalLayoutStrategy layout) {
        this.layout = layout;
    }

    public void initBoardStatus(BoardStatus status) {
        initMapByEmpty(status);

        layout.init(status);
    }

    private void initMapByEmpty( BoardStatus status) {
        for (int row = 0; row < NORMAL_BOARD_ROW_SIZE; row++) {
            initMapRowByEmpty(status, row);
        }
    }

    private void initMapRowByEmpty(BoardStatus status, int row) {
        for (int column = 0; column < NORMAL_BOARD_COL_SIZE; column++) {
            status.setPiece(new Position(row, column), Empty.instance);
        }
    }
}
