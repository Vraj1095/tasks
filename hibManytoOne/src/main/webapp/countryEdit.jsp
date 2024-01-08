<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 08/01/24
  Time: 6:46 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<%=request.getContextPath() %>/countryController" method="post">
    <input type="hidden" name="action" value="update">
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    <c:forEach items="${sessionScope.countryData}" var="i">
        <input type="hidden" name="id" value=${ i.country_id}>
        Country Name <input type="text" value=${i.countryName } name="countryName"><br>
    </c:forEach>
    <input type="submit" value="Submit">
</form>
</body>
</html>
