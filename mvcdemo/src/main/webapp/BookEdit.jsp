<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 27/12/23
  Time: 11:57 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath() %>/BookController" method="post">
    <input type="hidden" name="action" value="editBook">
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    <c:forEach items="${sessionScope.editData}" var="i">
        <input type="hidden" name="id" value=${ i.id}>
        Book Name   <input type="text" value=${i.name } name="name"><br>
        Author Name <input type="text" value=${i.author } name="author"><br>
    </c:forEach>
    <input type="submit" value="Submit">
</form>
</body>
</html>
