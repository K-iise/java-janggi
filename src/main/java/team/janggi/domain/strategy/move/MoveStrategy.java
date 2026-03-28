package team.janggi.domain.strategy.move;

import java.util.Map;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;

public interface MoveStrategy {

    boolean calculateMove(Position from,
                          Position to,
                          Map<Position, Piece> mapStatus);
}
