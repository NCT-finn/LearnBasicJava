package vn.edu.t3h.employeemananger.security;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    public String login(String username, String password, HttpServletRequest request);
}
