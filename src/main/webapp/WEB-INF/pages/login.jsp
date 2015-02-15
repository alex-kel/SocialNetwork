<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="part/links-part.jsp"></jsp:include>
</head>

<body>
<div class="sign-up">
    <div class="container">
        <c:url value="/j_spring_security_check" var="loginUrl"/>
        <form action="${loginUrl}" method="post">
            <h2 class="form-heading">Please sign in</h2>
            <input type="text" class="form-control" name="j_username" placeholder="Email address">
            <input type="password" class="form-control" name="j_password" placeholder="Password">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign In</button>
        </form>
    </div>
</div>
</body>
</html>