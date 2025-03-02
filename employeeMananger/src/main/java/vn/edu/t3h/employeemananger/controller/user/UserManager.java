package vn.edu.t3h.employeemananger.controller.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.employeemananger.dao.RoleDao;
import vn.edu.t3h.employeemananger.dao.UserDao;
import vn.edu.t3h.employeemananger.dao.impl.RoleDaoImpl;
import vn.edu.t3h.employeemananger.dao.impl.UserDaoImpl;
import vn.edu.t3h.employeemananger.model.UserModel;
import vn.edu.t3h.employeemananger.service.Impl.UserServiceImpl;
import vn.edu.t3h.employeemananger.service.UserService;

import java.io.IOException;

@WebServlet(value = "/user/userManager")
public class UserManager extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        UserDao userDao = new UserDaoImpl();
        RoleDao roleDao = new RoleDaoImpl();
        userService = new UserServiceImpl(userDao,roleDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        if(userId != null){
            UserModel userModel= userService.findUserById(userId);
            req.setAttribute("userModel",userModel);
        } else {
            req.setAttribute("userModel",null);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/User/addUser.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.userManager(req);
        resp.sendRedirect("/login");
//        userService.insertUser(userModel);
    }
}
