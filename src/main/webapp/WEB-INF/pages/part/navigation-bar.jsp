<%--<%@ sec:taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<sec:authorize access="isAuthenticated()">
    <div class="nav-container navigation">
        <ul class="nav nav-pills" style="float: left">
            <li role="presentation"><a href="#">My profile</a></li>
            <li role="presentation"><a href="#"> My Friends</a></li>
            <li role="presentation"><a href="#">My Messages</a></li>
            <li role="presentation"><a href="#">My News</a></li>
            <li role="presentation"><a href="#">My Photos</a></li>
        </ul>

        <ul style="float: right" class="nav nav-pills">
            <li><a href="<c:url value="/logout" />" role="button">Log Out</a></li>
        </ul>

    </div>
</sec:authorize>

