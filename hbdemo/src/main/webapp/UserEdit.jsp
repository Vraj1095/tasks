<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 28/12/23
  Time: 4:56 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath() %>/userController" method="post">
  <input type="hidden" name="action" value="updateUser">
  <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
  <c:forEach items="${sessionScope.editData}" var="i">
    <input type="hidden" name="id" value=${ i.id}>
    First Name <input type="text" value=${i.firstName } name="firstName"><br>
    Last Name <input type="text" value=${i.lastName } name="lastName"><br>
  </c:forEach>
  <input type="submit" value="Submit">
</form>

</body>
</html>
