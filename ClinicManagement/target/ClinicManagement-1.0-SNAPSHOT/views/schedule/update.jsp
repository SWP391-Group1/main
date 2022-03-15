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

    <title>Update schedule</title>

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
                                <h2>Update Schedule</h2>
                                <div class="d-flex justify-content-center">
                                    <form style="width: 400px" id="formLogin" onsubmit="return validateSchedule()"
                                          action="<c:url value="/schedule/update"/>" method="POST">
                                        <input hidden value="${requestScope.medicalSchedule.id}" name="id">
                                        <c:if test="${not empty requestScope.messageParam}">
                                            <div class="alert alert-${requestScope.alert}" role="alert">
                                                    ${requestScope.messageParam}
                                            </div>
                                        </c:if>
                                        <div class="form-group">
                                            <label class="form-check-label" for="type">
                                                Type
                                            </label>
                                            <select id="type" name="type" class="form-control form-control-lg">
                                                <c:if test="${requestScope.medicalSchedule.type eq 'HEALTH_CARE'}">
                                                    <option selected value="HEALTH_CARE">Health care</option>
                                                </c:if>
                                                <c:if test="${requestScope.medicalSchedule.type ne 'HEALTH_CARE'}">
                                                    <option value="HEALTH_CARE">Health care</option>
                                                </c:if>
                                                <c:if test="${requestScope.medicalSchedule.type eq 'TEST_COVID'}">
                                                    <option selected value="TEST_COVID">Test covid</option>
                                                </c:if>
                                                <c:if test="${requestScope.medicalSchedule.type ne 'TEST_COVID'}">
                                                    <option value="TEST_COVID">Test covid</option>
                                                </c:if>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="description" class="col-form-label">Description</label>
                                            <textarea required id="description"  name="description" type="text" rows="3" class="form-control">${requestScope.medicalSchedule.description}</textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="schedule" class="col-form-label">Old date</label><br/>
                                            <fmt:formatDate pattern="dd-MM-yyyy HH:mm a" value="${requestScope.medicalSchedule.schedule}"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="schedule" class="col-form-label">New Date</label><br/>
                                            <input required id="schedule" name="schedule" onchange="validateSchedule()" type="datetime-local"/>
                                            <p style="color: red" id="dateMessage"></p>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-primary">Update</button>
                                        </div>
                                    </form>
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

<script>
    function validateSchedule() {
        var dateTimeStr = document.getElementById("schedule").value;
        var dateTime = convertDateToUTC(new Date(dateTimeStr));
        var now = new Date();
        if (isNaN(dateTime.getTime()) || dateTime <= now) {
            document.getElementById("dateMessage").innerHTML
                = "Please select a date and time in the future!";
            return false;
        } else {
            document.getElementById("dateMessage").innerHTML = "";
        }
        return true;
    }

    function convertDateToUTC(date) {
        return new Date(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDate(), date.getUTCHours(), date.getUTCMinutes(), date.getUTCSeconds());
    }
</script>

</body>

</html>