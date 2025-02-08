package vn.edu.t3h.employeemananger.dao;

import vn.edu.t3h.employeemananger.model.Department;
import vn.edu.t3h.employeemananger.model.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getAllEmployee();

    List<Employee> searchByEmployee(String name, String salary, String fromDate, String toDate, String position);

    Employee getByEmployeeId(Integer id);

    List<Department> getDepartmentAll();

    void insertEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);
}
