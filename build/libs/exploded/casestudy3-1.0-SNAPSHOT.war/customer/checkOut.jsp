<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14/1/2021
  Time: 5:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<head>
    <title>Customer Check Out</title>
</head>
<style>
    h1 {
        color: #ffffff;
        font-family: 'Raleway', sans-serif;
        font-size: 62px;
        font-weight: 800;
        line-height: 72px;
        margin: 0 0 24px;
        text-align: center;
        text-transform: uppercase;
    }

    h2 {
        color: #ffffff;
        font-family: 'Raleway', sans-serif;
        font-size: 31px;
        font-weight: 800;
        line-height: 36px;
        margin: 0 0 24px;
        text-align: center;
        text-transform: uppercase;
    }

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
<body style="background: url(https://paris-hotel-senlis.com/_novaimg/4534434-1401795_0_211_2240_1220_2200_1200.rc.jpg) no-repeat;background-size: 100%">
<div style="text-align: center;">
    <h1 style="color: #f8f9fa">Customer Management</h1>
    <h2>
        <a href="/customer?action=checkOut" class="btn badge-success">List All Customer</a>
    </h2>
</div>
<div style="opacity: 0.5" align="center">
    <form action="/customer?action=checkOut" method="post">
        <table class="table table-striped table-dark" align="center">
            <c:if test="${customer != null}">
            <input type="hidden" name="id" value="<c:out value='${customer.id}'/>"/>
            </c:if>
            <tr>
                <th style="color: #f8f9fa">Check-out :</th>
                <td>
                    <input type="text" name="checkout" size="45" value="<c:out value='${customer.checkout}'/>"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input class="btn badge-success" type="submit" value="Save"/>
                </td>
            </tr>
    </form>
</div>
</body>
</html>
