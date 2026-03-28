package team.janggi.domain.strategy.move;

import java.util.Map;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.piece.Piece;

public class SoldierMoveStrategy implements MoveStrategy {
    public static final SoldierMoveStrategy instance = new SoldierMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, Map<Position, Piece> mapStatus) {
        Piece soldier = mapStatus.get(from);
        if (soldier.isSameTeam(Team.CHO)) {
            return canChoForward(from, to) || canSideMove(from, to);
        }
        
        if (soldier.isSameTeam(Team.HAN)) {
            return canHanForward(from, to) || canSideMove(from, to);
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
}
