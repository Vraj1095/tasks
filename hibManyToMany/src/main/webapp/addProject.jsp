<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 09/01/24
  Time: 2:54 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .container{
            width: 40%;
            border: 1px solid black;
            margin-top: 20px;
            margin: auto;
            padding: 10px;
        }
        .btn{
            width: 100%;
            height: 30px;
            background: black;
            color: aliceblue;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Insert Project</h2>
        <form action="projectController" method="post">
            <table>
                <input type="hidden" name="flag" value="insert">
                <tr>
                    <td>Project Name</td>
                    <td><input type="text" name="projectName" placeholder="enter Project"></td>
                </tr>
                <tr>
                    <td>Employees:</td>
                    <td>
                        <c:forEach var="j" items="${sessionScope.employeeList}">
                            <input type="checkbox" name="employeeId" value="${j.employeeId}">${j.employeeName}
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td><button type="submit" class="btn"> Submit</button></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
