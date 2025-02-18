package vn.edu.t3h.employeemananger.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.employeemananger.model.UserModel;
import vn.edu.t3h.employeemananger.utils.SessionUtil;

import java.io.IOException;

@WebServlet(value = "/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lấy current user thông qua session, để kiểm tra xem user đã đăng nhập chưa
        UserModel currentUser = (UserModel) SessionUtil.getValue(req, SessionUtil.SESSION_ID_CURRENT_USER);
        if (currentUser == null) {
            System.out.println("Current User is null");
        } else {
            System.out.println("Current User: " + currentUser.toString());
        }
        req.setAttribute("currentUser", currentUser);
        req.setAttribute("message", "hello");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/home-page.jsp");
        requestDispatcher.forward(req, resp);

    }
}
