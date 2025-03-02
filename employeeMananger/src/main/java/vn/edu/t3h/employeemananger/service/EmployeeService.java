package vn.edu.t3h.employeemananger.service;

import vn.edu.t3h.employeemananger.model.Department;
import vn.edu.t3h.employeemananger.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();

    List<Employee> searchByEmployee(String name, String salary, String fromDate, String toDate, String position);

    void insertEmployee(Employee employee);

    Employee getByEmployeeId(Integer id);

    List<Department> getDepartmentAll();

    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);
}