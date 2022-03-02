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

    <title>Medical Result</title>

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
            <%@include file="../components/topbar/topbar.jsp" %>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-8 col-lg-7">
                        <div class="card shadow mb-4">
                            <div class="card-body">
                                <h2>Add medical result</h2>
                                <div class="d-flex justify-content-center">
                                    <form style="width: 400px" id="formLogin"
                                          action="<c:url value='/result/add'/>"
                                          method="POST">
                                        <c:if test="${not empty requestScope.messageParam}">
                                            <div class="alert alert-${requestScope.alert}" role="alert">
                                                    ${requestScope.messageParam}
                                            </div>
                                        </c:if>
                                        <input hidden value="${requestScope.scheduleSelected.id}" name="scheduleId"/>
                                        <input hidden value="${requestScope.scheduleSelected.createdId}"
                                               name="createdId"/>
                                        <label for="diagnosis" style="font-weight: bold" class="col-form-label">Diagnosis</label>
                                        <div class="form-group">
                                            <label for="name" class="col-form-label">Name</label>
                                            <input required id="name" name="name"
                                                   class="form-control"/>
                                        </div>
                                        <c:if test="${requestScope.scheduleSelected.type eq 'TEST_COVID'}">
                                            <input type="radio" id="radPositive" name="conclude" value="POSITIVE"><label style="margin-left: 10px"
                                                                                                                         for="radPositive">POSITIVE</label><br>
                                            <input type="radio" id="radNegative" name="conclude" value="NEGATIVE"><label style="margin-left: 10px"
                                                                                                                         for="radNegative">NEGATIVE</label><br>
                                            <input type="text" hidden name="diagnosis" value=" ">
                                        </c:if>
                                        <c:if test="${requestScope.scheduleSelected.type eq 'HEALTH_CARE'}">
                                        <div class="form-group">
                                            <label for="diagnosis" class="col-form-label">Diagnosis</label>
                                            <textarea required id="diagnosis" name="diagnosis" type="text" rows="3"
                                                      class="form-control"></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="conclude" class="col-form-label">Conclude</label>
                                            <textarea required id="conclude" name="conclude" type="text" rows="3"
                                                      class="form-control"></textarea>
                                        </div>
                                        </c:if>
                                        <div id="method-panels">

                                        </div>
                                        <div class="form-group">
                                            <div class="row" style="justify-content: space-evenly">
                                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                                        data-target="#chooseMethodModal">Add method
                                                </button>
                                                <button type="submit" class="btn btn-success">Add result</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-4 col-lg-5">
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <!-- Card Body -->
                            <div class="card-body">
                                <div class="chart-pie pt-4 pb-2">
                                    <div class="card-body">
                                        <div class="row">
                                            <p class="card-text"
                                               style="text-align: left;font-weight: bold;color: #000000">Id: </p>
                                            <p class="card-text"
                                               style="text-align: left;margin-left: 10px;color: #000000">${requestScope.scheduleSelected.id}</p>
                                        </div>
                                        <div class="row" style="width: 300px">
                                            <p class="card-text"
                                               style="text-align: left;font-weight: bold;color: #000000">
                                                Description: </p>
                                            <p class="card-text"
                                               style="text-align: left;margin-left: 10px;color: #000000;width: 200px">${requestScope.scheduleSelected.description}</p>
                                        </div>
                                        <div class="row">
                                            <p class="card-text"
                                               style="text-align: left;font-weight: bold;color: #000000">Type: </p>
                                            <p class="card-text"
                                               style="text-align: left;margin-left: 10px;color: #000000">
                                                <c:if test="${requestScope.scheduleSelected.type eq 'TEST_COVID'}">Test Covid</c:if>
                                                <c:if test="${requestScope.scheduleSelected.type eq 'HEALTH_CARE'}">Health Care</c:if>
                                            </p>
                                        </div>
                                        <div class="row">
                                            <p class="card-text"
                                               style="text-align: left;font-weight: bold;color: #000000">Schedule: </p>
                                            <p class="card-text"
                                               style="text-align: left;margin-left: 10px;color: #000000">
                                                <fmt:formatDate pattern="dd-MM-yyyy HH:mm a"
                                                                value="${requestScope.scheduleSelected.schedule}"/>
                                            </p>
                                        </div>
                                        <div class="row">
                                            <p class="card-text"
                                               style="text-align: left;font-weight: bold;color: #000000">Status: </p>
                                            <p class="card-text"
                                               style="text-align: left;margin-left: 10px;color: #000000">
                                                <c:if test="${requestScope.scheduleSelected.status}">PENDING</c:if>
                                                <c:if test="${!requestScope.scheduleSelected.status}">COMPLETED</c:if></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="chooseMethodModal" tabindex="-1" role="dialog" aria-labelledby="chooseMethodModal"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Choose method</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div>
                            <table style="width: 500px">
                                <tr>
                                    <td>Method-1</td>
                                    <td><input name="method" type="checkbox" value="" id="method-1"></td>
                                </tr>
                                <tr>
                                    <td>Method-2</td>
                                    <td><input name="method" type="checkbox" value="" id="method-2"></td>
                                </tr>
                                <tr>
                                    <td>Method-3</td>
                                    <td><input name="method" type="checkbox" value="" id="method-3"></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" onclick="onAddMethodButtonClick()" class="btn btn-success"
                                data-dismiss="modal">Add
                        </button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../components/footer/footer.jsp" %>
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

    function onAddMethodButtonClick() {
        var choosedMethods = document.getElementsByName("method");
        var methodPanels = document.getElementById("method-panels");
        methodPanels.innerHTML = "";
        // Lặp qua từng checkbox để lấy giá trị
        for (var i = 0; i < choosedMethods.length; i++) {
            if (choosedMethods[i].checked === true) {
                var methodPanels = document.getElementById("method-panels");
                methodPanels.innerHTML = methodPanels.innerHTML +=
                    "<div id='panelMethod" + (i + 1) + "'" + " style='margin-top: 20px;margin-bottom: 20px'>" +
                    "<label style='font-weight: bold'>Method" + choosedMethods[i].id + "</label>" +

                    "<div class='form-group'>Diagnosis<textarea required type='text' rows='3' class='form-control' name='diagnosis'" + "></textarea></div>" +
                    "<div class='form-group'>Conclude<textarea required type='text' rows='3' class='form-control' name='conclude'" + "></textarea></div>" +
                    "<div class='form-group'><input hidden name='type' value='" + choosedMethods[i].id + "'/></div>" +
                    "</div>";
            }
        }
    }
</script>
</body>

</html>