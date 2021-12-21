<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="frm" uri="http://www.springframework.org/tags/form" %>

<tags:master pageTitle="QuickCart">
    <c:if test="${not empty successMessage}">
        <div class="container">
            <div class="panel panel-success">
                <div class="panel-heading">Success</div>
                <div class="panel-body">${successMessage}</div>
            </div>
        </div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="container">
            <div class="panel panel-danger">
                <div class="panel-heading">Success</div>
                <div class="panel-body">${errorMessage}</div>
            </div>
        </div>
    </c:if>
    <frm:form method="post" modelAttribute="quickCart" action="${pageContext.servletContext.contextPath}/quickCart">
        <table>
            <thead>
            <tr>
                <td>Model</td>
                <td>Quantity</td>
            </tr>
            </thead>
            <c:forEach var="i" begin="0" end="9">
                <tr>
                    <td>
                        <frm:input path="models[${i}]"/>
                        <p class="text-danger">
                            <frm:errors path="models[${i}]"/>
                        </p>
                    </td>
                    <td>
                        <frm:input path="quantities[${i}]"/>
                        <p class="text-danger">
                            <frm:errors path="quantities[${i}]"/>
                        </p>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <button type="submit">Add To Cart</button>
        </p>
    </frm:form>
</tags:master>