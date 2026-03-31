package team.janggi.domain.piece;

import team.janggi.domain.Team;
import team.janggi.domain.strategy.move.SoldierMoveStrategy;

public class Soldier extends Piece {

    public Soldier(Team team) {
        super(team, PieceType.SOLDIER, SoldierMoveStrategy.getInstance());
    }
}
