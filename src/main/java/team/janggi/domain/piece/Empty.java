package team.janggi.domain.piece;

import team.janggi.domain.Team;
import team.janggi.domain.strategy.move.EmptyMoveStrategy;

public class Empty extends Piece {
    public static final Empty instance = new Empty(Team.NONE);

    private Empty(Team team) {
        super(team, PieceType.EMPTY, EmptyMoveStrategy.getInstance());
    }
}
