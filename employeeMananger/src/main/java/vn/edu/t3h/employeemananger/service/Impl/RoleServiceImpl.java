package vn.edu.t3h.employeemananger.service.Impl;

import vn.edu.t3h.employeemananger.dao.RoleDao;
import vn.edu.t3h.employeemananger.dao.impl.RoleDaoImpl;
import vn.edu.t3h.employeemananger.model.RoleModel;
import vn.edu.t3h.employeemananger.service.RoleService;

public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao){
        this.roleDao = roleDao;
    }

    @Override
    public RoleModel getRoleById(Integer roleId) {
        return roleDao.findRoleById(roleId);
    }
}
