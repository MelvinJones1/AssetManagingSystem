package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
    public static String getConnectionString(String fileName) {
        String url = null;
        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream(fileName)) {
            props.load(fis);

            url = props.getProperty("url") + "/" + props.getProperty("database")
                    + "?user=" + props.getProperty("user")
                    + "&password=" + props.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }
}
