package team.janggi.service;

import java.util.List;
import java.util.Map;
import team.janggi.domain.JanggiFormation;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.board.Board;
import team.janggi.domain.board.BoardFactory;
import team.janggi.domain.piece.Piece;
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
        int gameId = gameRepository.saveGame(gameName, currentTurn);
        boardPieceRepository.saveBoardPiece(gameId, board.getStatus());
    }

    public List<Game> getGames() {
        return gameRepository.loadGame();
    }

    public Board loadBoard(int gameId) {
        Map<Position, Piece> boardPiece = boardPieceRepository.loadBoardPiece(gameId);
        return new Board(boardPiece, new BoardFactory(JanggiFormation.EHHE, JanggiFormation.EHHE));
    }

    public Team loadTurn(int gameId) {
        Game game = gameRepository.findGameById(gameId);
        return Team.from(game.getCurrentTurn());
    }
}
