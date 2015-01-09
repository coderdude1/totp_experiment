<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Two Factor Authentication</title>

    <%-- This stuff should be pulled into  a template --%>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resources/css/style1.css" />--%>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
</head>
<body onload='document.twoFactorAuthForm.authCode.focus();'>

  <div class="container" style="margin-top:30px">
    <%--<div class="col-md-6">--%>
      <div class="panel panel-default">
        <div class="panel-heading"><h3 class="panel-title"><strong>Two Factor Authentication </strong></h3></div>
        <div class="panel-body">
          <form role="form" name="twoFactorAuthForm" action="/twofactorauth/submitCode" method="post">
            <div class="form-group">
              <label for="authCode">Enter Auth Code from the authenticator</label>
              <input type="text" class="form-control" name='authCode' id="authCode" placeholder="Enter Auth Code">
              <br />
              <button type="submit" class="btn btn-sm btn-default">Submit</button>
              <input type="hidden" name="${_csrf.parameterName}"
                     value="${_csrf.token}" />
              <c:if test="${error != null}">
                <div class="has-error">${error}</div>
              </c:if>
            </div>
          </form>
        </div>
      <%--</div>--%>
  </div>

</body>
</html>
