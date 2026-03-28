package team.janggi.domain;

import java.util.Map;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.strategy.boardstruct.BoardStructStrategy;

public class Board {
    private final BoardStatus boardStatus;
    private final BoardStructStrategy boardStructStrategy;

    public Board(BoardStructStrategy boardStructStrategy) {
        this(new LocalMemoryBoardStatus(), boardStructStrategy);
    }

    public Board(BoardStatus boardStatus, BoardStructStrategy boardStructStrategy) {
        this.boardStatus = boardStatus;
        this.boardStructStrategy = boardStructStrategy;
    }

    public void initBoard() {
        boardStructStrategy.initBoardStatus(boardStatus);
    }

    public Map<Position, Piece> getStatus() {
        return boardStatus.getBoardStatus();
    }

    public void move(Team team, Position from, Position to) {
        boardStatus.movePiece(team, from, to);
    }

}
