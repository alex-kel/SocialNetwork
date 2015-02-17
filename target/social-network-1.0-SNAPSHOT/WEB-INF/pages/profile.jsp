<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${fullName}</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/mycss.css" rel="stylesheet">
    <script src="/resources/js/jquery-2.1.3.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/myapp.js"></script>
    <script src="/resources/js/profile.js"></script>
</head>
<body>
<%@ include file="part/navigation-bar.jsp" %>
<c:if test="${editable}">
    <script>
        setActive();
    </script>
</c:if>

<script type="text/javascript">
    $(document).ready(function() {
        $('.postbtn').click(function() {
            var text = $(".wall-post").val();
            var id = '${id}';
            $.ajax("/wall/createPost", {
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    "text": text,
                    "owner_id": id
                }),
                complete: function() {

                }
            });
        });
    });
</script>

<div class="profile">
    <div class="container">
        <h1>${fullName}</h1>
        <c:if test="${editable}">
            <a class="edit-profile" href="#">Edit profie</a>
        </c:if>
        <hr>
        <div class="row">
            <!-- left column -->
            <div class="col-md-2">
                <div class="text-center">
                    <img src="https://pp.vk.me/c618722/v618722210/37ab/dQWwJEbhMRg.jpg" class="avatar img-circle"
                         alt="avatar">
                    <button type="button" class="btn btn-success">Follow</button>
                    <button type="button" class="btn btn-primary">Send Message</button>
                </div>
            </div>
            <div class="col-md-3 col-lg-offset-1 personal-info">
                <label class="profile_info">Full name:</label>
                <label class="profile_content fullName">${fullName}</label>
                <hr>
                <br>
                <label class="profile_info">Email:</label>
                <label class="profile_content email">${email}</label>
                <br>
                <hr>
                <label class="profile_info">City:</label>
                <label class="profile_content city">${city}</label>
                <br>
                <hr>
                <label class="profile_info">Birth Date</label>
                <label class="profile_content birthDate">${birthDate}</label>
                <br>
                <hr>
                <label class="profile_info">Phone number:</label>
                <label class="profile_content phoneNumber">${phoneNumber}</label>
                <br>
                <hr>
                <label class="profile_info">About me:</label>
                <label class="profile_content about">${about}</label>
                <br>
                <hr>
            </div>

            <div class="col-md-4 col-lg-offset-1 wall">
                <textarea class="form-control wall-post" rows="3"></textarea>
                <button type="button" class="btn btn-primary postbtn" style="float: right">Post</button>

                <div class="post col-md-12">
                    <div class="post-author">Alexander Kel</div>
                    <div class="post-text">sdfasdfasdfasdf</div>
                    <div class="post-info">
                        <p class="date">17 Jan, 18:05</p>

                        <div class="like">
                            <img src="/resources/img/like.png">
                            <span class="like-count">5</span>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<c:if test="${editable}">
    <div class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Profile edit</h4>
                </div>
                <div class="modal-body">
                    <span>Name</span><input type="text" class="form-control" id="fullName" placeholder="Full name"
                                            value="${fullName}">
                    <input type="text" class="form-control" id="city" placeholder="City" value="${city}">
                    <input type="text" class="form-control" id="phoneNumber" placeholder="Phone number"
                           value="${phoneNumber}">
                    <input type="text" class="form-control" id="email" placeholder="Email" value="${email}">
                    <textarea class="form-control" id="about" placeholder="About me">${about}</textarea>
                    <input type="date" class="form-control" id="birthDate" placeholder="Birth date">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary save">Save changes</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
</c:if>
</body>
</html>
