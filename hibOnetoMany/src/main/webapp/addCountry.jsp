<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 05/01/24
  Time: 12:12 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="countryController" method="post">
    <input type="hidden" value="insert" name="action">
    <table>
        <tr>
            <td>Enter Country Name</td>
            <td><input type="text" name="cntname" placeholder="Country Name"></td>
        </tr>
        <tr>

            <td>State Name: </td>
            <td>
                <label>
                    <select name="selectedStates" multiple>

                        <c:forEach  var="i" items="${sessionScope.stateList}">
                            <option value="${i.id}">${i.name}</option>
                        </c:forEach>
                    </select>
                </label>
            </td>
        </tr>
        <tr></tr>

        <tr>
            <td><button type="submit" class="btn">Submit</button></td>&nbsp;&nbsp;
            <td><button type="reset" class="btn">Reset</button></td>
        </tr>
    </table>
</form>
</body>
</html>
