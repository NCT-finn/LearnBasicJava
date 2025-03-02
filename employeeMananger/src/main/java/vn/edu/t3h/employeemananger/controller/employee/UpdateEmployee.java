package vn.edu.t3h.employeemananger.controller.employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.employeemananger.dao.EmployeeDao;
import vn.edu.t3h.employeemananger.dao.impl.EmployeeDaoMysql;
import vn.edu.t3h.employeemananger.model.Department;
import vn.edu.t3h.employeemananger.model.Employee;
import vn.edu.t3h.employeemananger.service.EmployeeService;
import vn.edu.t3h.employeemananger.service.Impl.EmployeeServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/employee/update")
public class UpdateEmployee extends HttpServlet {

    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        super.init();
        // init bean, apply DI design pattern
        EmployeeDao employeeDao = new EmployeeDaoMysql();
        employeeService  = new EmployeeServiceImpl(employeeDao);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("employeeId"));
        Employee employee = employeeService.getByEmployeeId(id);
        List<Department> departmentList = employeeService.getDepartmentAll();
        req.setAttribute("Employee",employee);
        req.setAttribute("Department",departmentList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/EmployeeManager/updateEmployee.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("employeeId");
        String name   = req.getParameter("name");
        String position = req.getParameter("position");
        String salary = req.getParameter("salary");
        String departmentId = req.getParameter("department_id");
        String hireDate = req.getParameter("hire_date");
        Employee employee = new Employee();
        employee.setEmployeeId(Integer.parseInt(employeeId));
        employee.setNameEmployee(name);
        employee.setPosition(position);
        employee.setSalary(Long.parseLong(salary));
        employee.setDepartmentId(Integer.parseInt(departmentId));
        employee.setHireDate(hireDate);
        employeeService.updateEmployee(employee);
        resp.sendRedirect("/employee");
    }
}
