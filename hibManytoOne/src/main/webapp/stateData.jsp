<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 08/01/24
  Time: 1:19 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
<table border="1px solid black">
    <tr>
        <td>No.</td>
        <td>State Name</td>
        <td>Country Name</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>


    <c:forEach items="${sessionScope.stateList}" var="i" varStatus="j">
        <tr>
            <td>${j.count }</td>
            <td>${i.name }</td>
            <td>${i.country.countryName}</td>
            <td><a href="<%=request.getContextPath() %>/stateController?id=${i.id}&cid=${i.country.country_id}&action=edit">Edit</a></td>
            <td><a href="<%=request.getContextPath() %>/stateController?id=${i.id}&action=delete">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
