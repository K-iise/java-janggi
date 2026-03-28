package team.janggi.domain.strategy.boardstruct;

import team.janggi.domain.BoardStatus;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Empty;
import team.janggi.domain.strategy.layout.normal.NormalLayoutStrategy;

public class NormalBoardStrategy implements BoardStructStrategy {

    private final NormalLayoutStrategy layout;

    public NormalBoardStrategy(NormalLayoutStrategy layout) {
        this.layout = layout;
    }

    @Override
    public void initBoardStatus(BoardStatus status) {
        final int Normal_BOARD_ROW_SIZE = 10;
        final int Normal_BOARD_COL_SIZE = 9;

        initMapByEmpty(Normal_BOARD_ROW_SIZE, Normal_BOARD_COL_SIZE, status);

        layout.init(status);
    }

    private void initMapByEmpty(int rowSize, int colSize, BoardStatus status) {
        for (int row = 0; row < rowSize; row++) {
            for (int column = 0; column < colSize; column++) {
                status.setPiece(new Position(row, column), Empty.instance);
            }
        }
    }
}
