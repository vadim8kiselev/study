<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<h3>All items:</h3>
<ol>
    <c:forEach items="${list}" var="entity">
        <li>
            <p>${entity.username} ${entity.name}</p>

            <form method="post">
                <input type="hidden" name="id" value="${entity.id}"/>
                <input type="submit" value="Remove"/>
            </form>
        </li>
    </c:forEach>
</ol>

<form method="post">
    <input type="text" name="username" placeholder="username" required="required"/>
    <input type="text" name="name" placeholder="name" required="required"/>
    <input type="submit" value="Add"/>
</form>

</body>
</html>