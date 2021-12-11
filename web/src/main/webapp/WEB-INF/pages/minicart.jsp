<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="<c:url value="/cart"/>">
    <button class="btn btn-light"> My Cart:
        <span id="cartTotalQuantity"><c:out value="${cart.totalQuantity}"/></span> items
        <span id="cartTotalCost"><c:out value="${cart.totalCost}"/></span>$
    </button>
</form>