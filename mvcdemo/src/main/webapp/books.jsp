<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 27/12/23
  Time: 11:11 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath() %>/BookController" method="post">
    <input type="hidden" name="action" value="insertBook">
    Book Name <input type="text" name="name"> <br><br>
    Author Name <input type="text" name="author"> <br><br>
    <input type="submit" value="Submit">
</form>
<a href="<%=request.getContextPath() %>/BookController?act=searchBook">Click here search data</a>

</body>
</html>
