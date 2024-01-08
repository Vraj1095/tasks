<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 05/01/24
  Time: 12:12 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="stateController" method="post">
    <input type="hidden" value="insert" name="action">
    <table>
        <tr>
            <td>Enter State Name</td>
            <td><input type="text" name="stateName" placeholder="State Name"></td>
        </tr>
        <tr>

            <td>Country </td>
            <td>
                <label>
                    <select name="selectedCountry">

                        <c:forEach  var="i" items="${sessionScope.countryList}">
                            <option value="${i.country_id}">${i.countryName}</option>
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
