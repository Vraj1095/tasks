<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 05/01/24
  Time: 12:16 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="countryController" method="post">
    <input type="hidden" value="insert" name="action">
    <table border="1px solid black">
        <tr>
            <td>No</td>
            <td>Country Name</td>
            <td>States</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
            <c:forEach items="${sessionScope.countryList}" var="i" varStatus="j">
        <tr>
            <td>${j.count }</td>
            <td>${i.name }</td>
            <c:forEach items="${i.states}" var="s">
                <c:set var="myVar" value="${myVar}${empty myVar ? '' : ','}${s.name}" />
            </c:forEach>
            <td>
                <c:out value="${myVar}"></c:out>
            </td>
            <c:set var="myVar" value=""/>
            <td><a href="<%=request.getContextPath() %>/countryController?id=${i.id}&&action=edit">Edit</a></td>
            <td><a href="<%=request.getContextPath() %>/countryController?id=${i.id}&action=delete">Delete</a></td>
        </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
