<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 05/01/24
  Time: 12:12 pm
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
        <td>No.</td>
        <td>State Name</td>
    </tr>


    <c:forEach items="${sessionScope.stateList}" var="i" varStatus="j">
        <tr>
            <td>${j.count }</td>
            <td>${i.name }</td>
<%--            <td><a href="<%=request.getContextPath() %>/studentController?id=${i.id}&action=edit">Edit</a></td>--%>
<%--            <td><a href="<%=request.getContextPath() %>/studentController?id=${i.id}&action=delete">Delete</a></td>--%>
        </tr>
    </c:forEach>
</table>
</body>
</html>
