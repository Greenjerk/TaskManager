<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    $(document).ready(function () {
        // Handler for .ready() called.
        $('#changeLogo').click(function () {
            $('#fileInput').click();
        });
    });
</script>

<div class="container">

    <H2 style="margin-left: 5em"><spring:message code="label.edit"/></H2>

    <form:form method="post" action="/user/task/${taskId}/edit" commandName="taskForm"
               class="form-narrow form-horizontal" enctype="multipart/form-data">

        <form:errors path="*" cssClass="errorblock" element="div"/>

        <div class="form-group">
            <form:label path="title"><spring:message
                    code="label.task"/></form:label>
            <form:errors cssClass="error" path="title"/>
            <div class="col-lg-10">
                <form:input path="title" cssClass="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="description"><spring:message
                    code="label.description"/></form:label>
            <form:errors cssClass="error" path="description"/>
            <div class="col-lg-10">
                <form:textarea path="description" cssClass="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="users"><spring:message
                    code="label.users"/></form:label>
            <form:errors cssClass="error" path="users"/>
            <div class="col-lg-10">
                <form:input path="users" id="w-input-user" cssClass="form-control" value="${users}"/>
            </div>
        </div>

        <form:form modelAttribute="uploadForm" id="fileForm">
            <input type="submit" class="btn btn-success"
                   value="<spring:message code="label.save"/>"/>
        </form:form>


    </form:form>

</div>
