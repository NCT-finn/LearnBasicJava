package vn.edu.t3h.employeemananger;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CommonConnection extends HttpServlet {
    static String DATABASE_USERNAME = null;
    static String DATABASE_PASSWORD = null;
    static String DATABASE_URL = null;
    private static Connection connection = null;

    @Override
    public void init(ServletConfig config) throws ServletException {

        if (DATABASE_URL == null) DATABASE_URL = config.getServletContext().getInitParameter("DATABASE_URL");
        if (DATABASE_USERNAME == null) DATABASE_USERNAME = config.getServletContext().getInitParameter("DATABASE_USERNAME");
        if (DATABASE_PASSWORD == null) DATABASE_PASSWORD = config.getServletContext().getInitParameter("DATABASE_PASSWORD");
    }

    public static Connection getConnection(){
        try {
            if(connection == null || connection.isClosed()){
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                System.out.println(" get connection success");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
