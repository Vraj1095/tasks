<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 11/01/24
  Time: 5:51 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1px solid black">
    <tr>
        <th>No</th>
        <th>Name</th>
        <th>Salary</th>
        <th>Designation</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${employeeList}" var="p" varStatus="j">
    <tr>
        <td>${j.count}</td>
        <td>${p.name}</td>
        <td>${p.salary}</td>
        <td>${p.designation}</td>
        <td><a href="editEmployee?Id=${p.id}">Edit</a></td>
        <td><a href="deleteEmployee?Id=${p.id}">Delete</a>
    </tr>
    </c:forEach>
</body>
</html>
