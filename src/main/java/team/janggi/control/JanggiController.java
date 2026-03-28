package team.janggi.control;

import java.util.Map;
import team.janggi.domain.Board;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.strategy.boardstruct.NormalBoardStrategy;
import team.janggi.domain.strategy.layout.normal.NormalLayoutStrategy;
import team.janggi.domain.strategy.layout.normal.NormalSetup;
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

        final NormalLayoutStrategy layout = new NormalLayoutStrategy(choSetup, hanSetup);
        final NormalBoardStrategy boardStructStrategy = new NormalBoardStrategy(layout);
        return new Board(boardStructStrategy);
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
