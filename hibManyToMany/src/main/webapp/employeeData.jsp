<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 09/01/24
  Time: 2:54 pm
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
    <h2>Employee List </h2>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
    <table border="1px solid black" class="table table-dark">
        <tr>
            <td>Id</td>
            <td>Employee Name</td>
        </tr>
        <c:forEach var="i" items="${sessionScope.employeeList}" >
            <tr>
                <td>${i.employeeId}</td>
                <td>${i.employeeName}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
