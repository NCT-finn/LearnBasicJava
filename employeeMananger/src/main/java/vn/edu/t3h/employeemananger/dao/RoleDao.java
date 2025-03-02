package vn.edu.t3h.employeemananger.dao;

import vn.edu.t3h.employeemananger.model.RoleModel;

public interface RoleDao {

    RoleModel findRoleById(Integer roleId);
}
