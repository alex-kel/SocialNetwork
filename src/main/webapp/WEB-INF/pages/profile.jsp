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

    var postCode = "<div class=\"post col-md-12\"> " +
            " <div><span class=\"post-author\"></span><a style='float: right' class=\"btn btn-xs btn-default del\"><span class=\"glyphicon glyphicon-remove\"></span> </a></div>" +
            "<div class=\"post-text\" style='word-wrap: break-word'></div> " +
            "<div class=\"post-info\"> <p class=\"date\"></p> " +
            "<a style='float: right' class=\"btn btn-default btn-xs like-btn\"><span class=\"glyphicon glyphicon-thumbs-up\"></span> LIke</a>" +
            "</div> <hr style='margin-top: 60px'></div></div>";

    $(document).ready(function () {

        $.post("/isFollowed", {"id" : ${id}}, function(data) {
            if (data) {
                $(".follow").hide();
                $(".unfollow").show();
            } else {
                $(".follow").show();
            }
        });

        $.ajax("/photos/getAll", {
            type: 'GET',
            data: {"id" : '${id}'},
            success: function(data) {
                data = JSON.parse(data);
                var imgPattern = "<img class=\"photo-small img-thumbnail\">"
                for (var i = 0; i < data.length; i++) {
                    var newImg = $('<div/>').html(imgPattern).contents();
                    newImg.attr("src", data[i].ref);
                    newImg.addClass(data[i].id.toString());
                    $(".photos").append(newImg);
                }
            }

        });

        $(".postbtn").prop('disabled', true);
        $(".wall-post").keyup(function () {
            if ($(this).val().length == 0) {
                $(".postbtn").prop('disabled', true);
            } else {
                $(".postbtn").prop('disabled', false);
            }
        });

        $('.postbtn').click(function () {
            var text = $(".wall-post").val();
            var id = '${id}';
            $.ajax("/wall/createPost", {
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    "text": text,
                    "owner_id": id
                }),
                success: function (data) {
                    data = JSON.parse(data);
                    addPost(data);
                    $(".wall-post").val("");
                    $(".postbtn").prop('disabled', true);
                }
            });
        });

        $.ajax("/wall/getPosts", {
            type: 'GET',
            data: {"id": ${id}},
            success: function (data) {
                data = JSON.parse(data);
                for (var i = 0; i < data.length; i++) {
                    addPost(data[i]);
                }
            }
        });

        function addPost(data) {
            var id = data.id;
            var author = data.author.details.fullName;
            var text = data.text;
            var date = data.date;
            var likeCount = 0;
            try {
                likeCount = data.likes.length;
            } catch (err) {
            }
            var isLiked = false;
            for (var i = 0; i < likeCount; i++) {
                if (data.likes[i].owner.id === ${sessionId}) {
                    isLiked = true;
                }
            }
            var newPost = $('<div/>').html(postCode).contents();
            newPost.hide();
            newPost.addClass(id.toString());
            newPost.find(".post-author").text(author);
            newPost.find(".post-text").text(text);
            newPost.find(".date").text(date);
            newPost.find(".like-btn").text("Like " + likeCount);
            if (isLiked) {
                newPost.find(".like-btn").addClass("btn-primary");
            }
            $(".posts").prepend(newPost);
            newPost.fadeIn(500);
        }

        $(".follow").click(function() {
            $.post("/follow", {"id" : ${id}});
            $(".follow").hide();
            $(".unfollow").show();

        });

        $(".unfollow").click(function() {
            $.post("/unfollow", {"id" : ${id}});
            $(".unfollow").hide();
            $(".follow").show();
        });


    });
</script>

<div class="profile">
    <div class="container">
        <h1 class="fullName">${fullName}</h1>
        <p style="color: green" class="isFollowed"></p>
        <c:if test="${editable}">
            <a class="edit-profile" href="#">Edit profie</a>
            <a class="change-avatar" href="#" style="margin-left: 10px">New profile photo</a>
        </c:if>
        <hr>
        <div class="row">
            <!-- left column -->
            <div class="col-md-6">
                <div class="col-md-6">
                    <div class="text-center">
                        <img src="${avatarRef}" style="width: 250px; height: 330px;" class="avatar img-thumbnail"
                             alt="avatar">
                        <sec:authorize access="isAuthenticated()">
                            <c:if test="${!editable}">
                                <button  style="display: none" type="button" class="btn btn-success follow">Follow</button>
                                <button  style="display: none" type="button" class="btn btn-danger unfollow">Unfollow</button>
                            <button type="button" class="btn btn-primary msg">Send Message</button>
                            </c:if></sec:authorize>
                    </div>
                </div>
                <div class="col-md-6 personal-info">
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
                <div class="photos col-md-12">
                    <h1>Photos</h1>
                    <c:if test="${editable}"><a href="#" onclick="uploadModal()">Upload a new photo to album</a></c:if>
                    <hr>

                </div>
            </div>
            <div class="col-md-5 col-md-offset-1 wall">
                <textarea class="form-control wall-post" rows="3"></textarea>
                <button type="button" class="btn btn-primary postbtn" style="float: right">Post</button>
                <div class="posts">

                </div>
            </div>


        </div>

    </div>
</div>

<c:if test="${editable}">
    <div class="modal fade profileModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Profile edit</h4>
                </div>
                <div class="modal-body">
                    <span>Name:</span><input type="text" class="form-control" id="fullName" placeholder="Full name"
                                             value="${fullName}">
                    <span>City:</span><input type="text" class="form-control" id="city" placeholder="City"
                                             value="${city}">
                    <span>Phone number:</span><input type="text" class="form-control" id="phoneNumber"
                                                     placeholder="Phone number"
                                                     value="${phoneNumber}">
                    <span>Email:</span><input type="text" class="form-control" id="email" placeholder="Email"
                                              value="${email}">
                    <span>Some information about you:</span><textarea class="form-control" id="about"
                                                                      placeholder="About me">${about}</textarea>
                    <span>Birth date:</span><input type="date" class="form-control" id="birthDate"
                                                   placeholder="Birth date">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary save">Save changes</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade avatarModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">New profile photo</h4>
                </div>
                <div class="modal-body">
                    <form method="POST" enctype="multipart/form-data"
                          action="/uploadAvatar" id="avatar-form">
                                <span class="file-input btn btn-primary btn-file">
                Browse&hellip; <input type="file" name="file" id="newAvatar" onchange="readURL(this);">
            </span>
                        <img class="preview"
                             style="display: none; margin-top: 20px; margin-left: auto; margin-right: auto"
                             src="#" alt="your image"/>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" onclick="uploadAvatar()" class="btn btn-primary upload">Upload</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade uploadPhoto">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">New album photo</h4>
                </div>
                <div class="modal-body">
                    <form method="POST" enctype="multipart/form-data"
                          action="/photos/upload" id="photo-form">
                                <span class="file-input btn btn-primary btn-file">
                Browse&hellip; <input type="file" name="file" id="newPhoto" onchange="readURL(this);">
            </span>
                        <img class="preview"
                             style="display: none; margin-top: 20px; margin-left: auto; margin-right: auto"
                             src="#" alt="your image"/>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" onclick="uploadPhoto()" class="btn btn-primary upload">Upload</button>
                </div>
            </div>
        </div>
    </div>
</c:if>

<div class="modal fade photoModal">
    <div class="modal-dialog" style="display: inline-block; width: 100%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="clearFlag()" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Photos</h4>
            </div>
            <div class="modal-body">
                <img class="bigPhoto" style="margin: auto; display: block">
            </div>
            <div class="modal-footer">
                <a style='float: left' class="btn btn-default btn-md like-photo"><span class="glyphicon glyphicon-thumbs-up"></span> LIke</a>
                <span class="like-count" style="float: left; margin-top: 7px; margin-left: 10px"></span>
                <button type="button" class="btn btn-primary prev">Prev</button>
                <button type="button" class="btn btn-primary next">Next</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearFlag()">Close</button>
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
                <h4 class="modal-title">New message to ${fullName}</h4>
            </div>
            <div class="modal-body">
                <textarea class="form-control" id="text-message"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" onclick="sendMsg(${id})" class="btn btn-primary send">Send</button>
            </div>
        </div>
    </div>
</div>

<div class='wasSend' style='display:none'>Your message to user ${fullName} was send!</div>
</body>
</html>
