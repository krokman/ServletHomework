<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Show All Users</title>
</head>
<body>
<c:if test="${user!=null}">
    Hello :<p></p>
    Nickname: <p></p>
    <c:out value="${user.nickname}"/>
    Your data :<p></p>
    Password: <p></p>
    <c:out value="${user.password}"/>
    Email: <p></p>
    <c:out value="${user.email}"/>
    Role: <p></p>
    <c:out value="${user.role}"/>
</c:if>>
<c:if test="${data=='wrong'}">
    Wrong nickname or password
</c:if>
</body>
</html>