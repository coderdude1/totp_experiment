<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Edit User Prefs</title>

    <%-- This stuff should be pulled into  a template --%>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resources/css/style1.css" />--%>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>


    <%--<link href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">--%>
</head>

<body>

    <div class="container">
        <div class="row">
            <h1>Edit User Preferences</h1>

        <%--<divclass="span8 offset2">--%>
            <form:form method="post" action="add" commandName="user" class="form-horizontal">
                <div class="col-xs-6">
                    <div class="control-group">
                        <form:label cssClass="control-label" path="firstName">First Name:</form:label>
                        <div class="controls">
                            <form:input path="firstName"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <form:label cssClass="control-label" path="lastName">Last Name:</form:label>
                        <div class="controls">
                            <form:input path="lastName"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <form:label cssClass="control-label" path="email">Email:</form:label>
                        <div class="controls">
                            <form:input path="email"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <p />
                        <div class="controls">
                            <input type="submit" value="Edit User" class="btn btn-success"/>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <c:choose>
                        <c:when test="${user.twoFactorAuthEnabled}">
                            <p>Shared Secret to manually enter into Google Authenticator: ${user.totpSecret}</p>
                            <p>or scan the QR code</p>
                            <img src="/authSecretQrImages?userId=${user.id}"/>
                            <p><a class="btn btn-success" href="/userprefs/generateTotpSecret">Regenerate Secret</a></p>


                        </c:when>
                        <c:otherwise>
                            <p><a class="btn btn-success" href="/userprefs/generateTotpSecret">Enable Two Factor Auth</a></p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </form:form>
    <%--</div>--%>
        </div>
</body>
</html>