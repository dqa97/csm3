<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/1/2021
  Time: 3:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<head>
    <title>Customer Management Application</title>
</head>
<style>
    h1 { color: #ffffff; font-family: 'Raleway',sans-serif; font-size: 62px; font-weight: 800; line-height: 72px; margin: 0 0 24px; text-align: center; text-transform: uppercase; }
    h2 { color: #ffffff; font-family: 'Raleway',sans-serif; font-size: 31px; font-weight: 800; line-height: 36px; margin: 0 0 24px; text-align: center; text-transform: uppercase; }
    input[type=text], input[type=password] {
        margin: 5px;
        padding: 0 10px;
        width: 200px;
        height: 34px;
        color: #404040;
        background: white;
        border: 1px solid;
        border-color: #c4c4c4 #d1d1d1 #d4d4d4;
        border-radius: 2px;
        outline: 5px solid #eff4f7;
        -moz-outline-radius: 3px;
        -webkit-box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.5);
        box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.5);
    }
</style>
<body style="background: url(https://paris-hotel-senlis.com/_novaimg/4534434-1401795_0_211_2240_1220_2200_1200.rc.jpg) no-repeat;background-size: 100%" >
<div style="text-align: center;">
    <h1 style="color: #f8f9fa">Customer Management</h1>
    <h2>
        <a href="/customer?action=create" class="btn badge-success">Add New Customer</a>
    </h2>
</div>
<div style="opacity: 0.5" align="center">
    <table border="1" cellpadding="5"  class="table table-striped table-dark">
        <caption><h2 style="color: #f8f9fa">List of Customer</h2></caption>
        <tr>
            <th>ID</th>
            <th>Room</th>
            <th>Name</th>
            <th>Cmnd</th>
            <th>Check-in</th>
            <th>Check-out</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="customer" items="${listCustomer}">
            <tr>
                <td><c:out value="${customer.id}"/></td>
                <td><c:out value="${customer.room}"/></td>
                <td><c:out value="${customer.name}"/></td>
                <td><c:out value="${customer.cmnd}"/></td>
                <td><c:out value="${customer.checkin}"/></td>
                <td><c:out value="${customer.checkout}"/></td>
                <td>
                    <a href="/customer?action=edit&id=${customer.id}" class="btn btn-warning">Edit</a>
                    <a href="/customer?action=delete&id=${customer.id}" class="btn badge-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
