package team.janggi.control;

import java.util.List;
import java.util.Map;
import team.janggi.domain.board.Board;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.board.BoardFactory;
import team.janggi.domain.JanggiFormation;
import team.janggi.view.ConsoleInputView;
import team.janggi.view.ConsoleOutputView;

public class JanggiController {
    private final ConsoleOutputView consoleOutputView;
    private final ConsoleInputView consoleInputView;

    public JanggiController() {
        this.consoleOutputView = new ConsoleOutputView();
        this.consoleInputView = new ConsoleInputView();
    }

    public void run() {
        final Board board = createBoard();
        board.initBoard();

        Team currentTurn = Team.CHO;
        while (true) {
            currentTurn = doTurn(board, currentTurn);
        }
    }

    private Board createBoard() {
        final JanggiFormation choSetup = getChoFormation();
        final JanggiFormation hanSetup = getHanFormation();

        final BoardFactory boardFactory = new BoardFactory(choSetup, hanSetup);
        return new Board(boardFactory);
    }

    private JanggiFormation getChoFormation() {
        while (true) {
            try {
                int choNumber = consoleInputView.readChoFormation();
                return JanggiFormation.from(choNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private JanggiFormation getHanFormation() {
        while (true) {
            try {
                int hanNumber = consoleInputView.readHanFormation();
                return JanggiFormation.from(hanNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Team doTurn(Board board, Team currentTurn) {
        consoleOutputView.print(board);
        final Position from = getSourcePosition(currentTurn);
        final Position to = getDestinationPosition(currentTurn);

        try {
            board.move(currentTurn, from, to);
            return nextTeam(currentTurn);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return currentTurn;
        }
    }

    private Position getSourcePosition(Team currentTurn) {
        while (true) {
            try {
                List<Integer> sourcePosition = consoleInputView.readSourcePosition(currentTurn);
                return Position.of(sourcePosition.get(0), sourcePosition.get(1));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Position getDestinationPosition(Team currentTurn) {
        while (true) {
            try {
                List<Integer> destinationPosition = consoleInputView.readDestinationPosition(currentTurn);
                return Position.of(destinationPosition.get(0), destinationPosition.get(1));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Team nextTeam(Team team) {
        return Map.of(Team.CHO, Team.HAN, Team.HAN, Team.CHO).get(team);
    }
}
