<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Page</title>
    </head>
    <body>
        <h1>客戶管理</h1>
        <a href="ListServlet">List All Customer</a><br><br>        
        <form action="AddServlet" method="post">
            ID: <input type="text" name="id"><br><br>
            姓名: <input type="text" name="username"><br><br>
            地址: <input type="text" name="address"><br><br>
            公司: <select name='company'>
                    <option value='UNKNOWN'> --Select-- </option>
                    <c:forEach var="comp" items="${companys}">
                        <option value="${comp.key}">${comp.value}</option>
                    </c:forEach>
                  </select> <br><br>
            <input type="submit" value="Add"><br>
        </form>
    </body>
</html>
