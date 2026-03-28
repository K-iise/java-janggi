package team.janggi.domain;

import java.util.Map;
import team.janggi.domain.piece.Piece;

public interface BoardStatus {

    void movePiece(Team team, Position source, Position destination);

    void setPiece(Position position, Piece piece);

    Map<Position, Piece> getBoardStatus();

}
