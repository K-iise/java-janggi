package team.janggi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import team.janggi.entity.Game;

public class GameRepository {

    private final String URL = "jdbc:h2:file:./data/janggi;INIT=RUNSCRIPT FROM 'classpath:schema.sql'";
    private final String USER = "sa";
    private final String PASSWORD = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public int saveGame(String gameName, String currentTurn) {
        try {
            Connection connection = getConnection();
            String sql = "INSERT INTO game (game_name, current_turn) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, gameName);
            statement.setString(2, currentTurn);
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // 생성된 id 반환
                } else {
                    throw new SQLException("게임 저장 중 ID를 가져오지 못했습니다.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Game> loadGame() {
        try {
            List<Game> loadGames = new ArrayList<>();
            Connection connection = getConnection();
            String sql = "SELECT * FROM game;";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String gameName = resultSet.getString("game_name");
                String currentTurn = resultSet.getString("current_turn");
                loadGames.add(new Game(id, gameName, currentTurn));
            }

            resultSet.close();
            statement.close();
            connection.close();
            return loadGames;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
