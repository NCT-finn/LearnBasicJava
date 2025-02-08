package vn.edu.t3h.employeemananger.service;

import vn.edu.t3h.employeemananger.dao.EmployeeDao;
import vn.edu.t3h.employeemananger.model.Department;
import vn.edu.t3h.employeemananger.model.Employee;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeDao.getAllEmployee();
    }

    @Override
    public List<Employee> searchByEmployee(String name, String salary, String fromDate, String toDate, String position) {
        return employeeDao.searchByEmployee(name,salary,fromDate,toDate,position);
    }

    @Override
    public void insertEmployee(Employee employee) {
        employeeDao.insertEmployee(employee);
    }

    @Override
    public Employee getByEmployeeId(Integer id) {
        return employeeDao.getByEmployeeId(id);
    }

    @Override
    public List<Department> getDepartmentAll() {
        return employeeDao.getDepartmentAll();
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeDao.deleteEmployee(employee);
    }


}
