package team.janggi.domain.piece;

public enum PieceType {
    KING(0),
    GUARD(3),
    HORSE(5),
    ELEPHANT(3),
    CHARIOT(13),
    CANNON(7),
    SOLDIER(2),
    EMPTY(0);

    PieceType(int pieceScore) {
        this.pieceScore = pieceScore;
    }

    final int pieceScore;

    public int getPieceScore() {
        return pieceScore;
    }
}
