<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Show All Goods</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>id</th>>
        <th>name</th>
        <th>description</th>
        <th>price</th>

        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${goods}" var="goods">
        <tr>
            <td><c:out value="${goods.id}" /></td>
            <td><c:out value="${goods.name}" /></td>
            <td><c:out value="${goods.description}" /></td>
            <td><c:out value="${goods.price}" /></td>

            <td><a href="GoodServlet/update?id=<c:out value="${goods.id}"/>">Update</a></td>
            <td><a href="GoodServlet/delete?id=<c:out value="${goods.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="addGood.jsp">Add Good</a></p>
<p><a href="AdminServlet">All users Page</a></p>
</body>
</html>