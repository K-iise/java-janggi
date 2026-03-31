package team.janggi.control;

import java.util.Map;
import team.janggi.domain.board.Board;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.board.BoardFactory;
import team.janggi.domain.NormalSetup;
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
        final NormalSetup choSetup = consoleInputView.readChoNormalSetup();
        final NormalSetup hanSetup = consoleInputView.readHanNormalSetup();

        final BoardFactory boardFactory = new BoardFactory(choSetup, hanSetup);
        return new Board(boardFactory);
    }

    private Team doTurn(Board board, Team currentTurn) {
        consoleOutputView.print(board);
        final Position from = consoleInputView.readMoveSource(currentTurn);
        final Position to = consoleInputView.readMoveDestination(currentTurn);

        try {
            board.move(currentTurn, from, to);
            return nextTeam(currentTurn);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return currentTurn;
        }
    }

    private Team nextTeam(Team team) {
        return Map.of(Team.CHO, Team.HAN, Team.HAN, Team.CHO).get(team);
    }
}
