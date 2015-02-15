<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>

<head>
    <%@ include file="part/links-part.jsp" %>
</head>

<body>
<%@ include file="part/navigation-bar.jsp" %>
<div class="index">
    <div class="container" style="padding-top: 40px">
        <div class="jumbotron" style="margin-top: 20px;">
            <%--<h1>Social Network</h1>--%>
        </div>
        <sec:authorize access="!isAuthenticated()">
            <div class="btn-container">
                <a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Sign In</a>
                <a class="btn btn-lg btn-success" href="<c:url value="/register" />" role="button">Sign Up</a>
            </div>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
            <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Log Out</a></p>
        </sec:authorize>
        <div>
            <h1 style="float: right"><i>"My Social Network"</i></h1>
        </div>
    </div>
</div>
</body>

</html>