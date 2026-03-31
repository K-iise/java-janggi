package team.janggi.domain.piece;

import team.janggi.domain.Team;
import team.janggi.domain.movestrategy.CannonMoveStrategy;

public class Cannon extends Piece {

    public Cannon(Team team) {
        super(team, PieceType.CANNON, CannonMoveStrategy.getInstance());
    }
}
