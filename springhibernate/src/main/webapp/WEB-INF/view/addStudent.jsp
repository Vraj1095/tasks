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
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Add New Student</h1>
<form:form method="post" action="saveStudent" modelAttribute="student">
<table>
    <tr>
        <td>First Name : </td>
        <td><form:input path="firstName"  /></td>
    </tr>
    <tr>
        <td>Last Name :</td>
        <td><form:input path="lastName" /></td>
    </tr>
    <tr>
        <td>Email Id :</td>
        <td><form:input path="email" /></td>
    </tr>
    <tr>
        <td>College :</td>
        <td><form:input path="studentDetail.college" /></td>
    </tr>
    <tr>
        <td>No of Problems Solved :</td>
        <td><form:input path="studentDetail.noOfProblemsSolved" /></td>
    </tr>
    <tr>
        <td><form:hidden path="studentDetail.id" /></td>
        <td><form:hidden path="id"/> </td>
    </tr>
    <tr>
        <td><input type="submit" value="Save" /></td>
    </tr>
</table>
</form:form>
<a href="<%=request.getContextPath() %>/searchStudent">Click here search data</a>

