package vn.edu.t3h.employeemananger.dao.impl;

import vn.edu.t3h.employeemananger.CommonConnection;
import vn.edu.t3h.employeemananger.dao.EmployeeDao;
import vn.edu.t3h.employeemananger.model.Department;
import vn.edu.t3h.employeemananger.model.Employee;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoMysql implements EmployeeDao {

    @Override
    public List<Employee> getAllEmployee() {

        Connection connection = connection();
        List<Employee> employeeList = new ArrayList<>();
        String sql = "select employee_id,nameEmployee,position,department_name,hire_date,dept.department_id from employees emp " +
                "inner join departments dept on emp.department_id = dept.department_id;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();;
            while (resultSet.next()){
                Employee employee1 = new Employee();
                employee1.setEmployeeId(resultSet.getInt("employee_id"));
                employee1.setNameEmployee(resultSet.getString("nameEmployee"));
                employee1.setPosition(resultSet.getString("position"));
                employee1.setSalary(resultSet.getLong("salary"));
                employee1.setDepartmentName(resultSet.getString("department_name"));
                employee1.setHireDate(resultSet.getString("hire_date"));
                employee1.setDepartmentId(resultSet.getInt("department_id"));

                employeeList.add(employee1);
                System.out.println("get employee success");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employeeList;
    }

    @Override
    public List<Employee> searchByEmployee(String name, String salary, String fromDate, String toDate, String position) {
        String sql = "SELECT e.employee_id, e.nameEmployee, e.position, e.salary, d.department_name, e.hire_date, d.department_id\n" +
                "FROM employees e\n" +
                "LEFT JOIN departments d ON e.department_id = d.department_id\n" +
                "WHERE 1=1\n" +
                "AND (e.nameEmployee LIKE ? OR ? IS NULL)\n" +
                "AND (e.salary >= ? OR ? IS NULL)\n" +
                "  AND (e.hire_date >= ? or ? is NULL)\n" +
                "  AND (e.hire_date <= ? or ? is NULL)\n" +
                "AND (e.position LIKE ? OR ? IS NULL)\n;";

        List<Employee> employeeList = new ArrayList<>();
        Connection connection = null;
        try {
           connection = connection();
           PreparedStatement statement = connection.prepareStatement(sql);
           setConditionFilter(name,salary,fromDate,toDate,position,statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setNameEmployee(resultSet.getString("nameEmployee"));
                employee.setPosition(resultSet.getString("position"));
                employee.setSalary(resultSet.getLong("salary"));
                employee.setDepartmentName(resultSet.getString("department_name"));
                employee.setHireDate(resultSet.getString("hire_date"));
                employee.setDepartmentId(resultSet.getInt("department_id"));
                employeeList.add(employee);
            }
       } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    @Override
    public Employee getByEmployeeId(Integer id) {
        String sql = "select * from employees where employee_id = ?;";
        Employee employee = new Employee();
        Connection connection = null;
        try {
//            PreparedStatement statement = CommonConnection.getConnection().prepareStatement(sql);
            connection = connection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setNameEmployee(resultSet.getString("nameEmployee"));
                employee.setPosition(resultSet.getString("position"));
                employee.setSalary(resultSet.getLong("salary"));
                employee.setDepartmentId(resultSet.getInt("department_id"));
                employee.setHireDate(resultSet.getString("hire_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public List<Department> getDepartmentAll() {
        String sql = "select * from departments ;";
        Connection connection = null;
        List<Department> departmentList = new ArrayList<>();
        try {
//            PreparedStatement statement = CommonConnection.getConnection().prepareStatement(sql);
            connection = connection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Department department = new Department();
                department.setDepartmentId(resultSet.getInt("department_id"));
                department.setDepartmentName(resultSet.getString("department_name"));
                department.setLocation(resultSet.getString("location"));
                departmentList.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return departmentList;
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = "update \n" +
                "\t employees \n" +
                "set \n" +
                "\t nameEmployee = ?,\n" +
                "    position = ? , \n" +
                "    salary = ?, \n" +
                "   department_id = ?, \n" +
                "    hire_date = ? \n" +
                "where \n" +
                "\t employee_id = ?;";
        Connection connection = null;
        Date hireDate = convertDate(employee.getHireDate());
        try {
            connection = connection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,employee.getNameEmployee());
            statement.setString(2,employee.getPosition());
            statement.setLong(3,employee.getSalary());
            statement.setInt(4,employee.getDepartmentId());
            statement.setDate(5,hireDate);
            statement.setInt(6,employee.getEmployeeId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEmployee(Employee employee) {
        String sql =  "delete from employees where employee_id = ?;";
        Connection connection = null;
        try {
            connection = connection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,employee.getEmployeeId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void insertEmployee(Employee employee) {
//        Employee employee1 = new EmployeeDaoMysql().getDepartId(employee);
        String sql = "insert into employees(nameEmployee,position,salary,department_id,hire_date)" +
                " values (?,?,?,?,?);";
        Connection connection = null;
        try {
            connection = connection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,employee.getNameEmployee());
            statement.setString(2,employee.getPosition());
            statement.setLong(3,employee.getSalary());
            statement.setInt(4,employee.getDepartmentId());
            statement.setString(5, employee.getHireDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setConditionFilter(String name, String salary, String fromDate, String toDate, String position, PreparedStatement statement) throws SQLException {
        java.sql.Date convertFromDate= convertDate(fromDate);
        java.sql.Date convertToDate= convertDate(toDate);;
        if (name != null){
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + name + "%");
        }else {
            statement.setNull(1, Types.VARCHAR);
            statement.setNull(2, Types.VARCHAR);
        }

        if (salary != null && !salary.equals("") && !salary.isEmpty()){
            statement.setLong(3, Long.parseLong(salary));
            statement.setLong(4, Long.parseLong(salary));
        }else {
            statement.setNull(3, Types.DECIMAL);
            statement.setNull(4, Types.DECIMAL);
        }

        if (fromDate != null){
            statement.setDate(5, convertFromDate);
            statement.setDate(6, convertFromDate);
        }else {
            statement.setNull(5, Types.VARCHAR);
            statement.setNull(6, Types.VARCHAR);
        }

        if (toDate != null){
            statement.setDate(7, convertToDate);
            statement.setDate(8, convertToDate);
        }else {
            statement.setNull(7, Types.VARCHAR);
            statement.setNull(8, Types.VARCHAR);
        }

        if (position != null){
            statement.setString(9, "%" + position + "%");
            statement.setString(10, "%" + position + "%");
        }else {
            statement.setNull(9, Types.VARCHAR);
            statement.setNull(10, Types.VARCHAR);
        }
    }

    public Connection connection(){
        String url = "jdbc:mysql://localhost:3306/quanlynhansu";
        String userName = "root";
        String password = "123456789";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,userName,password);
            System.out.println(" get connection success");
            return connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static java.sql.Date convertDate(String hireDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date convertDate=null;
        if(hireDate != null && !hireDate.equals("")){
            try{
                java.util.Date utilDate = format.parse(hireDate);
                convertDate = new Date(utilDate.getTime());
            }
            catch(ParseException e){
                System.out.println(e);
            }
        }
        return convertDate;
    }
}
