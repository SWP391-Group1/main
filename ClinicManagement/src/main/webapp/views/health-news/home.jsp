<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home page</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

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

<%--    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">--%>
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
<%--    <link href="css/sb-admin-2.min.css" rel="stylesheet">--%>
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">

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
    <%@include file="../components/sidebar/home_sidebar.jsp" %>
    <div id="content-wrapper" class="d-flex flex-column">
        <%--    <section class="section">--%>
        <div id="content">
            <%@include file="../components/topbar/home_topbar.jsp" %>
            <%--            class="container"--%>
            <div class="container-fluid">
                <form action="<c:url value='/home'/>" id="formSubmit" method="get">
                    <div class="row">
                        <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
                            <div class="page-wrapper">
                                <div class="blog-top clearfix">
                                    <h4 class="pull-left">Health news <a><i class="fa fa-rss"></i></a></h4>
                                </div>

                                <div class="blog-list clearfix">
                                    <c:forEach var="item" items="${model.listResult}">
                                        <div class="blog-box row">
                                            <div class="col-md-4">
                                                <div class="post-media">
                                                    <a href='<c:url value=""/>'>
                                                        <img src="${item.thumbnail}" alt="" class="img-fluid">
                                                        <div class="hovereffect"></div>
                                                    </a>
                                                </div><!-- end media -->
                                            </div><!-- end col -->

                                            <div class="blog-meta big-meta col-md-8">
                                                <h4>
                                                    <a href='<c:url value="/news/details?id=${item.id}&user=false"/>'>${item.title}</a>
                                                </h4>
                                                <p>${item.shortDescription}</p>
                                                <small><a
                                                        href='<c:url value="/news/details?id=${item.id}&user=false"/>'>${item.createdStamp}</a></small>
                                                <small><a
                                                        href='<c:url value="/news/details?id=${item.id}&user=false"/>'>${item.createdBy}</a></small>
                                            </div>
                                        </div>

                                        <hr class="invis">
                                    </c:forEach>
                                </div>

                            </div>

                            <hr class="invis">

                            <div class="row">
                                <div class="col-md-12">
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination" id="pagination"></ul>
                                        <input type="hidden" value="" id="page" name="page"/>
                                        <input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
                                        <input type="hidden" value="" id="sortName" name="sortName"/>
                                        <input type="hidden" value="" id="sortBy" name="sortBy"/>
                                        <input type="hidden" value="false" id="user" name="user"/>
                                    </nav>
                                </div>
                            </div>

                        </div>

                        <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
                            <div class="sidebar">
                                <div class="widget">
                                    <div class="banner-spot clearfix">
                                        <div class="banner-img">
                                            <img src='<c:url value="/template/web/upload/5k.png"/>' class="img-fluid">
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <%--    </section>--%>
    </div>
</div>

<script>
    console.log("Start paging............")
    var totalPages = ${model.totalPage};
    var currentPage = ${model.page};
    var limit = 2;
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#maxPageItem').val(limit);
                    $('#page').val(page);
                    $('#sortName').val('title');
                    $('#sortBy').val('desc');
                    $('#formSubmit').submit();
                }
            }
        });
    });
</script>

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
</body>
</html>