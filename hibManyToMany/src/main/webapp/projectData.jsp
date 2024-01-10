<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 09/01/24
  Time: 5:04 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Data</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container{
            width: fit-content;
            border: 1px solid black;
            margin-top: 20px;
            margin: auto;
            padding: 10px;
        }
        .btn{
            width: 100%;
            height: 30px;
            background: black;
            color: aliceblue;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Employee Project</h2>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
    <table border="1px solid black" class="table table-dark">
        <tr>
            <td>Id</td>
            <td>Project Name</td>
            <td>Employee Names</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <c:forEach var="i" items="${sessionScope.projectList}" varStatus="j">
            <tr>
                <td>${j.count}</td>
                <td>${i.projectName}</td>
                <c:forEach items="${i.employees}" var="s">
                    <c:set var="myVar" value="${myVar}${empty myVar ? '' : ','}${s.employeeName}" />
                </c:forEach>
                <td>
                    <c:out value="${myVar}"></c:out>
                </td>
                <c:set var="myVar" value=""/>
                <td><a href="projectController?flag=edit&id=${i.projectId}">Edit</a></td>
                <td><a href="projectController?flag=delete&id=${i.projectId}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
