package team.janggi.domain.strategy.move;

import java.util.Map;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;

public class GuardMoveStrategy implements MoveStrategy {

    public static final GuardMoveStrategy instance = new GuardMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, Map<Position, Piece> mapStatus) {
        return false;
    }
}
