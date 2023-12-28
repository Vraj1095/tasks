<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 28/12/23
  Time: 4:53 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<table border="1px solid black">
    <tr>
        <td>Id</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

    <c:forEach items="${sessionScope.userData}" var="i">
        <tr>
            <td>${i.id }</td>
            <td>${i.firstName }</td>
            <td>${i.lastName }</td>
            <td><a href="<%=request.getContextPath() %>/userController?id=${i.id}&action=editUser">Edit</a></td>
            <td><a href="<%=request.getContextPath() %>/userController?id=${i.id}&action=deleteUser">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
