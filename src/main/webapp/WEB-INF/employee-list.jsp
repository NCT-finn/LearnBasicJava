<%--
  Created by IntelliJ IDEA.
  User: GIANG VIEN T3H
  Date: 1/9/2025
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Danh sách nhân viên</h1>
<form method="post">
    <input type="text" name="query" placeholder="Search by Last Name with First Name">
    <button type="submit">Search</button>
</form>
<table border="1" width="90%" id="my-table">
    <thead>
    <tr>
        <th>Employee Id</th>
        <th>Name</th>
        <th>Position</th>
        <th>Salary</th>
        <th>Department Name</th>
        <th>Hire Date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employee" items="${employeeData}">
        <tr>
            <td style="text-align: center;">${employee.employeeId}</td>
            <td style="text-align: center;">${employee.nameEmployee}</td>
            <td style="text-align: center;">${employee.position}</td>
            <td style="text-align: center;">${employee.salary}</td>
            <td style="text-align: center;">${employee.departmentName}</td>
            <td style="text-align: center;">${employee.hireDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>