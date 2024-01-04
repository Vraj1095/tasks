<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 04/01/24
  Time: 3:38 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath() %>/studentController" method="post">
    <input type="hidden" name="action" value="update">
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    <c:forEach items="${sessionScope.studentList}" var="i">
        <input type="hidden" name="id" value=${ i.id}>
        First Name <input type="text" value=${i.firstName } name="firstName"><br>
        Last Name <input type="text" value=${i.lastName } name="lastName"><br>
        Email <input type="text" value=${i.email } name="email"><br>
        College <input type="text" value=${i.studentDetail.college } name="college"><br>
        No of Problems Solved <input type="text" value=${i.studentDetail.noOfProblemsSolved } name="noOfProblemsSolved"><br>
    </c:forEach>
    <input type="submit" value="Submit">
</form>
</body>
</html>
