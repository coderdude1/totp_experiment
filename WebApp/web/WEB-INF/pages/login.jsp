<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login Page</title>
    <%-- This stuff should be pulled into  a template --%>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resources/css/style1.css" />--%>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>

</head>
<body onload='document.loginForm.username.focus();'>

<%--<h1>Login Required</h1>--%>

<div id="login-box">

    <c:if test="${param.logout != null}}">
        <div class="error">You have been logged out</div>
    </c:if>
    <c:if test="${param.error != null}}">
        <div class="msg">Invalid Username/Password</div>
    </c:if>

<%-- http://bootsnipp.com/tags/3.2.0 --%>
    <div class="container" style="margin-top:30px">
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading"><h3 class="panel-title"><strong>Sign In </strong></h3></div>
                <div class="panel-body">
                    <form role="form" name="loginForm" action="/login" method="post">
                        <div class="form-group">
                            <label for="username">Username or Email</label>
                            <input type="text" class="form-control" name='username' id="username" placeholder="Enter username">
                        </div>
                        <div class="form-group">
                            <label for="password">Password <a href="/sessions/forgot_password">(forgot password)</a></label>
                            <input type="password" class="form-control" name='password' id="password" placeholder="Password">
                        </div>
                        <div class="checkbox">
                            <label>
                                <input name="remember" type="checkbox" value="Remember Me">Remember Me
                            </label>
                        </div>

                        <button type="submit" class="btn btn-sm btn-default">Sign in</button>
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}" />
                    </form>
                </div>
            </div>
        </div>
        <hr>
    </div>
</div>
</body>
</html>

