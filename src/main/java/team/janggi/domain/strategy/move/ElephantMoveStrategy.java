package team.janggi.domain.strategy.move;

import java.util.Map;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;

public class ElephantMoveStrategy implements MoveStrategy {

    public static final ElephantMoveStrategy instance = new ElephantMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, Map<Position, Piece> mapStatus) {
        return false;
    }
}
