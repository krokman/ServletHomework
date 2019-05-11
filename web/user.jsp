<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Add new user</title>
</head>
<body>

<form method="POST" action='/AdminServlet/update' >
    <input type="hidden" name="id"
            value="<c:out value="${user.id}" />" /> <br />
    Nickname : <input
        type="text" name="nickname"
        value="<c:out value="${user.nickname}" />" /> <br />
    Password: <input
        type="text" name="password"/> <br />
    Email : <input type="text" name="email"
                   value="<c:out value="${user.email}" />" /> <br />
    Role : <input type="text" name="role"
                  value="<c:out value="${user.role}" />" /> <br />
    <input type="submit" value="Submit" />

</form>
</body>
</html>