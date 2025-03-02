
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tạo Người Dùng</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: Arial, sans-serif;
      background: url('https://images.pexels.com/photos/2246476/pexels-photo-2246476.jpeg') no-repeat center center fixed;
      background-size: cover;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      color: white;
    }

    .form-container {
      background-color: rgba(0, 0, 0, 0.6);
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
      padding: 40px;
      width: 100%;
      max-width: 400px;
    }

    h2 {
      text-align: center;
      font-size: 24px;
      margin-bottom: 20px;
      color: #ffffff;
    }

    .input-group {
      margin-bottom: 15px;
    }

    input[type="text"], input[type="password"], input[type="email"] {
      width: 100%;
      padding: 12px;
      border: 1px solid #ddd;
      border-radius: 4px;
      font-size: 16px;
      outline: none;
      transition: border-color 0.3s;
    }

    input[type="text"]:focus, input[type="password"]:focus, input[type="email"]:focus {
      border-color: #ff7e5f;
    }

    .form-btn {
      width: 100%;
      padding: 12px;
      background-color: #ff7e5f;
      color: white;
      border: none;
      border-radius: 4px;
      font-size: 16px;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    .form-btn:hover {
      background-color: #feb47b;
    }

    .form-footer {
      text-align: center;
      margin-top: 15px;
    }

    .form-footer a {
      text-decoration: none;
      color: #ff7e5f;
      font-size: 14px;
    }

    .form-footer a:hover {
      text-decoration: underline;
    }

    .remember-me {
      display: flex;
      align-items: center;
    }

    .remember-me input {
      margin-right: 8px;
    }

    /* Thông báo */
    .notification {
      position: fixed;
      top: 20px;
      right: 20px;
      background-color: #28a745;
      color: white;
      padding: 15px 20px;
      border-radius: 5px;
      box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
      opacity: 0;
      transform: translateX(100%);
      transition: opacity 0.5s, transform 0.5s;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .notification.show {
      opacity: 1;
      transform: translateX(0);
    }

    .notification .close-btn {
      font-size: 20px;
      cursor: pointer;
      margin-left: 15px;
    }
  </style>
</head>
<body>

<div class="form-container">
  <c:if test="${empty userModel.id}">
    <h2 class="mb-4 text-center">Add New User</h2>
  </c:if>
  <c:if test="${not empty userModel.id}">
    <h2 class="mb-4 text-center">Edit User</h2>
  </c:if>
  <form action="userManager" method="POST">
    <div class="input-group">
      <input type="text" value="${userModel.id}" name="   " placeholder="Nhập tên đăng nhập" hidden>
    </div>
    <div class="input-group">
      <label for="username" class="form-label">User Name</label>
      <input type="text" id="username" value="${userModel.username}" name="username" placeholder="Nhập tên đăng nhập" required>
    </div>
    <div class="input-group">
      <input type="password" value="${userModel.password}" name="password" placeholder="Nhập mật khẩu" required>
    </div>
    <div class="input-group">
      <input type="text" name="full_name" value="${userModel.full_name}" placeholder="Nhập tên đầy đủ" required>
    </div>
    <div class="input-group">
      <input type="text" value="${userModel.deleted}" name="deleted" placeholder="Nhập deleted" hidden>
    </div>
    <div class="input-group">
      <label for="role">Vai trò:</label>
      <select name="role" id="role" >
<%--        <option value="2" selected>User</option>--%>
          <option value="2"}>User</option>
      </select>
    </div>

    <c:if test="${empty userModel.id}">
      <div class="d-grid">
        <button type="submit" class="form-btn">Tạo Người Dùng</button>
      </div>
    </c:if>
    <c:if test="${not empty userModel.id}">
      <div class="d-grid">
        <button type="submit" class="form-btn">Update Người Dùng</button>
      </div>
    </c:if>
  </form>
  <div class="form-footer">
    <a type="button" class="btn btn-light ms-2" onclick="window.history.back();">Quay lại</a>
  </div>
</div>

<!-- Thông báo -->
<div id="notification" class="notification show">
  <span>Tạo người dùng thành công!</span>
  <span class="close-btn" onclick="closeNotification()">×</span>
</div>

<script>
  function closeNotification() {
    var notification = document.getElementById("notification");
    notification.classList.remove("show");
  }

  document.getElementById("userManager").addEventListener("submit", function(event) {
    event.preventDefault();

    var notification = document.getElementById("notification");
    notification.classList.add("show");

    setTimeout(function() {
      notification.classList.remove("show");
    }, 4000);
  });
</script>

</body>
</html>

