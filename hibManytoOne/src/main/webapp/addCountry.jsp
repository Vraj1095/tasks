<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 05/01/24
  Time: 12:12 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/countryController" method="post">
    <input type="hidden" name="action" value="insert">
    Country Name <input type="text" name="name"> <br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
