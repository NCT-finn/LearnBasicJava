package vn.edu.t3h.employeemananger.service.Impl;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.t3h.employeemananger.dao.RoleDao;
import vn.edu.t3h.employeemananger.dao.UserDao;
import vn.edu.t3h.employeemananger.model.RoleModel;
import vn.edu.t3h.employeemananger.model.UserModel;
import vn.edu.t3h.employeemananger.service.UserService;
import vn.edu.t3h.employeemananger.utils.Constants;

import vn.edu.t3h.employeemananger.utils.PasswordUtils;
import vn.edu.t3h.employeemananger.utils.StringUtils;

import static vn.edu.t3h.employeemananger.utils.StringUtils.toInterger;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public String login(String username, String password, HttpServletRequest request) {
        String passwordEncrypted = PasswordUtils.encryptPassword(password);
        UserModel userModel = userDao.findUserByUserNameAndPassword(username,passwordEncrypted);
        String urlRedirect = "";
        if(userModel == null){
            urlRedirect = "/login?message=" + Constants.LOGIN_ERROR;
            return urlRedirect;
        }

        RoleModel roleModel = roleDao.findRoleById(userModel.getRole_id());
        if(roleModel == null){
            urlRedirect = "/login?message=" + Constants.PERMISSION_DENIED;
            return urlRedirect;
        }
        request.getSession().setAttribute(Constants.SESSION_ID_CURRENT_USER,userModel);
        request.setAttribute("userModel",userModel);
        if(roleModel.getCode().equals(Constants.ROLE.ROLE_ADMIN.name())){
            urlRedirect = "/employee";
        }else {
            urlRedirect = "/home";
        }
        return urlRedirect;
    }

    @Override
    public UserModel findUserById(String userId) {
        return userDao.findUserById(userId);
    }

    @Override
    public UserModel findUserByUserAndPassword(String username, String password) {
        String passwordEncrypt = PasswordUtils.encryptPassword(password);
        return userDao.findUserByUserNameAndPassword(username,password);
    }

    @Override
    public int userManager(HttpServletRequest req) {
        UserModel userModel = new UserModel();
        userModel.setId(toInterger(req.getParameter("userId")));
        userModel.setUsername(req.getParameter("username"));
        userModel.setPassword(req.getParameter("password"));
        userModel.setFull_name(req.getParameter("full_name"));
        userModel.setDeleted(Boolean.parseBoolean(req.getParameter("deleted")));
        userModel.setRole_id(toInterger(req.getParameter("role")));
        int numberRowExecute = 0;
        if (userModel.getId() == null){
            numberRowExecute = userDao.insertUser(userModel);
        } else {
            numberRowExecute = userDao.updateUser(userModel);
        }

        return numberRowExecute;
    }
}
