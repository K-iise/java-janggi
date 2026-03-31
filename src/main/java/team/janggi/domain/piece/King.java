package team.janggi.domain.piece;

import team.janggi.domain.Team;
import team.janggi.domain.movestrategy.KingMoveStrategy;

public class King extends Piece {

    public King(Team team) {
        super(team, PieceType.KING, KingMoveStrategy.getInstance());
    }
}
