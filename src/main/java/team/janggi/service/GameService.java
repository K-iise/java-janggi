package team.janggi.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import team.janggi.domain.JanggiFormation;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.board.Board;
import team.janggi.domain.board.BoardFactory;
import team.janggi.domain.piece.Piece;
import team.janggi.entity.Game;
import team.janggi.infra.ConnectionManager;
import team.janggi.repository.BoardPieceRepository;
import team.janggi.repository.GameRepository;

public class GameService {

    private final GameRepository gameRepository;
    private final BoardPieceRepository boardPieceRepository;
    private final ConnectionManager connectionManager;

    public GameService(GameRepository gameRepository, BoardPieceRepository boardPieceRepository,
                       ConnectionManager connectionManager) {
        this.gameRepository = gameRepository;
        this.boardPieceRepository = boardPieceRepository;
        this.connectionManager = connectionManager;
    }

    public void saveGame(String gameName, Team team, Board board) {
        String currentTurn = team.name();
        try (Connection connection = connectionManager.getConnection()) {
            connection.setAutoCommit(false);
            int gameId = gameRepository.saveGame(gameName, currentTurn, connection);
            boardPieceRepository.saveBoardPiece(gameId, board.getStatus(), connection);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Game> getGames() {
        try (Connection connection = connectionManager.getConnection()) {
            return gameRepository.loadGame(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Board loadBoard(int gameId) {
        try (Connection connection = connectionManager.getConnection()) {
            Map<Position, Piece> boardPiece = boardPieceRepository.loadBoardPiece(gameId, connection);
            return new Board(boardPiece, new BoardFactory(JanggiFormation.EHHE, JanggiFormation.EHHE));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Team loadTurn(int gameId) {
        try (Connection connection = connectionManager.getConnection()) {
            Game game = gameRepository.findGameById(gameId, connection);
            return Team.from(game.getCurrentTurn());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
