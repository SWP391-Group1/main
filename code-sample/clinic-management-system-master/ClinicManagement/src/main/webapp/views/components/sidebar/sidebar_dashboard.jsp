<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Custom fonts for this template-->
    <link href="./../template/layout_dashboard/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
          type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../../../template/layout_dashboard/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body>
<div>
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<c:url value="/user/home"/>">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">Clinic <sup>Management</sup></div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="<c:url value='/user/home'/>">
                <i class="fas fa-home"></i>
                <span>Home</span></a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="<c:url value='/schedule/add'/>">
                <i class="fas fa-solid fa-calendar-check"></i>
                <span>Add schedule</span></a>
        </li>
        <!-- Divider -->
<%--        <hr class="sidebar-divider">--%>

<%--        <!-- Heading -->--%>
<%--        <div class="sidebar-heading">--%>
<%--            Interface--%>
<%--        </div>--%>

<%--        <!-- Divider -->--%>
<%--        <hr class="sidebar-divider">--%>
<%--        <!-- Heading -->--%>
<%--        <div class="sidebar-heading">--%>
<%--            Addons--%>
<%--        </div>--%>

    </ul>
</div>

<!-- Bootstrap core JavaScript-->
<script src="./../template/layout_dashboard/vendor/jquery/jquery.min.js"></script>
<script src="./../template/layout_dashboard/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="./../template/layout_dashboard/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="./../template/layout_dashboard/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="./../template/layout_dashboard/vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="./../template/layout_dashboard/js/demo/chart-area-demo.js"></script>
<script src="./../template/layout_dashboard/js/demo/chart-pie-demo.js"></script>
</body>
</html>
