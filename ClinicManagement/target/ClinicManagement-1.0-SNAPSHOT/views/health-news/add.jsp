<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Health News</title>
    <script src="<c:url value='/ckeditor/ckeditor.js' />"></script>
    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body id="page-top">
<div id="wrapper">
    <%@include file="../components/sidebar/sidebar_dashboard.jsp" %>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <div id="content">

            <%@include file="../components/topbar/topbar.jsp" %>

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <h2>Add health news</h2>

                <!-- Content Row -->
                <div class="row">
                    <div class="col-xs-12" style="margin-left: 150px;">
                        <form id="formSubmit" action="<c:url value='/news/add'/>" method="POST"
                              enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Title</label>
                                <div class="col-sm-12">
                                    <input required type="text" class="form-control" id="title" name="title"
                                           value="<c:url value="${news.title}"/>"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <br/>

                            <input id="avatar" onchange="readURL(this);" name="avatar" type="file" accept="image/*" />
                            <input hidden id="avatarTemp" value="<c:url value="${news.thumbnail}"/>" />
                            <img id="blah" width="200" height="200" src="<c:url value="${news.thumbnail}"/>" alt="Your thumbnail" />

<%--                            <div class="form-group">--%>
<%--                                <label class="col-sm-3 control-label no-padding-right">Thumbnail</label>--%>
<%--                                <div class="col-sm-9">--%>
<%--                                    <c:if test="${not empty news.thumbnail}">--%>
<%--                                        <img src="${news.thumbnail}" style="cursor: pointer"--%>
<%--                                             onclick="$('#image').click()" id="img_url" alt="Your image" width="80px"--%>
<%--                                             height="60px"/>--%>
<%--                                        <input required type="file" style="display: none" class="form-control -border-none"--%>
<%--                                               id="image" name="image" value="${news.thumbnail}"/>--%>
<%--                                        <script>--%>
<%--                                            $("#image").change(function () {--%>
<%--                                                readURL(this);--%>
<%--                                            });--%>

<%--                                            function readURL(input) {--%>
<%--                                                if (input.files && input.files[0]) {--%>
<%--                                                    var reader = new FileReader();--%>
<%--                                                    reader.onload = function (e) {--%>
<%--                                                        $('#img_url').attr('src', e.target.result);--%>
<%--                                                    }--%>
<%--                                                    reader.readAsDataURL(input.files[0]);--%>
<%--                                                }--%>
<%--                                            }--%>
<%--                                        </script>--%>
<%--                                    </c:if>--%>
<%--                                    <c:if test="${empty news.thumbnail}">--%>
<%--                                        <img src="" onclick="$('#image').click()" style="display: none;" id="editImg"--%>
<%--                                             alt="Your image" width="120px" height="100px"/>--%>
<%--                                        <input required type="file" style="margin-bottom: 5px; width: 250px;"--%>
<%--                                               class="form-control -border-none" id="image" name="image"/>--%>
<%--&lt;%&ndash;                                        <script>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            $("#image").change(function () {&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                if (document.querySelector('#image').files[0]) {&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                    $('#editImg').css({&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                        'cursor': 'pointer',&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                        'display': 'block'&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                    });&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                    $('#image').css({&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                        'display': 'none'&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                    });&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                    readURL(this);&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                }&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            });&ndash;%&gt;--%>

<%--&lt;%&ndash;                                            function readURL(input) {&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                if (input.files && input.files[0]) {&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                    var reader = new FileReader();&ndash;%&gt;--%>

<%--&lt;%&ndash;                                                    reader.onload = function (e) {&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                        $('#editImg').attr('src', e.target.result);&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                    }&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                    reader.readAsDataURL(input.files[0]);&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                }&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            }&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        </script>&ndash;%&gt;--%>
<%--                                    </c:if>--%>
<%--                                </div>--%>
<%--                            </div>--%>

                            <br/>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Short Description</label>
                                <div class="col-sm-12">
                                    <input required type="text" class="form-control" id="shortDescription"
                                           name="shortDescription"
                                           value="${news.shortDescription}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Content</label>
                                <div class="col-sm-pull-12">
                                <textarea rows="" cols="" class="ckeditor" id="content-new" name="content-new"
                                          style="width: 820px;height: 175px">${news.content}</textarea>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <input type="hidden" id="contentEditor" name="contentEditor"/>
                            <input type="hidden" id="newsId" name="newsId" value="${news.id}"/>
                            <input type="hidden" id="createdId" name="createdId" value="${sessionScope.USER_APP.id}"/>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <c:if test="${not empty news.id}">
                                        <button type="button" class="btn btn-primary" id="btnAddOrUpdateNew">Update
                                            health news
                                        </button>
                                    </c:if>
                                    <c:if test="${empty news.id}">
                                        <button type="button" class="btn btn-primary" id="btnAddOrUpdateNew">Add health
                                            news
                                        </button>
                                    </c:if>
                                    <button style="visibility: hidden;" type="submit" class="btn btn-primary"
                                            id="hiddenSubmit"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </div>
        <%@include file="../components/footer/footer.jsp" %>
    </div>
</div>
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/chart-area-demo.js"></script>
<script src="js/demo/chart-pie-demo.js"></script>
<script src="jquery-3.5.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</body>
<script>

    function readURL(input) {
        console.log(input)
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#blah')
                    .attr('src', e.target.result)
                    .width(200)
                    .height(200);
            };
            reader.readAsDataURL(input.files[0]);
        }else{
            var avatar=document.getElementById("avatarTemp").value
            $('#blah')
                .attr('src', avatar)
                .width(200)
                .height(200);
        }
    }

    $('#btnAddOrUpdateNew').click(async function (e) {
        console.log("helloo")
        e.preventDefault();
        var content = CKEDITOR.instances['content-new'].getData();
        $('#contentEditor').val(content);
        console.log("Content:  " + content);
        $('#hiddenSubmit').trigger("click");
    });

    const toBase64 = file => new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    });
</script>

</html>
