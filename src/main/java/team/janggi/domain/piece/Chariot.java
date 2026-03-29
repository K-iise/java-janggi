package team.janggi.domain.piece;

import team.janggi.domain.Team;
import team.janggi.domain.strategy.move.ChariotMoveStrategy;

public class Chariot extends Piece {

    public Chariot(Team team) {
        super(team, PieceType.CHARIOT, ChariotMoveStrategy.instance);
    }

}
