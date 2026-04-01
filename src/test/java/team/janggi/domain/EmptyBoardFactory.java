package team.janggi.domain;

import team.janggi.domain.board.BoardFactory;
import team.janggi.domain.board.BoardStatus;

public class EmptyBoardFactory extends BoardFactory {


    public EmptyBoardFactory(JanggiFormation choSetup,
                             JanggiFormation hanSetup) {
        super(choSetup, hanSetup);
    }

    @Override
    public void batchPiece(BoardStatus boardStatus) {

    }
}
