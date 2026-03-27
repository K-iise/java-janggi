package team.janggi.domain.strategy.move;

import java.util.Map;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;

public interface MoveStrategy {
    MoveStrategy emptyMoveStrategy = new EmptyMoveStrategy();
    MoveStrategy kingMoveStrategy = new KingMoveStrategy();
    MoveStrategy CannonMoveStrategy = new CannonMoveStrategy();
    MoveStrategy SoldierMoveStrategy = new SoldierMoveStrategy();
    MoveStrategy HorseMoveStrategy = new HorseMoveStrategy();
    MoveStrategy GuardMoveStrategy = new GuardMoveStrategy();
    MoveStrategy ElephantMoveStrategy = new ElephantMoveStrategy();



    boolean calculateMove(Position from,
                          Position to,
                          Map<Position, Piece> mapStatus);
}
