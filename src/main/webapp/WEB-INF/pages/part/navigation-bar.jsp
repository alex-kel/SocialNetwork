<%--<%@ sec:taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<sec:authorize access="isAuthenticated()">
    <div class="nav-container navigation">
        <ul class="nav nav-pills" style="float: left">
            <li role="presentation" id="1"><a href="/myprofile">My profile</a></li>
            <li role="presentation" id="2"><a href="#"> My Friends</a></li>
            <li role="presentation" id="3"><a href="#">My Messages</a></li>
        </ul>

        <ul style="float: right" class="nav nav-pills">
            <li><a href="<c:url value="/logout" />" role="button">Log Out</a></li>
        </ul>

    </div>
</sec:authorize>

<sec:authorize access="!isAuthenticated()">
    <div class="nav-container navigation">
        <ul class="nav nav-pills" style="float: left">
            <li role="presentation" id="login"><a href="/login">Sign in</a></li>
            <li role="presentation" id="register"><a href="/register">Sign Up</a></li>
        </ul>


    </div>
</sec:authorize>

