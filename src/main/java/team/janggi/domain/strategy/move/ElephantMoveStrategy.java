package team.janggi.domain.strategy.move;

import java.util.Map;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.piece.PieceType;

public class ElephantMoveStrategy implements MoveStrategy {

    public static final ElephantMoveStrategy INSTANCE = new ElephantMoveStrategy();

    private ElephantMoveStrategy() {}

    public static ElephantMoveStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean calculateMove(Position from, Position to, Map<Position, Piece> mapStatus) {
        return validateDirection(from, to) && isPathBlock(from, to, mapStatus) && canKill(from, to, mapStatus);
    }

    private boolean validateDirection(Position from, Position to) {
        int dx = Math.abs(from.x() - to.x());
        int dy = Math.abs(from.y() - to.y());

        return (dx + dy) == 5;
    }

    private boolean isPathBlock(Position from, Position to, Map<Position, Piece> mapStatus) {
        int dx = Math.abs(from.x() - to.x());
        int dy = Math.abs(from.y() - to.y());

        int fromX = from.x();
        int fromY = from.y();

        if (dx > dy) {
            fromX += (to.x() - from.x()) / 3;
        }
        if (dy > dx) {
            fromY += (to.y() - from.y()) / 3;
        }

        Position obstaclePosition = new Position(fromX, fromY);
        Piece obstacle = mapStatus.get(obstaclePosition);
        if (!obstacle.isSamePieceType(PieceType.EMPTY)){
            return false;
        }

        int x2 = (to.x() - from.x()) / Math.abs(to.x() - from.x());
        int y2 = (to.y() - from.y()) / Math.abs(to.y() - from.y());

        Position obstaclePosition2 = new Position(fromX+x2, fromY+y2);
        Piece obstacle2 = mapStatus.get(obstaclePosition2);
        if (!obstacle2.isSamePieceType(PieceType.EMPTY)) {
            return false;
        }
        return true;
    }

    private boolean canKill(Position from, Position to, Map<Position, Piece> mapStatus) {
        Piece currentPiece = mapStatus.get(from);
        Piece definationPiece = mapStatus.get(to);
        return !currentPiece.isSameTeam(definationPiece);
    }

}
