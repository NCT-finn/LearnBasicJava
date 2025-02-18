package vn.edu.t3h.employeemananger.dao;


import vn.edu.t3h.employeemananger.model.UserModel;

public interface UserDao {

    UserModel findUserByUserNameAndPassword(String username, String password);

    UserModel findUserById(String id);

    int insertUser(UserModel userModel);

    int updateUser(UserModel userModel);
}
