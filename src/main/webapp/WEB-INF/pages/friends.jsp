<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/mycss.css" rel="stylesheet">
    <script src="/resources/js/jquery-2.1.3.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/myapp.js"></script>
    <script src="/resources/js/friends.js"></script>
</head>
<body>
<%@ include file="part/navigation-bar.jsp" %>

<div class="friends col-md-12" style="margin-top: 50px">
    <div class="followers col-md-6">
        <h1 style="padding-bottom: 20px">My followers</h1>

    </div>

    <div class="followings col-md-6">
        <h1 style="padding-bottom: 20px">I`m following</h1>
    </div>
</div>
</body>
</html>
