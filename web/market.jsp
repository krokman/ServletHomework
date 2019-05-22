<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Show All Goods</title>
</head>
<body>

<table border=1>
    <H1>Basket of Goods</H1>
    <thead>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${baskets}" var="basket">
        <tr>
            <td><c:out value="${basket.name}" /></td>
            <td><c:out value="${basket.description}" /></td>
            <td><c:out value="${basket.price}" /></td>
        </tr>
    </c:forEach>
    </tbody>
    <td><form method="post" action="Market?name=Buy">
        <input type="submit" value="Buy Goods" />
    </form>
    </td>

<table border=1>
    <H1>Market</H1>
    <thead>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Buy</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${goods}" var="good">
        <tr>
            <td><c:out value="${good.name}" /></td>
            <td><c:out value="${good.description}" /></td>
            <td><c:out value="${good.price}" /></td>
            <td><form method="post" action="Basket?name=<c:out value="${good.name}"/>&description=
                <c:out value="${good.description}"/>&price=<c:out value="${good.price}"/>">
            <input type="submit" value="Add to Basket" />
        </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>