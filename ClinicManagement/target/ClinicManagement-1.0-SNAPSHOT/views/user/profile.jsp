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

    <title>SB Admin 2 - Dashboard</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body id="page-top">
<div id="wrapper">
    <%@include file="../components/sidebar/sidebar_dashboard.jsp" %>
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <%@include file="../components/topbar/topbar.jsp"%>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-8 col-lg-7">
                        <div class="card shadow mb-4">
                            <div class="card-body">
                                <div class="d-flex justify-content-center">
                                    <div class="container h-100">
                                        <div class="d-flex justify-content-center" style="padding: 30px">
                                            <div class="user_regist_card">
                                                <div class="d-flex justify-content-center">
                                                    <div>
                                                        <h2>Profile</h2>
                                                    </div>
                                                </div>
                                                <div class="d-flex justify-content-center">
                                                    <form onsubmit="return checkFormRegist()" id="formLogin" action="<c:url value="/user/profile"/>" method="POST" enctype="multipart/form-data">
                                                        <c:if test="${not empty requestScope.messageParam}">
                                                            <div class="alert alert-${requestScope.alert}" role="alert">
                                                                    ${requestScope.messageParam}
                                                            </div>
                                                        </c:if>
                                                        <div class="row">
                                                            <div class="form-group" style="margin: 10px">
                                                                <label for="username" class="col-form-label">User name</label>
                                                                <input disabled required id="username" value="${sessionScope.USER_APP.username}" name="username" type="text" class="form-control" id="regisName"/>
                                                            </div>
                                                            <div class="form-group" style="margin: 10px">
                                                                <label for="firstName" class="col-form-label">First name</label>
                                                                <input required id="firstName" value="${sessionScope.USER_APP.firstName}" name="firstName" type="text" class="form-control"/>
                                                            </div>
                                                            <div class="form-group" style="margin: 10px">
                                                                <label for="lastName" class="col-form-label">Last name</label>
                                                                <input required id="lastName"  value="${sessionScope.USER_APP.lastName}" name="lastName" type="text" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group" style="margin: 10px">
                                                                <label for="regisEmail" class="col-form-label">Email</label>
                                                                <input required value="${sessionScope.USER_APP.email}" name="txtEmail" type="email" class="form-control" id="regisEmail">
                                                            </div>
                                                            <div class="form-group" style="margin: 10px">
                                                                <label for="regisPhone" class="col-form-label">Phone number</label>
                                                                <input required  value="${sessionScope.USER_APP.phoneNumber}" name="txtPhone" type="text" class="form-control" id="regisPhone"/>
                                                                <p style="color: red" id="checkPhone"></p>
                                                            </div>
                                                            <div class="form-group" style="margin: 10px">
                                                                <label for="cardId" class="col-form-label">Card Id</label>
                                                                <input required id="cardId" value="${sessionScope.USER_APP.cardId}" name="cardId" type="text" class="form-control" id="cardId"/>
                                                            </div>
                                                        </div>
                                                        <div >
                                                            <div>
                                                                <div class="form-group">
                                                                    <label for="dob" class="col-form-label">DOB</label><br/>
                                                                    <input value="${sessionScope.USER_APP.dob}" required id="dob" name="dob" type="date"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="form-check-label" for="exampleRadios1">
                                                                        Gender
                                                                    </label>
                                                                    <div class="form-check">
                                                                        <c:if test="${sessionScope.USER_APP.gender eq 0}">
                                                                            <input class="form-check-input" type="radio" name="gender" id="exampleRadios1" value="0" checked>
                                                                        </c:if>
                                                                        <c:if test="${sessionScope.USER_APP.gender ne 0}">
                                                                            <input class="form-check-input" type="radio" name="gender" id="exampleRadios1" value="0">
                                                                        </c:if>
                                                                        <label class="form-check-label" for="exampleRadios1">
                                                                            Female
                                                                        </label>
                                                                    </div>
                                                                    <div class="form-check">
                                                                        <c:if test="${sessionScope.USER_APP.gender eq 1}">
                                                                            <input class="form-check-input" type="radio" name="gender" id="exampleRadios2" value="1" checked>
                                                                        </c:if>
                                                                        <c:if test="${sessionScope.USER_APP.gender ne 1}">
                                                                            <input class="form-check-input" type="radio" name="gender" id="exampleRadios2" value="1">
                                                                        </c:if>
                                                                        <label class="form-check-label" for="exampleRadios2">
                                                                            Male
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div>
                                                                <div class="form-group">
                                                                    <label for="dob" class="col-form-label">Avatar</label><br/>
                                                                    <input id="avatar" onchange="readURL(this);" name="avatar" type="file" accept="image/*" />
                                                                    <input hidden id="avatarTemp" value="<c:url value="${sessionScope.USER_APP.avatar}"/>" />
                                                                    <img id="blah" width="200" height="200" src="<c:url value="${sessionScope.USER_APP.avatar}"/>" alt="your image" />
                                                                </div>
                                                            </div>
                                                        </div>


                                                        <div class="form-group">
                                                            <button type="submit" class="btn btn-primary">Update profile</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../components/footer/footer.jsp"%>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

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
    const PATTERN_TEXT = "[A-Za-z0-9]{1,100}";
    const PATTERN_EMAIL = "[A-Za-z0-9]{1,}@[A-Za-z0-9]{1,}";
    const PATTERN_PHONE = "[0-9]{10}";
    const checkFormRegist = () => {
        // document.getElementById("checkName").innerHTML = "";
        // document.getElementById("checkPass").innerHTML = "";
        document.getElementById("checkConfirm").innerHTML = "";
        document.getElementById("checkPhone").innerHTML = "";
        //var regisName = document.getElementById("regisName").value.trim();
        var regisPass = document.getElementById("regisPass").value.trim();
        var regisCheckPass = document.getElementById("regisCheckPass").value.trim();
        var regisPhone = document.getElementById("regisPhone").value.trim();
        var check = true;

        if (!regisPhone.match(PATTERN_PHONE)) {
            document.getElementById("checkPhone").innerHTML = "invalid phone"
            check = false;
        }
        if (check) {
            if (regisPass !== regisCheckPass) {
                document.getElementById("checkConfirm").innerHTML = "invalid confirm"
                check = false;
            }
        }
        return check;
    }

</script>
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

</body>

</html>