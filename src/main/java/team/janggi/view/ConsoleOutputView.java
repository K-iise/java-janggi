package team.janggi.view;

import java.util.Map;
import team.janggi.domain.board.Board;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.piece.PieceType;

public class ConsoleOutputView {
    private static final int ROW_SIZE = 10;
    private static final int COLUMN_SIZE = 9;
    private static final String SPACE = " ";
    private static final String HEADER_LEFT_PADDING = "   ";
    private static final String EMPTY_TEXT = "";
    private static final String EMPTY_SYMBOL = "．";
    private static final String UNKNOWN_SYMBOL = "？";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RED = "\u001B[31m";

    public void print(Board board) {
        final Map<Position, Piece> status = board.getStatus();
        final int totalCellCount = ROW_SIZE * COLUMN_SIZE;

        printColumnHeader();
        for (int index = 0; index < totalCellCount; index++) {
            int row = index / COLUMN_SIZE;
            int col = index % COLUMN_SIZE;
            printText(rowPrefix(row, col));

            Piece piece = status.get(new Position(row, col));
            String cellText = toSymbol(piece);
            printText(applyTeamColor(piece, cellText) + SPACE);
            printText(rowSuffix(col));
        }
    }

    private void printColumnHeader() {
        printText(HEADER_LEFT_PADDING);
        for (int col = 0; col < COLUMN_SIZE; col++) {
            printText(toFullWidthDigit(col) + SPACE);
        }
        printLine();
    }

    private String toSymbol(Piece piece) {
        if (piece == null || piece.isSamePieceType(PieceType.EMPTY)) {
            return EMPTY_SYMBOL;
        }

        return pieceCode(piece);
    }

    private String pieceCode(Piece piece) {
        final boolean isCho = piece.isSameTeam(Team.CHO);

        return switch (piece.getPieceType()) {
            case KING -> isCho ? "宮" : "將";
            case GUARD -> "士";
            case HORSE -> "馬";
            case ELEPHANT -> "象";
            case CHARIOT -> "車";
            case CANNON -> isCho ? "包" : "砲";
            case SOLDIER -> isCho ? "卒" : "兵";
            default -> UNKNOWN_SYMBOL;
        };
    }

    private String applyTeamColor(Piece piece, String value) {
        if (piece == null || piece.isSamePieceType(PieceType.EMPTY)) {
            return value;
        }

        if (piece.isSameTeam(Team.CHO)) {
            return ANSI_BLUE + value + ANSI_RESET;
        }
        if (piece.isSameTeam(Team.HAN)) {
            return ANSI_RED + value + ANSI_RESET;
        }
        return value;
    }

    private String toFullWidthDigit(int value) {
        return String.valueOf((char) ('０' + value));
    }

    private String rowPrefix(int row, int col) {
        if (col == 0) {
            return String.format("%2d%s", row, SPACE);
        }

        return EMPTY_TEXT;
    }

    private String rowSuffix(int col) {
        if (col == COLUMN_SIZE - 1) {
            return System.lineSeparator();
        }

        return EMPTY_TEXT;
    }

    private void printText(String text) {
        System.out.print(text);
    }

    private void printLine() {
        System.out.println();
    }

}
