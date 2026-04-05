package team.janggi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;

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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
