package team.janggi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.piece.PieceFactory;
import team.janggi.domain.piece.PieceType;

public class BoardPieceRepository {
    private final String URL = "jdbc:h2:file:./data/janggi;INIT=RUNSCRIPT FROM 'classpath:schema.sql'";
    private final String USER = "sa";
    private final String PASSWORD = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void saveBoardPiece(int game_id, Map<Position, Piece> boardPieceMap) {
        try {
            Connection connection = getConnection();
            String sql = "INSERT INTO board_piece(game_id, type, team, x_position, y_position) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (Map.Entry<Position, Piece> entry : boardPieceMap.entrySet()) {
                Piece piece = entry.getValue();
                Position position = entry.getKey();

                preparedStatement.setInt(1, game_id);
                preparedStatement.setString(2, piece.getPieceType().toString());
                preparedStatement.setString(3, piece.getTeam().toString());
                preparedStatement.setInt(4, position.x());
                preparedStatement.setInt(5, position.y());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Position, Piece> loadBoardPiece(int gameId) {
        Map<Position, Piece> boardPieceMap = new HashMap<>();
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM board_piece WHERE game_id = ?");
            statement.setInt(1, gameId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String type = resultSet.getString("type");
                String team = resultSet.getString("team");
                int x_position = resultSet.getInt("x_position");
                int y_position = resultSet.getInt("y_position");

                PieceType pieceType = PieceType.from(type);
                Team teamType = Team.from(team);
                Position position = new Position(x_position, y_position);
                Piece piece = PieceFactory.createPiece(pieceType, teamType);
                boardPieceMap.put(position, piece);

            }
            resultSet.close();
            statement.close();
            connection.close();

            return boardPieceMap;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
