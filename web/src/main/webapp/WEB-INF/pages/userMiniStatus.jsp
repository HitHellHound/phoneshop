<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authorize access="hasRole('ADMIN')">
    <a href="<c:url value="/admin/orders"/>">Admin</a>
</security:authorize>
<security:authorize access="isAuthenticated()">
    <a href="<c:url value="/logout"/>">Logout</a>
</security:authorize>
<security:authorize access="!isAuthenticated()">
    <a href="<c:url value="/signIn"/>">Login</a>
</security:authorize>