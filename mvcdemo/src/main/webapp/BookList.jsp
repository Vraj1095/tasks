<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 27/12/23
  Time: 11:44 am
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1px solid black">
        <tr>
            <td>Id</td>
            <td>Book Name</td>
            <td>Author Name</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

        <c:forEach items="${sessionScope.bookData}" var="i">
            <tr>
                <td>${i.id }</td>
                <td>${i.name }</td>
                <td>${i.author}</td>
                <td><a href="<%=request.getContextPath() %>/BookController?act=editBook&id=${i.id}">Edit</a></td>
                <td><a href="<%=request.getContextPath() %>/BookController?act=deleteBook&id=${i.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
