<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="frm" uri="http://www.springframework.org/tags/form" %>

<tags:master pageTitle="Login">
    <div class="container">
        <div class="row">
            <div class="col-4"></div>
            <div class="col-4 container-fluid">
                <frm:form method="post" modelAttribute="loginDto" action="${pageContext.servletContext.contextPath}/login">
                    <div class="row">
                        <div class="col-4">
                            Login:
                        </div>
                        <div class="col-8">
                            <frm:input path="login"/>
                            <p class="text-danger"><frm:errors path="login"/></p>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-4">
                            Password:
                        </div>
                        <div class="col-8">
                            <frm:input path="password" type="password"/>
                            <p class="text-danger"><frm:errors path="password"/></p>
                        </div>
                    </div>
                    <br>
                    <button class="btn btn-light float-right" type="submit">Login</button>
                </frm:form>
            </div>
            <div class="col-4"></div>
        </div>
    </div>
</tags:master>
