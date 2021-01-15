<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15/1/2021
  Time: 8:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="content-container">
    <form>
        <div id="search" align="center">
            <input type="hidden" name="action" value="search">
            <input type="text" name="search" placeholder="Enter Room">
            <input type="submit" value="search">
        </div>
    </form>
    <br>
    <table border="1" align="center">
        <thead>
        <tr>
            <td>Room</td>
            <td>Customer Name</td>
            <td>Customer CMND</td>
            <td>Check-in</td>
            <td>Check-out</td>
            <td colspan="2" style="text-align: center">Option</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listCustomer}" var="customer">
            <tr>
                <td>${customer.room}</td>
                <td>${customer.name}</td>
                <td>${customer.cmnd}</td>
                <td>${customer.checkin}</td>
                <td>${customer.checkout}</td>
                <td>
                    <a href="/customer?action=edit&id=${customer.room}"> Search
                    </a>
                </td>

            </tr>
        </c:forEach>
        <% request.removeAttribute("search"); %>
        </tbody>
    </table>
</div>
</body>
</html>
