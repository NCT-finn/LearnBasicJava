package vn.edu.t3h.employeemananger.security;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.t3h.employeemananger.dao.impl.RoleDaoImpl;
import vn.edu.t3h.employeemananger.dao.impl.UserDaoImpl;
import vn.edu.t3h.employeemananger.model.RoleModel;
import vn.edu.t3h.employeemananger.model.UserModel;
import vn.edu.t3h.employeemananger.service.Impl.RoleServiceImpl;
import vn.edu.t3h.employeemananger.service.Impl.UserServiceImpl;
import vn.edu.t3h.employeemananger.service.RoleService;
import vn.edu.t3h.employeemananger.service.UserService;
import vn.edu.t3h.employeemananger.utils.Constants;
import vn.edu.t3h.employeemananger.utils.SessionUtil;

public class AuthenticationServiceImpl implements AuthenticationService{

    private UserService userService;
    private RoleService roleService;

    public AuthenticationServiceImpl(){
        this.userService = new UserServiceImpl(new UserDaoImpl());
        this.roleService = new RoleServiceImpl(new RoleDaoImpl());
    }

    @Override
    public String login(String username, String password, HttpServletRequest request) {
        //Authentication: Kiem tra userName, Password co ton tai trong db khong
        UserModel userModel = userService.findUserByUserAndPassword(username,password);
        if(userModel == null) {
            return "/login?message=loginError";
        }
        request.getSession().setAttribute(SessionUtil.SESSION_ID_CURRENT_USER,userModel);
        //Authorization: Kiem tra user neu co quyen Admin se cho phep truy cap vao trang quan ly employee
        RoleModel roleModel = roleService.getRoleById(userModel.getRole_id());
        if(Constants.ROLE.ROLE_ADMIN.name().equalsIgnoreCase(roleModel.getCode())){
            return "/employee";
        } else if (Constants.ROLE.ROLE_ADMIN.name().equalsIgnoreCase(roleModel.getCode())){
            return "/home";
        }
        return " ";
    }
}
