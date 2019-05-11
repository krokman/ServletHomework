<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Show All Users</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>Nickname</th>
        <th>Password</th>
        <th>Email</th>
        <th>Role</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.nickname}" /></td>
            <td><c:out value="${user.password}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td><c:out value="${user.role}" /><td>
            <td><a href="AdminServlet/update?id=<c:out value="${user.id}"/>">Update</a></td>
            <td><a href="AdminServlet/delete?id=<c:out value="${user.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="registration.jsp">Add User</a></p>
<p><a href="GoodServlet">Goods Page</a></p>
</body>
</html>