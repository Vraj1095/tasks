<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 08/01/24
  Time: 5:32 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <style>
        .container{
            width: fit-content;
            border: 1px solid black;
            margin-top: 20px;
            padding: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
    <table border="1px solid black" class="table table-dark">
        <thead>
        <tr>
            <th>No.</th>
            <th>Country Name</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="i" items="${sessionScope.countryList}" varStatus="j">
            <tr>
                <td>${j.count}</td>
                <td>${i.countryName}</td>
                <td><a href="<%=request.getContextPath() %>/countryController?id=${i.country_id}&action=edit">Edit</a></td>
                <td><a href="<%=request.getContextPath() %>/countryController?id=${i.country_id}&action=delete">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
