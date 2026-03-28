package team.janggi.control;

import team.janggi.domain.Board;
import team.janggi.domain.strategy.boardstruct.NormalBoardStrategy;
import team.janggi.domain.strategy.layout.normal.NormalLayoutStrategy;
import team.janggi.domain.strategy.layout.normal.NormalSetup;
import team.janggi.view.ConsoleOutputView;

public class JanggiController {
    private final ConsoleOutputView consoleOutputView;

    public JanggiController() {
        this.consoleOutputView = new ConsoleOutputView();
    }

    public void run(){
        Board board = new Board(
                new NormalBoardStrategy(
                        new NormalLayoutStrategy(NormalSetup.왼상차림, NormalSetup.오른상차림)
                )
        );

        board.initBoard();
        consoleOutputView.print(board);
    }
}
