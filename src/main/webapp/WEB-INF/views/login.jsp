<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Bank</title>
</head>
<body>
<h1><s:message code="login.title"/></h1>
<form method="post">
    <p>
        <s:message code="login.login"/>
    </p>
    <p>
        <input type="text" name="username"/>
    </p>
    <p>
        <s:message code="login.password"/>
    </p>
    <p>
        <input type="password" name="password"/>
    </p>
    <button type="submit">Ok</button>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<c:if test="${param.error != null}">
    <s:message code="login.authenticationFailed"/>
</c:if>
</body>
</html>
