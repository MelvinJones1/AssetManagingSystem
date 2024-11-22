package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

    public static Connection getConnection() {

        Connection con = null;
        String fileName = "db.properties";
        try {
            String url = DBPropertyUtil.getConnectionString(fileName);
            con = DriverManager.getConnection(url);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
