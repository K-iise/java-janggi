package team.janggi.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import team.janggi.entity.Game;

public class GameRepository {

    public static final String SAVE_GAME_SQL = "INSERT INTO game (game_name, current_turn) VALUES (?, ?)";
    public static final String SELECT_GAME_SQL = "SELECT * FROM game;";
    public static final String SELECT_GAME_ID_SQL = "SELECT * FROM game WHERE id = ?";

    public int saveGame(String gameName, String currentTurn, Connection connection) {
        try (
                PreparedStatement statement = connection.prepareStatement(SAVE_GAME_SQL,
                        Statement.RETURN_GENERATED_KEYS);
                ResultSet generatedKeys = statement.getGeneratedKeys();
        ) {
            statement.setString(1, gameName);
            statement.setString(2, currentTurn);
            statement.executeUpdate();
            generatedKeys.next();
            return generatedKeys.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Game> loadGame(Connection connection) {
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_GAME_SQL);
                ResultSet resultSet = statement.executeQuery()
        ) {
            List<Game> loadGames = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String gameName = resultSet.getString("game_name");
                String currentTurn = resultSet.getString("current_turn");
                loadGames.add(new Game(id, gameName, currentTurn));
            }

            return loadGames;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Game findGameById(int gameId, Connection connection) {
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_GAME_ID_SQL);
                ResultSet resultSet = statement.executeQuery();
        ) {
            statement.setInt(1, gameId);
            resultSet.next();

            int id = resultSet.getInt("id");
            String gameName = resultSet.getString("game_name");
            String currentTurn = resultSet.getString("current_turn");

            return new Game(id, gameName, currentTurn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
