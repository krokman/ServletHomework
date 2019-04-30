<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Show All Users</title>
</head>
<body>
<c:if test="${user!=null}">
    User Created with data :<p></p>
    Nickname: <p></p>
    <c:out value="${user.nickname}"/><p></p>
    Password: <p></p>
    <c:out value="${user.password}"/><p></p>
    Email: <p></p>
    <c:out value="${user.email}"/><p></p>
    Role: <p></p>
    <c:out value="${user.role}"/><p></p>
</c:if>>
<c:if test="${data=='wrong'}">
    Wrong nickname or password
</c:if>
</body>
</html>