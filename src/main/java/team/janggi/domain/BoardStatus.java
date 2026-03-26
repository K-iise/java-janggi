package team.janggi.domain;

import java.util.Map;
import team.janggi.domain.piece.Piece;

public interface BoardStatus {

    Piece getPiece(Position position);

    void movePiece(Position source, Position destination);

    void setPiece(Position position, Piece piece);

    Map<Position, Piece> getBoardStatus();

}
