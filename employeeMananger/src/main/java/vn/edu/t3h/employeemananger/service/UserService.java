package vn.edu.t3h.employeemananger.service;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.t3h.employeemananger.model.UserModel;

public interface UserService {
    public String login (String username, String password, HttpServletRequest request);

    UserModel findUserById(String userId);

    UserModel findUserByUserAndPassword(String username, String password);

    int userManager (HttpServletRequest request);
}
