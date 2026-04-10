package team.janggi.infra;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;

public class TransactionManager {
    private final ConnectionManager connectionManager;

    public TransactionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public <T> T executeTransaction(Function<Connection, T> function) {
        try (Connection connection = this.connectionManager.getConnection()) {
            connection.setAutoCommit(false);
            T apply = function.apply(connection);
            connection.commit();
            return apply;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeTransaction(Consumer<Connection> consumer) {
        try (Connection connection = connectionManager.getConnection()) {
            connection.setAutoCommit(false);
            consumer.accept(connection);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
