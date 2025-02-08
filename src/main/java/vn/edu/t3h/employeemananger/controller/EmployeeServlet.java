package vn.edu.t3h.employeemananger.controller;

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
import vn.edu.t3h.employeemananger.service.EmployeeServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeServlet", value = "/employee")
public class EmployeeServlet extends HttpServlet {

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
        String findByName   = req.getParameter("name");
        String findBySalary = req.getParameter("salary");
        String findByFromDate     = req.getParameter("fromDate");
        String findByToDate = req.getParameter("toDate");
        String findByPosition = req.getParameter("position");
        List<Employee> employeeList = employeeService.searchByEmployee(findByName,findBySalary,findByFromDate,findByToDate,findByPosition);
        req.setAttribute("employeeModels",employeeList);
        // config kiểu dữ liiệu trả về trong response là html/text
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/employees.jsp");
        requestDispatcher.forward(req,resp);
    }
}
