package team.janggi.domain;

import java.util.HashMap;
import java.util.Map;
import team.janggi.domain.piece.Piece;

public class LocalMemoryBoardStatus implements BoardStatus {
    private final Map<Position, Piece> map;

    public LocalMemoryBoardStatus() {
        this.map = new HashMap<>();
    }

    @Override
    public Piece getPiece(Position position) {
        return null;
    }

    @Override
    public void movePiece(Position source, Position destination) {

    }

    @Override
    public void setPiece(Position position, Piece piece) {
        map.put(position, piece);
    }

    @Override
    public Map<Position, Piece> getBoardStatus() {
        return new HashMap<>(map);
    }
}
