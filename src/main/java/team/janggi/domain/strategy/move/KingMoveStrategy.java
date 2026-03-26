package team.janggi.domain.strategy.move;

import java.util.Map;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;

public class KingMoveStrategy implements MoveStrategy {
    private static final int MAX_MOVE_DISTANCE = 1;

    public static final KingMoveStrategy instance = new KingMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, Map<Position, Piece> mapStatus) {
        return false;
    }

}
