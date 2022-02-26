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

    <title>Home</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">

    <link href="<c:url value='/template/web/makup/css/bootstrap.css' />" rel="stylesheet"/>

    <link href="<c:url value='/template/web/makup/css/font-awesome.min.css' />" rel="stylesheet"/>

    <link href="<c:url value='/template/web/makup/style.css' />" rel="stylesheet"/>

    <link href="<c:url value='/template/web/makup/css/responsive.css' />" rel="stylesheet"/>

    <link href="<c:url value='/template/web/makup/css/colors.css' />" rel="stylesheet"/>

    <link href="<c:url value='/template/web/makup/css/version/tech.css' />" rel="stylesheet"/>

    <link rel="stylesheet"
          href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet"
          href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type='text/javascript'
            src='<c:url value="/template/health-news/js/jquery-2.2.3.min.js" />'></script>
    <script
            src="<c:url value='/template/health-news/assets/js/jquery.2.1.1.min.js' />"></script>

    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <script src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>
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
                                <div class="panel-body" >
                                    <section class="section single-wrapper">
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
                                                    <div class="page-wrapper">
                                                        <div class="blog-title-area text-center">
                                                            <%--                        <ol class="breadcrumb hidden-xs-down">--%>
                                                            <%--                            <li class="breadcrumb-item"><a href='<c:url value="/home"/>'>Home page</a></li>--%>
                                                            <%--                            <li class="breadcrumb-item active">${news.title}</li>--%>
                                                            <%--                        </ol>--%>

                                                            <h3>${news.title}</h3>

                                                            <img src="${news.thumbnail}"/>

                                                            <div class="blog-meta big-meta">
                                                                <small><a>${news.createdStamp}</a></small>
                                                            </div>
                                                        </div>

                                                        <div class="blog-content">
                                                            ${news.content}
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </div>

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


<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

<script type="text/javascript"
        src="<c:url value='/template/web/makup/js/tether.min.js' />"></script>

<script type="text/javascript"
        src="<c:url value='/template/web/makup/js/custom.js' />"></script>

<script type="text/javascript"
        src="<c:url value='/template/web/makup/js/bootstrap.min.js' />"></script>

<script type="text/javascript"
        src="<c:url value='/template/web/makup/js/custom.js' />"></script>

<script
        src="<c:url value='/template/health-news/assets/js/bootstrap.min.js' />"></script>
<script
        src="<c:url value='/template/health-news/assets/js/jquery-ui.custom.min.js' />"></script>
<script
        src="<c:url value='/template/health-news/assets/js/jquery.ui.touch-punch.min.js' />"></script>
<script
        src="<c:url value='/template/health-news/assets/js/jquery.easypiechart.min.js' />"></script>
<script
        src="<c:url value='/template/health-news/assets/js/jquery.sparkline.min.js' />"></script>
<script
        src="<c:url value='/template/health-news/assets/js/jquery.flot.min.js' />"></script>
<script
        src="<c:url value='/template/health-news/assets/js/jquery.flot.pie.min.js' />"></script>
<script
        src="<c:url value='/template/health-news/assets/js/jquery.flot.resize.min.js' />"></script>
<script
        src="<c:url value='/template/health-news/assets/js/ace-elements.min.js' />"></script>
<script src="<c:url value='/template/health-news/assets/js/ace.min.js' />"></script>
<script
        src="<c:url value='/template/health-news/assets/js/bootstrap.min.js'/>"></script>

<script
        src="<c:url value='/template/health-news/assets/js/jquery-ui.min.js'/>"></script>
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