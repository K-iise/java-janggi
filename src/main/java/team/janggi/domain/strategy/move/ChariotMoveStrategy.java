package team.janggi.domain.strategy.move;

import java.util.Map;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;

public class ChariotMoveStrategy implements MoveStrategy{

    public static final ChariotMoveStrategy instance = new ChariotMoveStrategy();
    @Override
    public boolean calculateMove(Position from, Position to, Map<Position, Piece> mapStatus) {
        return false;
    }
}
