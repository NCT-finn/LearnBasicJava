package vn.edu.t3h.employeemananger.security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.employeemananger.dao.RoleDao;
import vn.edu.t3h.employeemananger.dao.impl.RoleDaoImpl;
import vn.edu.t3h.employeemananger.model.RoleModel;
import vn.edu.t3h.employeemananger.model.UserModel;
import vn.edu.t3h.employeemananger.service.Impl.RoleServiceImpl;
import vn.edu.t3h.employeemananger.service.RoleService;
import vn.edu.t3h.employeemananger.utils.Constants;


import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    private RoleService roleService;

    private String urlRedirect ;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        roleService = new RoleServiceImpl(new RoleDaoImpl());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        UserModel currentUser = (UserModel) request.getSession().getAttribute(Constants.SESSION_ID_CURRENT_USER);

        String url = request.getRequestURI();
//        cms: user muon truy cap vao web
        if (url.startsWith("/employee")){
            if (currentUser != null){
                RoleModel roleModel = roleService.getRoleById(currentUser.getRole_id());
                urlRedirect = "/login?message=" +Constants.DONT_LOGIN;
               if(roleModel != null && roleModel.getCode().equalsIgnoreCase(Constants.ROLE.ROLE_ADMIN.name())){
                   filterChain.doFilter(request,response);
               } else {
                   response.sendRedirect("/login?message="+ Constants.PERMISSION_DENIED);
               }
            }else {
                response.sendRedirect("/login?message="+Constants.DONT_LOGIN);
            }
        }else {
            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
