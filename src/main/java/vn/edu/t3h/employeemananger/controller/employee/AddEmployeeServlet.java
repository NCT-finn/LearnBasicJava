package vn.edu.t3h.employeemananger.controller.employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.employeemananger.dao.EmployeeDao;
import vn.edu.t3h.employeemananger.dao.impl.EmployeeDaoMysql;
import vn.edu.t3h.employeemananger.model.Employee;
import vn.edu.t3h.employeemananger.service.EmployeeService;
import vn.edu.t3h.employeemananger.service.Impl.EmployeeServiceImpl;

import java.io.IOException;

@WebServlet(value = "/employee/addEmployee")
public class AddEmployeeServlet extends HttpServlet {

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
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/EmployeeManager/addEmployee.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String addName   = req.getParameter("name");
        String addPosition = req.getParameter("position");
        String addSalary = req.getParameter("salary");
        String addDepartmentid = req.getParameter("department_id");
        String addHireDate = req.getParameter("hire_date");
        System.out.println("---------------------------Test---");
        Employee employee = new Employee();
        employee.setNameEmployee(addName);
        employee.setPosition(addPosition);
        employee.setSalary(Long.parseLong(addSalary));
        employee.setDepartmentId(Integer.parseInt(addDepartmentid));
        employee.setHireDate(addHireDate);

        employeeService.insertEmployee(employee);
        resp.sendRedirect("/employee");

    }
}
