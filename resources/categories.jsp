<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Kickstarter</title>
</head>
<body>
    <c:forEach var="category" items="${requestScope.categories}" >
        <c:out value="${category.name}"/><p>
    </c:forEach>

</body>
</html>
