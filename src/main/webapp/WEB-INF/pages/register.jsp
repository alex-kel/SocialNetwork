<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 13.02.2015
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Register</title>
    <jsp:include page="part/links-part.jsp"></jsp:include>
</head>
<body>
<div class="register">
    <div class="container">
        <form method="post">
            <h2 class="form-heading">Registration</h2>
            <input type="text" class="form-control" name="login" placeholder="Login">
            <input type="password" class="form-control" name="password" placeholder="Password">
            <input type="password" class="form-control" name="confirmPassword" placeholder="Password again">
            <input type="text" class="form-control"  name="fullName" placeholder="Full name">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
        </form>
    </div>
</div>
</body>
</html>
