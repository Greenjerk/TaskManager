<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="container">

    <div style="display: inline-block; margin-bottom: 20px">
        <div class="label label-primary pull-left" style="margin-top: 30px">
            <spring:message code="label.author"/>: ${task.author.username}
        </div>
        <H2 style="margin-left: 5em; display: block; float: left">
            <spring:message code="label.subproblems"/></H2>
    </div>

    <ul id="sortable">
        <c:forEach var="subproblem" items="${task.subproblems}">
            <li class="ui-state-default nav">
                <div class="container-fluid" style="text-align: center">
                    <div class="accordion" id="accordion${head.id}">
                        <div class="accordion-group">
                            <div class="accordion-heading">
                                <a class="accordion-toggle btn btn-group-lg badge panel-title"
                                   data-toggle="collapse"
                                   data-parent="#accordion${subproblem.id}" href="#collapse${subproblem.id}">
                                        ${subproblem.title}
                                </a>
                                <c:if test="${task.author.username == pageContext['request'].userPrincipal.name}">
                                    <a class="col-lg-12" href="/user/task/${task.id}/${subproblem.id}/delete_subproblem">
                                        <spring:message code="label.delete"/></a>
                                </c:if>
                            </div>
                            <div id="collapse${subproblem.id}" class="accordion-body collapse">
                                <div class="accordion-inner">
                                    <div id="content${subproblem.id}" class="wmd-panel wmd-preview well">${subproblem.content}</div>

                                    <script type="text/javascript">
                                        (function () {
                                            var converter = new Markdown.Converter();
                                            $("#content${subproblem.id}").html(converter.makeHtml($("#content${subproblem.id}").html()));
                                        })();
                                    </script>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>

    <c:if test="${task.author.username == pageContext['request'].userPrincipal.name}">
        <div class="label control-label">
            <a href="/user/task/${task.id}/add_subproblem">
                <spring:message code="label.add_subproblem"/>
            </a>
        </div>
    </c:if>

    <div style="margin-top: 100px">
        <c:forEach var="comment" items="${task.comments}">
            <div class="form-narrow">
                <c:if test="${comment.user.username == pageContext['request'].userPrincipal.name}">
                    <div class="badge alert-danger pull-right">
                        <form action="<c:url value="/general/task/${taskId}/remove_comment"/>"
                              method="POST">
                            <input type="hidden" name="commentId" value="${comment.id}">
                            <input type="submit"
                                   value="<spring:message code="label.delete"/>">
                        </form>
                    </div>
                </c:if>
                <ul class="media-list">
                    <li class="media">

                        <img class="pull-left avatar_profile"
                             src="<c:url value="/general/${comment.user.username}/avatar"/>"
                             width="70" height="70"/>

                        <div class="media-body" style="word-wrap: break-word">
                            <h4 class="media-heading"> ${comment.user.username}</h4>
                                ${comment.comment}
                        </div>


                    </li>
                </ul>
            </div>
        </c:forEach>
    </div>


    <security:authorize ifAnyGranted="ROLE_USER, ROLE_ADMIN">
        <form:form method="post" action="/general/task/${task.id}/add_comment"
                   commandName="commentForm">
            <form:errors cssClass="error" path="comment"/>
            <div class="form-narrow" style="margin-top: 70px">
                <ul class="media-list">
                    <li class="media">
                        <img width="100" height="100" class="pull-left avatar_profile"
                             src="<c:url value="/user/profile/avatar"/>"/>

                        <div class="media-body">
                            <h4 class="media-heading"><spring:message code="label.comment"/></h4>
                            <form:textarea path="comment" rows="4" cols="37"/>
                        </div>
                        <input type="submit"
                               value="<spring:message code="label.add"/>"
                               class="btn btn-block"
                               style="margin-top: 20px"/>
                    </li>
                </ul>
            </div>
        </form:form>
    </security:authorize>

</div>

<script>
    $(function () {
        var root = $('#sortable');
        $("#sortable").sortable({
            revert: true
        });
        $('> *', root).each(function (index) {
            this.id = 'item-' + index;
        });
        root.sortable({
            'update': function (event, ui) {
                var order = $(this).sortable('serialize');
                $.cookies.set('sortable', order);
            }
        });
        var c = $.cookies.get('sortable');
        if (c) {
            $.each(c.split('&'), function () {
                var id = this.replace('[]=', '-');
                $('#' + id).appendTo(root);
            });
        }
    });
</script>







