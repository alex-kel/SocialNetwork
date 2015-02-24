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

<div role="tabpanel" style="padding-top: 100px; margin-right: auto; margin-left: auto; width: 700px">

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#follower" aria-controls="follower" role="tab"
                                                  data-toggle="tab">Followers</a></li>
        <li role="presentation"><a href="#following" aria-controls="following" role="tab" data-toggle="tab">Following</a></li>
        <li role="presentation"><a href="#friends" aria-controls="friends" role="tab" data-toggle="tab">Friends</a></li>
        <li role="presentation"><a href="#all" aria-controls="all" role="tab" data-toggle="tab">All users</a></li>
    </ul>

    <!-- Tab panes -->
    <style>
    </style>

    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active fade in" id="follower"></div>

        <div role="tabpanel" class="tab-pane fade" id="following">

        </div>

        <div role="tabpanel" class="tab-pane fade" id="friends">

        </div>

        <div role="tabpanel" class="tab-pane fade" id="all">

        </div>
    </div>
</div>

</div>


<div class="modal fade send-message">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">New message</h4>
            </div>
            <div class="modal-body">
                <textarea class="form-control" id="text-message"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button"  class="btn btn-primary send">Send</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
