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

    <title>Schedule</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

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
                <div class="row">
                    <!-- Area Chart -->
                    <div class="col-xl-12 col-lg-12">
                        <div class="card shadow mb-4">
                            <!-- Card Body -->
                            <div class="card-body">
                                <div>
                                    <h2>List schedule booking</h2>
                                    <c:if test="${sessionScope.USER_ROLE eq 'PATIENT'}">
                                        <a href="<c:url value='/schedule/add'/>" type="button" class="btn btn-success">Add
                                            Schedule</a>
                                    </c:if>
                                </div>
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Description</th>
                                        <th>Type</th>
                                        <th>Schedule</th>
                                        <th>Status</th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope.medicalScheduleList}" var="schedule">
                                        <tr>
                                            <td>${schedule.id}</td>
                                            <td>
                                                <div style="width: 100px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
                                                        ${schedule.description}
                                                </div>
                                            </td>
                                            <td>
                                                <c:if test="${schedule.type eq 'TEST_COVID'}">Test Covid</c:if>
                                                <c:if test="${schedule.type eq 'HEALTH_CARE'}">Health Care</c:if>
                                            </td>
                                            <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm a"
                                                                value="${schedule.schedule}"/></td>
                                            <td>
                                                <c:if test="${schedule.status}">
                                                    <div style="font-size: 12px;text-align: center;padding: 2px;background-color: orange;border-radius: 3px;color:#ffffff;font-weight: bold">
                                                        PENDING
                                                    </div>
                                                </c:if>
                                                <c:if test="${!schedule.status}">
                                                    <div style="font-size: 12px;text-align: center;padding: 2px;background-color: green;border-radius: 3px;color:#ffffff;font-weight: bold">
                                                        COMPLETED
                                                    </div>
                                                </c:if>
                                            </td>
                                            <td></td>
                                            <td>
                                                <c:url value="/result/add" var="urlResultAdd">
                                                    <c:param name="scheduleId" value="${schedule.id}"/>
                                                </c:url>
                                                <c:url value="/result/view" var="urlResultView">
                                                    <c:param name="scheduleId" value="${schedule.id}"/>
                                                </c:url>
                                                <c:if test="${schedule.status and (sessionScope.USER_ROLE eq 'DOCTOR')}"><a
                                                        type="button"
                                                        style="background-color: #93c975;color: #ffffff"
                                                        class="btn"
                                                        href="${urlResultAdd}">Create
                                                    result</a></c:if>
                                                <c:if test="${!schedule.status}"><a type="button"
                                                                                    style="background-color: #66aede;color: #ffffff"
                                                                                    class="btn"
                                                                                    href="${urlResultView}">View
                                                    result</a></c:if>
                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                                        data-target="#detailSchedule${schedule.id}">Detail
                                                </button>
                                            </td>
                                                <%--                                            <td>--%>
                                                <%--                                                <c:url var="urlUpdate" value="/schedule/update">--%>
                                                <%--                                                    <c:param name="id" value="${schedule.id}"/>--%>
                                                <%--                                                </c:url>--%>
                                                <%--                                                <a type="button" class="btn btn-warning" href="${urlUpdate}">Update</a>--%>
                                                <%--                                            </td>--%>

                                            <td>
                                                <c:if test="${sessionScope.USER_ROLE eq 'PATIENT'}">
                                                    <form action="<c:url value='/schedule/delete'/>" method="POST">
                                                        <input name="id" hidden value="${schedule.id}"/>
                                                        <button type="submit" class="btn btn-danger">Delete</button>
                                                    </form>
                                                </c:if>
                                            </td>

                                        </tr>
                                        <div class="modal fade" id="detailSchedule${schedule.id}" tabindex="-1"
                                             role="dialog" aria-labelledby="detailSchedule${schedule.id}"
                                             aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">Detail</h5>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="card-body">
                                                            <div class="row">
                                                                <p class="card-text"
                                                                   style="text-align: left;font-weight: bold;color: #000000">
                                                                    Id: </p>
                                                                <p class="card-text"
                                                                   style="text-align: left;margin-left: 10px;color: #000000">${schedule.id}</p>
                                                            </div>
                                                            <div class="row" style="width: 300px">
                                                                <p class="card-text"
                                                                   style="text-align: left;font-weight: bold;color: #000000">
                                                                    Description: </p>
                                                                <p class="card-text"
                                                                   style="text-align: left;margin-left: 10px;color: #000000;width: 200px">${schedule.description}</p>
                                                            </div>
                                                            <div class="row">
                                                                <p class="card-text"
                                                                   style="text-align: left;font-weight: bold;color: #000000">
                                                                    Type: </p>
                                                                <p class="card-text"
                                                                   style="text-align: left;margin-left: 10px;color: #000000">
                                                                    <c:if test="${schedule.type eq 'TEST_COVID'}">Test Covid</c:if>
                                                                    <c:if test="${schedule.type eq 'HEALTH_CARE'}">Health Care</c:if>
                                                                </p>
                                                            </div>
                                                            <div class="row">
                                                                <p class="card-text"
                                                                   style="text-align: left;font-weight: bold;color: #000000">
                                                                    Schedule: </p>
                                                                <p class="card-text"
                                                                   style="text-align: left;margin-left: 10px;color: #000000">
                                                                    <fmt:formatDate pattern="dd-MM-yyyy HH:mm a"
                                                                                    value="${schedule.schedule}"/>
                                                                </p>
                                                            </div>
                                                            <div class="row">
                                                                <p class="card-text"
                                                                   style="text-align: left;font-weight: bold;color: #000000">
                                                                    Status: </p>
                                                                <p class="card-text"
                                                                   style="text-align: left;margin-left: 10px;color: #000000">
                                                                    <c:if test="${schedule.status}">PENDING</c:if>
                                                                    <c:if test="${!schedule.status}">COMPLETED</c:if></p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-dismiss="modal">Close
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
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