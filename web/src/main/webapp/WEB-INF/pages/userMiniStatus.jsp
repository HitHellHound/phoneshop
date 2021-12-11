<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${role.name() eq 'admin'}">
        <a href="<c:url value="/admin/orders"/>">Admin</a>
        <a href="<c:url value="/login/logout"/>">Logout</a>
    </c:when>
    <c:otherwise>
        <a href="<c:url value="/login"/>">Login</a>
    </c:otherwise>
</c:choose>