<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Bank</title>
</head>
<body>
<h1><s:message code="createAccountSuccess.title"/></h1>
<p>
    <s:message code="createAccountSuccess.message"/> ${account.number}
</p>
<a href="index.html"><s:message code="menu"/></a>
</body>
</html>
