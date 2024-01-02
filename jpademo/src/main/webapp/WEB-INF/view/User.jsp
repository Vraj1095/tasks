<%--
  Created by IntelliJ IDEA.
  User: BAPS
  Date: 2/17/2022
  Time: 12:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>REGISTRATION PAGE</title>
</head>
<%@taglib  prefix="f" uri="http://www.springframework.org/tags/form"%>
<body>
<f:form action="insert" method="post" modelAttribute="user">

    NAME:<f:input path="name"/><br/><br/>

    email:<f:input path="email"/><br/>

    mobile no:<f:input path="mobile"/><br/>

    <f:hidden path="id"/>

    <input type="submit" value="Submit">
</f:form><br/>
<a href="search">SEARCH</a><br/>
</body>
</html>
