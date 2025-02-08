package vn.edu.t3h.employeemananger.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.employeemananger.dao.EmployeeDao;
import vn.edu.t3h.employeemananger.dao.impl.EmployeeDaoMysql;
import vn.edu.t3h.employeemananger.model.Employee;
import vn.edu.t3h.employeemananger.service.EmployeeService;
import vn.edu.t3h.employeemananger.service.EmployeeServiceImpl;

import java.io.IOException;

@WebServlet(value = "/employee/delete")
public class DeleteEmployee extends HttpServlet {

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
        Integer employeeId = Integer.parseInt(req.getParameter("employeeId"));
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employeeService.deleteEmployee(employee);
        resp.sendRedirect("/employee");
    }
}
