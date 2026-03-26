package team.janggi.domain.strategy.move;

import java.util.Map;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;

public class CannonMoveStrategy implements MoveStrategy {
    public static final CannonMoveStrategy instance = new CannonMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, Map<Position, Piece> mapStatus) {
        return false;
    }
}
