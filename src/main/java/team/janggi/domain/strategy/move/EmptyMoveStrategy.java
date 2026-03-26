package team.janggi.domain.strategy.move;

import java.util.Map;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.Position;

public class EmptyMoveStrategy implements MoveStrategy {

    public static final EmptyMoveStrategy instance = new EmptyMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, Map<Position, Piece> mapStatus) {
        return false;
    }
}
