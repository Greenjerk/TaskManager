<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="container">
    <c:if test="${not empty taskList}">
        <H2 style="margin-left: 5em"><spring:message code="label.found"/></H2>
    </c:if>
    <c:if test="${empty taskList}">
        <H2 style="margin-left: 5em"><spring:message code="label.empty"/></H2>
    </c:if>

    <c:forEach var="task" items="${taskList}">

        <div class="pull-right" style="margin-right: 3em">
            <div>
                <small><strong><spring:message code="label.author"/>: ${task.author.username}</strong></small>
            </div>
            <div class="pull-left">
                <img class="avatar_profile"
                     src="<c:url value="/general/${task.author.username}/avatar"/>"
                     width="105" height="105"/>
            </div>

        </div>

        <div class="form-narrow">
            <c:if test="${task.author.username == pageContext['request'].userPrincipal.name}">
                <div class="task panel">
                    <a href="/user/task/${task.id}/edit" class="btn btn-primary">
                        <spring:message code="label.edit"/></a>
                    <a href="/user/task/${task.id}/delete" class="btn btn-danger">
                        <spring:message code="label.delete"/></a>
                </div>
            </c:if>
            <a href="/general/task/${task.id}" class="btn-group">
                <div id="task_info">
                    <h4>${task.title}</h4>
                    <p>${task.description}</p>
                </div>
            </a>

            <div>
                <strong><spring:message code="label.subscribers"/>:</strong>
                <c:forEach var="subscriber" items="${task.subscribers}">
                    <small>${subscriber.username}</small>
                </c:forEach>

            </div>
        </div>

    </c:forEach>
</div>