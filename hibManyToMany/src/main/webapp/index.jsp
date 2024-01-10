<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<%--<h1><%= "Hello World!" %></h1>--%>
<br/>

<a href="addEmployee.jsp">Insert Employee</a><br><br>
<a href="projectController?flag=insert">Insert Project</a><br><br>
<a href="projectController?flag=search">View Projects</a><br><br>
<a href="employeeController?flag=search">View Employees</a><br><br>
</body>
</html>