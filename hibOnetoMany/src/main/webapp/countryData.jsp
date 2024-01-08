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
        </tr>
            <c:forEach items="${sessionScope.countryList}" var="i" varStatus="j">
        <tr>
            <td>${j.count }</td>
            <td>${i.name }</td>
        </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
