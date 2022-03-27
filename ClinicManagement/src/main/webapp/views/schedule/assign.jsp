<%@ page import="com.management.clinic.dao.impl.UserDAOImpl" %>
<%@ page import="com.management.clinic.entity.UserApp" %>
<%@ page import="java.util.List" %>
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
                                <h2>Assign</h2>
                                <div class="d-flex justify-content-center">
                                    <form style="width: 400px" onsubmit="return checkAssign()" action="<c:url value='/schedule/assign'/>" method="POST">
                                        <input hidden value="${requestScope.medicalSchedule.id}" name="id">
                                        <div class="form-group">
                                            <label class="col-form-label">Type</label>
                                            <textarea required readonly id="type" type="text" rows="1" class="form-control">${requestScope.medicalSchedule.type}</textarea>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-form-label">Date</label><br/>
                                            <fmt:formatDate pattern="dd-MM-yyyy HH:mm a" value="${requestScope.medicalSchedule.schedule}"/>
                                        </div>

                                        <div>
                                            <label class="col-form-label">Assign to</label><br/>
                                            <select name="doctor_acc" id="doctor_acc">
                                                <option value="">Choose a doctor</option>
                                                <%
                                                    UserDAOImpl daoUser = new UserDAOImpl();
                                                    List<UserApp> listDr = daoUser.getAllDoctor();
                                                    for(UserApp item : listDr){
                                                %>
                                                <option value="<%=item.getId() %>">Dr. <%=item.getFirstName() + " " + item.getLastName() %></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        </br>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-primary">Assign</button>
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
    function checkAssign() {
        var n = document.getElementById("doctor_acc");
        if(n.value != "") {
            return true;
        } else {
            alert("Choose a doctor");
            return false;
        }
    }
</script>

</body>

</html>