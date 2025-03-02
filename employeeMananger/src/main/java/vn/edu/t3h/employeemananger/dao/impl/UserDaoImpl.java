package vn.edu.t3h.employeemananger.dao.impl;

import vn.edu.t3h.employeemananger.dao.UserDao;
import vn.edu.t3h.employeemananger.model.UserModel;
import vn.edu.t3h.employeemananger.utils.DatabaseConnection;
import vn.edu.t3h.employeemananger.utils.PasswordUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static vn.edu.t3h.employeemananger.utils.PasswordUtils.decryptPassword;
import static vn.edu.t3h.employeemananger.utils.StringUtils.toInterger;

public class UserDaoImpl implements UserDao {

    private static PasswordUtils passwordUtils = new PasswordUtils();

    @Override
    public UserModel findUserByUserNameAndPassword(String username, String password) {
        UserModel userModel = null;
        String sql = "SELECT u.id, u.username, u.password, u.full_name, u.deleted, u.role_id, r.name AS role_name " +
                "FROM user u " +
                "JOIN role r ON u.role_id = r.id " +
                "WHERE u.username = ? AND u.password = ? AND u.deleted = 0";

        // nếu viết trong try thì connection sẽ tự động được close, áp dụng cho version jdbc  moi
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username); // Set tên đăng nhập
            preparedStatement.setString(2, password); // Set mật khẩu
            // tuong tu connection
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Lấy dữ liệu từ kết quả truy vấn và gán vào đối tượng UserModel
                    Integer id = resultSet.getInt("id");
                    String fullName = resultSet.getString("full_name");
                    boolean deleted = resultSet.getBoolean("deleted");
                    Integer roleId = resultSet.getInt("role_id");

                    // Tạo đối tượng UserModel từ dữ liệu truy vấn
                    userModel = new UserModel(id, username, password, fullName, deleted, roleId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userModel;
    }

    @Override
    public UserModel findUserById(String UserId) {
        UserModel userModel = null;
        String sql = "SELECT *" +
                "FROM user  " +
                "WHERE id = ?";

        // nếu viết trong try thì connection sẽ tự động được close, áp dụng cho version jdbc  moi
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, toInterger(UserId)); // Set tên đăng nhập
            // tuong tu connection
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Lấy dữ liệu từ kết quả truy vấn và gán vào đối tượng UserModel
                    Integer id = resultSet.getInt("id");
                    String fullName = resultSet.getString("full_name");
                    String username = resultSet.getString("username");
                    String password = decryptPassword(resultSet.getString("password"));
                    boolean deleted = resultSet.getBoolean("deleted");
                    Integer roleId = resultSet.getInt("role_id");

                    // Tạo đối tượng UserModel từ dữ liệu truy vấn
                    userModel = new UserModel(id, username, password, fullName, deleted, roleId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userModel;
    }

    @Override
    public int insertUser(UserModel userModel) {
        String sql = "insert into user(username,password,full_name,deleted,role_id)" +
                " values (?,?,?,?,?);";
        int rowsInsert = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userModel.getUsername()); // Set tên đăng nhập
            preparedStatement.setString(2, passwordUtils.encryptPassword(userModel.getPassword())); // Set mật khẩu
            preparedStatement.setString(3, userModel.getFull_name());
            preparedStatement.setBoolean(4,userModel.getDeleted());
            preparedStatement.setInt(5,userModel.getRole_id());
            rowsInsert = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsInsert;
    }

    @Override
    public int updateUser(UserModel userModel) {
        String sql = "update user set username = ?,password = ?,full_name = ?, deleted = ?, role_id = ? where id = ?;";
        int rowsUpdate = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userModel.getUsername()); // Set tên đăng nhập
            preparedStatement.setString(2, passwordUtils.encryptPassword(userModel.getPassword())); // Set mật khẩu
            preparedStatement.setString(3, userModel.getFull_name());
            preparedStatement.setBoolean(4,userModel.getDeleted());
            preparedStatement.setInt(5,userModel.getRole_id());
            preparedStatement.setInt(6,userModel.getId());
            rowsUpdate = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsUpdate;
    }
}
