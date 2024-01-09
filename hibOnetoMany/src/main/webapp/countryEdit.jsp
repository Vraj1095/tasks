<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 09/01/24
  Time: 11:41 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach  var="i" items="${sessionScope.countryList}">
<form action="countryController" method="post">
    <input type="hidden" value="update" name="action">
    <input type="hidden" value="${i.id}" name="id">
    <table>
        <tr>
            <td>Country Name</td>
            <td><input type="text" name="cntname" placeholder="Country Name" value="${i.name}"></td>
        </tr>
        <tr>
            <td>State Name: </td>
            <td>
                <label>
                    <select name="selectedStates" multiple>
                        <c:forEach var="i" items="${i.states}">
                            <option value="${i.id}" selected>${i.name}</option>
                        </c:forEach>
                        <c:forEach  var="i" items="${sessionScope.stateList}">
                            <option value="${i.id}">${i.name}</option>
                        </c:forEach>
                    </select>
                </label>
            </td>
        </tr>
        <tr><td></td></tr>
        </c:forEach>
        <tr>
            <td><button type="submit" class="btn">Submit</button></td>&nbsp;&nbsp;
            <td><button type="reset" class="btn">Reset</button></td>
        </tr>
    </table>
</form>
</body>
</html>
