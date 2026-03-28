package team.janggi.domain;

import java.util.HashMap;
import java.util.Map;
import team.janggi.domain.piece.Empty;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.piece.PieceType;

public class LocalMemoryBoardStatus implements BoardStatus {
    private final Map<Position, Piece> map;

    public LocalMemoryBoardStatus() {
        this.map = new HashMap<>();
    }

    @Override
    public void movePiece(Team team, Position from, Position to) {
        validateTeam(team, from);
        validatePosition(from, to);

        final Piece piece = getPiece(from);

        if (!piece.canMove(from, to, getBoardStatus())) {
            throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
        }

        map.put(to, piece);
        map.put(from, Empty.instance);
    }

    @Override
    public void setPiece(Position position, Piece piece) {
        map.put(position, piece);
    }

    @Override
    public Map<Position, Piece> getBoardStatus() {
        return new HashMap<>(map);
    }

    private Piece getPiece(Position position) {
        return map.get(position);
    }

    private void validateTeam(Team team, Position from) {
        final Piece piece = getPiece(from);

        if (!piece.isSameTeam(team)) {
            throw new IllegalArgumentException("자신의 기물만 이동할 수 있습니다.");
        }
    }

    private void validatePosition(Position from, Position to) {
        if (isOutOfBounds(from) || isOutOfBounds(to)) {
            throw new IllegalArgumentException("위치가 보드 범위를 벗어났습니다.");
        }

        if (isEmptySpace(from)) {
            throw new IllegalArgumentException("이동할 위치에 기물이 없습니다.");
        }
    }

    private boolean isEmptySpace(Position from) {
        return map.get(from).isSamePieceType(PieceType.EMPTY);
    }

    private boolean isOutOfBounds(Position position) {
        return !map.containsKey(position);
    }
}
