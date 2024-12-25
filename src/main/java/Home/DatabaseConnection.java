package Home;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://DESKTOP-07RR9PT:1433;"
            + "user=sa;"
            + "password=123;"
            + "databaseName=KiThiTotNghiep;"
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "loginTimeout=3;";
    private static Connection connection;
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) connection = DriverManager.getConnection(URL);
        return connection;
    }
    public static void closeConnection() throws SQLException {
        if (connection != null || !connection.isClosed()) connection.close();

    }
}
