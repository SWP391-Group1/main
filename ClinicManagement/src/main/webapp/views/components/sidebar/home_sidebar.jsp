<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Custom fonts for this template-->
    <link href="../../../template/layout_dashboard/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
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
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<c:url value="/home"/>">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">Clinic <br/><br/><sup>Management</sup></div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="<c:url value='/user/sign-in'/>">
                <i class="fas fa-home"></i>
                <span>Login</span></a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="<c:url value='/schedule/add'/>">
                <i class="fas fa-solid fa-plus"></i>
                <span>Add schedule</span></a>
        </li>
    </ul>
</div>
<script src="./../template/layout_dashboard/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->

<!-- Custom scripts for all pages-->
<script src="./../template/layout_dashboard/js/sb-admin-2.min.js"></script>

</body>
</html>
