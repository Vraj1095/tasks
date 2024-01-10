<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 09/01/24
  Time: 5:06 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Project</title>
</head>
<body>
<div class="container">
    <h1>Update Employee</h1>
    <form action="projectController" method="post">
        <input type="hidden" value="update" name="flag">

        <table>
            <c:forEach  var="i" items="${sessionScope.projectList}">
                <tr>
                    <td><input type="hidden" name="projectId" placeholder="" value="${i.projectId}"></td>
                </tr>
                <tr>
                    <td>Project Name</td>
                    <td><input type="text" name="empName" placeholder="Employee Name" value="${i.projectName}" ></td>
                </tr>
                <tr>
                <tr>
                    <td>Employees:</td>
                    <td>
                        <c:forEach var="j" items="${i.employees}">
                            <input type="checkbox" name="employeeId" value="${j.employeeId}" checked>${j.employeeName}
                        </c:forEach>
                        <c:forEach var="j" items="${sessionScope.employeeList}">
                            <input type="checkbox" name="employeeId" value="${j.employeeId}">${j.employeeName}
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td><button type="submit" class="btn">Update</button></td>

            </tr>

        </table>

    </form>
</div>
</body>
</html>
