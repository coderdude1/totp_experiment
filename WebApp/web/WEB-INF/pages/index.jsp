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
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>


    <%--<link href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">--%>
</head>
  <body>
     <div class="container">
        <h1>${title}</h1>
        <p><a href="/user/">User Management</a>&nbsp</p>
        <p><a href="/user/">Edit Your Preferences</a>&nbsp</p>
      </div>
  </body>
</html>
