<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 22.02.2015
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
    <title>My messages</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/mycss.css" rel="stylesheet">
    <script src="/resources/js/jquery-2.1.3.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/myapp.js"></script>
    <script src="/resources/js/messages.js"></script>
</head>
<body>

<%@ include file="part/navigation-bar.jsp" %>
<script>
    $(".navigation").find("#3").addClass("active");
</script>

<div role="tabpanel" style="padding-top: 100px; margin-right: auto; margin-left: auto; width: 700px">

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#inbox" aria-controls="inbox" role="tab"
                                                  data-toggle="tab">Inbox</a></li>
        <li role="presentation"><a href="#outbox" aria-controls="outbox" role="tab" data-toggle="tab">Outbox</a></li>
    </ul>

    <!-- Tab panes -->
    <style>
        .tab-pane {
            padding-left: 40px;
            padding-top: 40px;
        }
        .msg {
            margin-top: 40px;
        }
        .ava {
            float: left; height: 75px;
        }
        .author {
            padding-left: 40px; color: blue; font-size: 18px;
        }
        .text {
            margin-left: 40px; display: inline-block; width: 500px;
        }
        .right {
            float: right;
        }
    </style>

    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active fade in" id="inbox">


        <div role="tabpanel" class="tab-pane fade" id="outbox">
            out
        </div>
    </div>

</div>
</body>
</html>
