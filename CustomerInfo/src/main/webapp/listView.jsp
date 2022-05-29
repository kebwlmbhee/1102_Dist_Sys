<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.example.customerinfo.entity.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer List</title>
    </head>
    <body>
        <h3>客戶列表</h3>
        <c:choose>
        <c:when test = "${not empty customers}">
        <table border="1">
            <tr>
                <th>姓名</th>
                <th>地址</th>
                <th>公司</th>
            </tr>
            <c:forEach var="customer" items="${customers}">                
            <tr>
                <td><a href='showServlet?id=${customer.id}'>${customer.username}</a></td> 
                <td>${customer.address}</td>
                <td>
                    <c:forEach var="company" items="${companys}">
                        <c:if test="${company.key eq customer.company}">
                            ${company.value}
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
            </c:forEach>
        </table>
        </c:when>
        <c:otherwise>
            <h3>無客戶存在</h3>
        </c:otherwise>
        </c:choose>
    </body>
</html>
