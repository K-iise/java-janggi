package team.janggi.domain.strategy.move;

import java.util.Map;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;

public class GuardMoveStrategy implements MoveStrategy {
    private static final int MAX_MOVE_DISTANCE = 1;

    public static final GuardMoveStrategy instance = new GuardMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, Map<Position, Piece> mapStatus) {
        return validateDirection(from, to) && !validateObstacle(from, to, mapStatus);
    }

    private boolean validateDirection(Position from, Position to) {
        int dx = Math.abs(from.x() - to.x());
        int dy = Math.abs(from.y() - to.y());

        return (dx + dy) == MAX_MOVE_DISTANCE || (dx == MAX_MOVE_DISTANCE) && (dy == MAX_MOVE_DISTANCE);
    }

    private boolean validateObstacle(Position from, Position to, Map<Position, Piece> mapStatus) {
        Piece toPiece = mapStatus.get(to);
        Piece fromPiece = mapStatus.get(from);
        return toPiece.isSameTeam(fromPiece);
    }
}
