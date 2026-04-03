package team.janggi.domain.movestrategy;

import java.util.Map;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.piece.Piece;

public class SoldierMoveStrategy implements MoveStrategy {
    private static final SoldierMoveStrategy INSTANCE = new SoldierMoveStrategy();

    private SoldierMoveStrategy() {
    }

    public static SoldierMoveStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean calculateMove(Position from, Position to, Map<Position, Piece> mapStatus) {
        Piece soldier = mapStatus.get(from);
        if (soldier.isSameTeam(Team.CHO)) {
            return (canChoForward(from, to) || canSideMove(from, to) || canChoDiagonalMove(from, to))
                    && !validateObstacle(from, to, mapStatus);
        }

        if (soldier.isSameTeam(Team.HAN)) {
            return (canHanForward(from, to) || canSideMove(from, to) || canHanDiagonalMove(from, to))
                    && !validateObstacle(
                    from, to, mapStatus);
        }

        return false;
    }

    private boolean canChoForward(Position from, Position to) {
        return from.y() - to.y() == 1 && from.x() == to.x();
    }

    private boolean canHanForward(Position from, Position to) {
        return to.y() - from.y() == 1 && from.x() == to.x();
    }

    private boolean canSideMove(Position from, Position to) {
        return from.y() == to.y() && Math.abs(from.x() - to.x()) == 1;
    }

    private boolean canChoDiagonalMove(Position from, Position to) {
        if (from.x() == 3 && from.y() == 2 && to.x() == 4 && to.y() == 1) {
            return true;
        }
        if (from.x() == 4 && from.y() == 1 && to.x() == 5 && to.y() == 0) {
            return true;
        }
        if (from.x() == 5 && from.y() == 2 && to.x() == 4 && to.y() == 1) {
            return true;
        }
        return from.x() == 4 && from.y() == 1 && to.x() == 3 && to.y() == 0;
    }

    private boolean canHanDiagonalMove(Position from, Position to) {
        if (from.x() == 3 && from.y() == 7 && to.x() == 4 && to.y() == 8) {
            return true;
        }
        if (from.x() == 5 && from.y() == 7 && to.x() == 4 && to.y() == 8) {
            return true;
        }
        if (from.x() == 4 && from.y() == 8 && to.x() == 3 && to.y() == 9) {
            return true;
        }
        return from.x() == 4 && from.y() == 8 && to.x() == 5 && to.y() == 9;
    }

    private boolean validateObstacle(Position from, Position to, Map<Position, Piece> mapStatus) {
        Piece toPiece = mapStatus.get(to);
        Piece fromPiece = mapStatus.get(from);
        return toPiece.isSameTeam(fromPiece);
    }
}
