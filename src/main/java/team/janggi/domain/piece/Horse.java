package team.janggi.domain.piece;

import team.janggi.domain.Team;
import team.janggi.domain.movestrategy.HorseMoveStrategy;

public class Horse extends Piece {

    public Horse(Team team) {
        super(team, PieceType.HORSE, HorseMoveStrategy.getInstance());
    }
}
