package vn.edu.t3h.employeemananger.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigInit {

    public static String DB_URL;
    public static String DB_USERNAME;
    public static String DB_PASSWORD;

    public static String BASE_URL;

    static {
        try (InputStream inputStream = ConfigInit.class.getClassLoader().getResourceAsStream("application.properties")){
            if( inputStream == null){
                throw new IOException("Unable to find application.properties");
            }

            Properties properties = new Properties();
            properties.load(inputStream);

            DB_URL = properties.getProperty("db.url");
            DB_USERNAME = properties.getProperty("db.username");
            DB_PASSWORD = properties.getProperty("db.password");
            // Đọc baseUrl từ config
            BASE_URL = properties.getProperty("baseUrl");

        }catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Failed to load configuration");
        }
    }
}
