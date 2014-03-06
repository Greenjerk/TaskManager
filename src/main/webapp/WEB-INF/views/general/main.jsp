<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">

    <div class="list-inline">
        <li><a class="btn btn-primary btn-sm"
               style="margin-bottom: 10px"
               href="/user/task/new"><spring:message code="label.create"/></a></li>
        <li><H2 style="margin-left: 3em"><spring:message code="label.alltasks"/></H2></li>
    </div>
    <br/>

    <c:forEach var="task" items="${tasks}">

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
                <br/>

            </div>
        </div>

    </c:forEach>

</div>

<script>
    $(document).ready(function(){
        $(document).click(function(){
            var scrt = $(document).scrollTop();
            $.cookies.set("scroll", scrt);
        });
    });

    $(document).ready(function(){
        if($.cookies.get("scroll")) {
            var scrt = $.cookies.get("scroll");
            $(document).scrollTop(scrt);
        }
    });
</script>
