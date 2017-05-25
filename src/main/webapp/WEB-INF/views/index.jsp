<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Bank</title>
</head>
<body>
<h1><s:message code="index.title"/></h1>
<ul>
    <li><a href="customer.html"><s:message code="register"/></a></li>
    <li><a href="create-account.html"><s:message code="index.createAccount"/></a></li>
    <li><a href="operation.html?type=deposit"><s:message code="deposit"/></a></li>
    <li><a href="operation.html?type=withdraw"><s:message code="withdraw"/></a></li>
    <li><a href="operation.html?type=transfer"><s:message code="transfer"/></a></li>
</ul>
</body>
</html>
