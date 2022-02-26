<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${news.title}</title>
</head>
<body>
<%@include file="../components/topbar/home_topbar.jsp"%>
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
</body>
</html>
