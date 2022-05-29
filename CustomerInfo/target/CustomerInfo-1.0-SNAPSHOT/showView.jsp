<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Info</title>
    </head>
    <body>
        <h3>客戶資訊</h3>
        <c:choose>
            <c:when test = "${not empty customer}">                
                <p>姓名: ${customer.username}</p>
                <p>地址: ${customer.address}</p>
                <p>公司:
                    <c:forEach var="company" items="${companys}">
                        <c:if test="${company.key eq customer.company}">
                            ${company.value}
                        </c:if>
                    </c:forEach>       
                </p>
                <c:if test = "${not empty price}">
                    <p>今日股價: ${price}</p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h3>查詢的客戶不存在</h3>
            </c:otherwise>
        </c:choose>
    </body>
</html>
