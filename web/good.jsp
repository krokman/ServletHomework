
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Add new good</title>
</head>
<body>

<form method="POST" action='/GoodServlet/update'>
    <input type="hidden" name="id"
            value="<c:out value="${good.id}" />"/> <br/>
    Name : <input
        type="text" name="name"
        value="<c:out value="${good.name}" />"/> <br/>
    Description: <input
        type="text" name="description"
        value="<c:out value="${good.description}" />"/> <br/>
    Price : <input type="text" name="price"
                   value="<c:out value="${good.price}" />"/> <br/>

    <input type="submit" value="Submit"/>

</form>
</body>
</html>