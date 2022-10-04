package company.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DatabaseConfigReader.getConfig(ConfigParametersDB.URL),DatabaseConfigReader.getConfig(ConfigParametersDB.USERNAME),DatabaseConfigReader.getConfig(ConfigParametersDB.PASSWORD));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection(){
        return connection;
    }

    public static PreparedStatement getPrepareStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }



}
