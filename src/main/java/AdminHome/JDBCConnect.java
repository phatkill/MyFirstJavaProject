package AdminHome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnect {
    private static final String URL = "jdbc:sqlserver://phatkill:1433;"
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
