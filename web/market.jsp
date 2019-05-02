<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Show All Goods</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Buy</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${goods}" var="goods">
        <tr>
            <td><c:out value="${goods.name}" /></td>
            <td><c:out value="${goods.description}" /></td>
            <td><c:out value="${goods.price}" /></td>
            <td><form method="post" action="Market?name=<c:out value="${goods.name}"/>">
            <input type="submit" value="Buy" />
        </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>