<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Kickstarter</title>
</head>
<body>
    <c:forEach items="${requestScope.categories}" var="category" >
        <c:out value="${category.name}"/>
    </c:forEach>
</body>
</html>

<%--  <a href="/sample/projects?category=${category.id}"><c:out value="${category.name}"/></a>--%>
<%--<c:out value="${category.name}"/>--%>