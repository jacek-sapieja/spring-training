<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Bank</title>
</head>
<body>
<h1><s:message code="operation.title"/>: <s:message code="${param.type}"/></h1>
<sf:form modelAttribute="operation" method="post">
    <p><s:message code="operation.accountNumber"/></p>
    <p><sf:input path="sourceAccountNumber"/></p>

    <c:if test="${param.type == 'transfer'}">
        <p><s:message code="operation.destinationAccountNumber"/></p>
        <p><sf:input path="destinationAccountNumber"/></p>
    </c:if>

    <p><s:message code="operation.funds"/></p>
    </p><sf:input path="funds"/></p>

    <sf:hidden path="type" value="#{param.type}"/>
    <button type="submit"><s:message code="execute"/></button>
</sf:form>
<a href="index.html"><s:message code="menu"/></a>
</body>
</html>
