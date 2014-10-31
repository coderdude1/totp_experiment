<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Main Landing</title>

    <%-- This stuff should be pulled into  a template --%>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resources/css/style1.css" />--%>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>


    <%--<link href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">--%>
</head>
<body>
<div class="container">
    <%--<sec:authentication var="principal" property="principal" />--%>
    <h1>${title}</h1>
    <c:url value="/logout" var="logoutUrl"/>
    <form style="visibility: hidden" action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
    <h3>
        Welcome ${pageContext.request.userPrincipal.name}
    </h3>

    <p>Things you can do</p>
    <a href="javascript:performLogout()">Logout</a>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <p><a href="/useradmin/">User Management</a>&nbsp</p>
    </sec:authorize>
    <p><a href="/userprefs/">Edit Your Preferences</a>&nbsp</p>
</div>

<script>
    function performLogout() {
        <%--${"#logoutForm"}.submit(); for some reason jqueyr doesn't work, the $ gets stripped--%>
        document.getElementById("logoutForm").submit();
    }
</script>
</body>
</html>
