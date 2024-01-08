<%--
  Created by IntelliJ IDEA.
  User: dev1072
  Date: 08/01/24
  Time: 5:20 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="stateController" method="post">
    <input type="hidden" value="update" name="action">

    <table>
        <c:forEach  var="i" items="${sessionScope.stateList}">
        <tr>
            <td>State Name</td>
            <td><input type="text" name="stateName" placeholder="State Name" value="${i.name}"></td>
        </tr>
        <tr>

            <td>Country </td>
            <td>
                <label>
                    <select name="selectedCountry">

                        <option value="${i.country.country_id}" selected>${i.country.countryName}</option>
                        <c:forEach var="j" items="${sessionScope.countryList}">
                            <c:if test="${j.country_id ne i.country.country_id}">
                                <option value="${j.country_id}">${j.countryName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </label>
            </td>
        </tr>
        <tr><td><input type="hidden" value="${i.id}" name="id"></td></tr>
        </c:forEach>
        <tr>
            <td><button type="submit" class="btn">Submit</button></td>&nbsp;&nbsp;
            <td><button type="reset" class="btn">Reset</button></td>
        </tr>
    </table>
</form>
</body>
</html>
