<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 28/12/23
  Time: 12:10 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/userController" method="post">
    <input type="hidden" name="action" value="saveUser">
    First Name <input type="text" name="firstName"> <br><br>
    Last Name <input type="text" name="lastName"> <br><br>
    <input type="submit" value="Submit">
</form>
<a href="<%=request.getContextPath() %>/userController?action=searchUser">Click here search data</a>

