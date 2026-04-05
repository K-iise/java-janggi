package team.janggi.control;

import java.util.List;
import java.util.Map;
import team.janggi.domain.JanggiFormation;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.board.Board;
import team.janggi.domain.board.BoardFactory;
import team.janggi.entity.Game;
import team.janggi.repository.BoardPieceRepository;
import team.janggi.repository.GameRepository;
import team.janggi.service.GameService;
import team.janggi.util.Parser;
import team.janggi.view.ConsoleInputView;
import team.janggi.view.ConsoleOutputView;

public class JanggiController {
    private final ConsoleOutputView consoleOutputView;
    private final ConsoleInputView consoleInputView;
    private final GameService gameService;

    public JanggiController() {
        this.consoleOutputView = new ConsoleOutputView();
        this.consoleInputView = new ConsoleInputView();
        this.gameService = new GameService(new GameRepository(), new BoardPieceRepository());
    }

    public void run() {
        int option = consoleInputView.readStartOption();
        if (option == 1) {
            loadAndResumeGame();
            return;
        }
        startNewGame();
    }

    public void startNewGame() {
        final Board board = createBoard();
        board.initBoard();

        Team currentTurn = Team.CHO;
        GameLoop(board, currentTurn);
    }

    private void loadAndResumeGame() {
        List<Game> games = gameService.getGames();
        consoleOutputView.printGames(games);

        if (games.isEmpty()) {
            consoleOutputView.printNoSavedGames();
            startNewGame();
            return;
        }

        int selectedId = getSelectedGameId(games);

        if (selectedId == games.size() + 1) {
            startNewGame();
            return;
        }

        Board board = gameService.loadBoard(selectedId);
        Team currentTurn = gameService.loadTurn(selectedId);
        GameLoop(board, currentTurn);
    }

    private void GameLoop(Board board, Team currentTurn) {
        while (true) {
            printCurrentScores(board);
            currentTurn = doTurn(board, currentTurn);

            if (board.isKingDisappeared()) {
                consoleOutputView.printWinner(currentTurn);
                break;
            }
        }
    }

    private int getSelectedGameId(List<Game> games) {
        while (true) {
            try {
                int selectedId = consoleInputView.readGameId();
                if (selectedId == games.size() + 1) {
                    return selectedId;
                }
                games.stream()
                        .filter(game -> game.getId() == selectedId)
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 게임 번호입니다."));
                return selectedId;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printCurrentScores(Board board) {
        consoleOutputView.printScore(Team.CHO, board.getScore(Team.CHO));
        consoleOutputView.printScore(Team.HAN, board.getScore(Team.HAN));
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

        try {
            String input = consoleInputView.readCommand(currentTurn);

            if (input.equals("save")) {
                String gameName = consoleInputView.readGameName();
                gameService.saveGame(gameName, currentTurn, board);
                System.out.println(gameName + " 을 저장했습니다. 게임이 종료됩니다.");
                System.exit(0);
            }

            final Position from = getSourcePosition(input);
            final Position to = getDestinationPosition(currentTurn);

            board.move(currentTurn, from, to);
            return nextTeam(currentTurn);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return currentTurn;
        }
    }

    private Position getSourcePosition(String input) {
        while (true) {
            try {
                List<Integer> positions = Parser.parseByDelimiter(input, "[ERROR] 좌표는 숫자 형식이어야 합니다.");
                return Position.of(positions.get(0), positions.get(1));
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
