<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 04/01/24
  Time: 3:32 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<table border="1px solid black">
    <tr>
        <td>No.</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Email</td>
        <td>College</td>
        <td>No of Problems Solved</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>


    <c:forEach items="${studentList}" var="i" varStatus="j">
        <tr>
            <td>${j.count }</td>
            <td>${i.firstName }</td>
            <td>${i.lastName }</td>
            <td>${i.email }</td>
            <td>${i.studentDetail.college }</td>
            <td>${i.studentDetail.noOfProblemsSolved }</td>
            <td><a href="<%=request.getContextPath() %>/editStudent?id=${i.id}">Edit</a></td>
            <td><a href="<%=request.getContextPath() %>/deleteStudent?id=${i.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
