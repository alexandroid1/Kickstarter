<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Kickstarter</title>
</head>
<body>
    <c:forEach var="i" begin="1" end="5">
        Item <c:out value="${i}"/><p>
    </c:forEach>
</body>
</html>
