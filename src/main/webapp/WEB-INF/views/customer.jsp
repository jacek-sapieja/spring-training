<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Bank</title>
</head>
<body>
<h1><s:message code="customer.title"/></h1>
<sf:form modelAttribute="customerViewModel" method="post">
    <p><s:message code="customer.firstName"/></p>
    <p>
        <sf:input path="firstName"/>
        <sf:errors path="firstName"/>
    </p>
    <p><s:message code="customer.lastName"/></p>
    <p>
        <sf:input path="lastName"/>
        <sf:errors path="lastName"/>
    </p>
    <p><s:message code="customer.email"/></p>
    <p>
        <sf:input path="email"/>
        <sf:errors path="email"/>
    </p>
    <button type="submit"><s:message code="save"/></button>
</sf:form>
<a href="index.html"><s:message code="menu"/></a>
</body>
</html>
