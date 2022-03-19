<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <!-- Content Row -->
                <div class="row">
                    <div class="col-xl-12 col-lg-11">
                        <div class="card shadow mb-4">
                            <!-- Card Body -->
                            <div class="card-body">
                                <div>
                                    <h2>List health news</h2>
                                    <a href="<c:url value='/news/add'/>" type="button" class="btn btn-success">Add
                                        health news</a>
                                </div>
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th style="width: 5%;">Id</th>
                                        <th style="width: 25%;">Title</th>
                                        <th style="width: 60%;">Content</th>
                                        <th style="width: 5%; margin-right: 5px;"></th>
                                        <th style="width: 5%;"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="item" items="${listNews}">
                                        <tr>
                                            <td>${item.id}
                                            <td>${item.title}
                                            <td>${item.content}
                                            <td>
                                                <a class="btn btn-warning"
                                                   data-toggle="tooltip"
                                                   title="Update" href='<c:url value="/news/add?editId=${item.id}"/>'>
                                                    Update
                                                </a>
                                            </td>
                                            <td>
                                                <form action="<c:url value="/news/delete?id=${item.id}"/>"
                                                      method="POST">
                                                    <button type="submit" class="btn btn-danger">Delete</button>
                                                </form>
                                            </td>
                                        </tr>
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
<script>
    $('#deleteNews').click(async function (e) {
        e.preventDefault();
        console.log("Call delete")
        $('#btnDeleteNews').trigger("click");
    });
</script>
</html>