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
                                                        <h2>Change password</h2>
                                                    </div>
                                                </div>
                                                <div class="d-flex justify-content-center">
                                                    <form onsubmit="return checkForm(${sessionScope.USER_APP.password})"
                                                          action="<c:url value="/user/password"/>" method="POST">
                                                        <c:if test="${not empty requestScope.messageParam}">
                                                            <div class="alert alert-${requestScope.alert}" role="alert">
                                                                    ${requestScope.messageParam}
                                                            </div>
                                                        </c:if>
                                                        <div class="form-group">
                                                            <label for="oldPass" class="col-form-label">Old password</label>
                                                            <input required name="oldPass" type="password" class="form-control" id="oldPass" style="width: 600px;">
                                                            <p style="color: red" id="checkOldPass"></p>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="newPass" class="col-form-label">New Password</label>
                                                            <input required name="newPass" type="password" class="form-control" id="newPass">
                                                            <p style="color: red" id="checkNewPass"></p>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="confirmNewPass" class="col-form-label">Confirm Password</label>
                                                            <input required name="confirmNewPass" type="password" class="form-control" id="confirmNewPass">
                                                            <p style="color: red" id="checkConfirmNewPass"></p>
                                                        </div>
                                                        <div class="form-group">
                                                            <button type="submit" class="btn btn-primary">Change password</button>
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
    const checkForm = (password) => {
        document.getElementById("checkOldPass").innerHTML = "";
        document.getElementById("checkConfirmNewPass").innerHTML = "";
        var oldPass = document.getElementById("oldPass").value.trim();
        var newPass = document.getElementById("newPass").value.trim();
        var confirmNewPass = document.getElementById("confirmNewPass").value.trim();
        console.log("Old: " + oldPass);
        console.log("Pass: " + password);
        if (oldPass != password) {
            document.getElementById("checkOldPass").innerHTML = "Old password incorrect"
            return false;
        }
        console.log("Old: " + oldPass);
        console.log("New: " + newPass);
        console.log("Confirm: " + confirmNewPass);

        if (newPass != confirmNewPass) {
            document.getElementById("checkConfirmNewPass").innerHTML = "Confirm new password incorrect";
            return false;
        }
        return true;
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