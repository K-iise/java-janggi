package team.janggi.service;

import java.util.List;
import team.janggi.domain.Team;
import team.janggi.domain.board.Board;
import team.janggi.entity.Game;
import team.janggi.repository.BoardPieceRepository;
import team.janggi.repository.GameRepository;

public class GameService {

    private final GameRepository gameRepository;
    private final BoardPieceRepository boardPieceRepository;

    public GameService(GameRepository gameRepository, BoardPieceRepository boardPieceRepository) {
        this.gameRepository = gameRepository;
        this.boardPieceRepository = boardPieceRepository;
    }

    public void saveGame(String gameName, Team team, Board board) {
        String currentTurn = team.name();
        int game_id = gameRepository.saveGame(gameName, currentTurn);
        boardPieceRepository.saveBoardPiece(game_id, board.getStatus());
    }

    public List<Game> getGames() {
        return gameRepository.loadGame();
    }
}
