package team.janggi.domain.strategy.move;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.piece.PieceType;

public class ChariotMoveStrategy implements MoveStrategy {

    public static final ChariotMoveStrategy instance = new ChariotMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, Map<Position, Piece> mapStatus) {
        if (!isAllowDirection(from, to)) {
            return false;
        }

        final List<Piece> paths = getPaths(from, to, mapStatus);

        if (isBlocked(paths)) {
            return false;
        }

        final Piece me = mapStatus.get(from);
        final Piece lastPiece = mapStatus.get(to);
        return canKill(lastPiece, me);
    }

    private boolean canKill(Piece target, Piece me) {
        return !target.isSameTeam(me);
    }

    private boolean isBlocked(List<Piece> paths) {
        if (paths.isEmpty()) {
            return false;
        }
        
        paths = new ArrayList<>(paths);
        paths.removeLast();

        return paths.stream()
                .anyMatch(piece -> !piece.isSamePieceType(PieceType.EMPTY));
    }

    private List<Piece> getPaths(Position from, Position to, Map<Position, Piece> mapStatus) {
        List<Piece> paths = new ArrayList<>();

        int dx = Integer.signum(to.x() - from.x());
        int dy = Integer.signum(to.y() - from.y());

        int currentX = from.x() + dx;
        int currentY = from.y() + dy;

        while (currentX != to.x() || currentY != to.y()) {
            paths.add(mapStatus.get(new Position(currentX, currentY)));
            currentX += dx;
            currentY += dy;
        }

        return paths;
    }

    public boolean isAllowDirection(Position from, Position to) {
        int dx = to.x() - from.x();
        int dy = to.y() - from.y();

        return (dx == 0 && dy > 0) || (dx == 0 && dy < 0) || (dx > 0 && dy == 0) || (dx < 0 && dy == 0);
    }
}
