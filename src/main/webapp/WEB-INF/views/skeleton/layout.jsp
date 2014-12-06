<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <c:set var="titleKey"><tiles:getAsString name="titleKey"/></c:set>
    <title><spring:message code="${titleKey}"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/core.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/task.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/pagedown/css/demo.css" type="text/css"/>


    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.autocomplete.min.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.cookies.2.2.0.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap-collapse.js"></script>
    <script src="${pageContext.request.contextPath}/resources/pagedown/Markdown.Converter.js"></script>
    <script src="${pageContext.request.contextPath}/resources/pagedown/Markdown.Sanitizer.js"></script>
    <script src="${pageContext.request.contextPath}/resources/pagedown/Markdown.Editor.js"></script>

</head>
<body>
<tiles:insertAttribute name="header"/>
<tiles:insertAttribute name="body"/>
<tiles:insertAttribute name="footer"/>

</body>
</html>