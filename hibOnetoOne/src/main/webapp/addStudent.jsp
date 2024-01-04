<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 04/01/24
  Time: 3:31 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/studentController" method="post">
    <input type="hidden" name="action" value="insert">
    First Name <input type="text" name="firstName"> <br><br>
    Last Name <input type="text" name="lastName"> <br><br>
    Email <input type="text" name="email"> <br><br>
    College <input type="text" name="college"> <br><br>
    No of Problem Solved <input type="number" name="noOfProblemsSolved"> <br><br>
    <input type="submit" value="Submit">
</form>
<a href="<%=request.getContextPath() %>/userController?action=searchUser">Click here search data</a>

