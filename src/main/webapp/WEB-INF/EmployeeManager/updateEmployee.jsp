<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Employee</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .form-container {
            max-width: 600px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<div class="container">
    <div class="form-container">
        <h2 class="mb-4 text-center">Add Employee</h2>
        <form action="/employee/update" method="POST">
            <input type="hidden" name="employeeId" value="${Employee.employeeId}"/>
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input value="${Employee.nameEmployee}" type="text" class="form-control" id="name" name="name" placeholder="Enter employee name" required>
            </div>
            <div class="mb-3">
                <label for="position" class="form-label">Position</label>
                <input value="${Employee.position}" type="text" class="form-control" id="position" name="position" placeholder="Enter position" required>
            </div>
            <div class="mb-3">
                <label for="salary" class="form-label">Salary</label>
                <input value="${Employee.salary}" type="number" class="form-control" id="salary" name="salary" placeholder="Enter salary" step="0.01" required>
            </div>
            <div class="mb-3">
                <label for="department_id" class="form-label">Department</label>
                <select class="form-select" id="department_id" name="department_id" required>
                    <option value="">Select Department</option>
                    <c:forEach var="department" items="${requestScope.Department}">
                        <c:choose>
                            <c:when test="${Employee.departmentId eq department.departmentId}">
                                <option value="${department.departmentId}" selected>${department.departmentName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${department.departmentId}">${department.departmentName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="hire_date" class="form-label">Hire Date</label>
                <input value="${Employee.hireDate}" type="date" class="form-control" id="hire_date" name="hire_date" required>
            </div>
            <div class="d-grid">
                <button type="submit" class="btn btn-primary">Update Employee</button>
                <button type="reset" class="btn btn-warning ms-2">Tẩy trống</button>
                <button type="button" class="btn btn-light ms-2" onclick="window.history.back();">Hủy</button>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>