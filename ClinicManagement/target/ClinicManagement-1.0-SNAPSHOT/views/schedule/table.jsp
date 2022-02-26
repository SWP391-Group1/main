<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
          integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <%--    <link--%>
    <%--            href="<c:url value='/template/login/style.css' />"--%>
    <%--            rel="stylesheet" type="text/css" media="all"/>--%>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<a href="<c:url value="/schedule/add"/>" type="button" class="btn btn-success">Add Schedule</a>
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
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.medicalScheduleList}" var="schedule">
        <tr>
            <td>${schedule.id}</td>
            <td>${schedule.description}</td>
            <td>
                <c:if test="${schedule.type eq 'TEST_COVID'}">Test Covid</c:if>
                <c:if test="${schedule.type eq 'HEALTH_CARE'}">Health Care</c:if>
            </td>
            <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm a"
                                value="${schedule.schedule}"/></td>
            <td>${schedule.status}</td>
            <td>
                <button type="button" class="btn btn-primary">Detail</button>
            </td>

            <td>
                <c:url var="urlUpdate" value="/schedule/update">
                    <c:param name="id" value="${schedule.id}"/>
                </c:url>
                <a type="button" class="btn btn-warning" href="${urlUpdate}">Update</a>
            </td>

            <td>
                <form action="<c:url value="/schedule/delete"/>" method="POST">
                    <input name="id" hidden value="${schedule.id}"/>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
