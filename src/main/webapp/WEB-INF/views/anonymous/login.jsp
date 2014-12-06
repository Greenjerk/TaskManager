<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">

    <form name='f' action="<c:url value='j_spring_security_check'/>"
          class="form-narrow form-horizontal" method='POST' Style="margin-top: 50px">

        <h2><spring:message code="label.login"/></h2>

        <c:if test="${not empty error}">
            <div class="errorblock">
                "<spring:message code="label.login_error"/>"<br/> <spring:message code="label.caused"/> :
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </c:if>

        <div class="form-group">
            <h5 style="font-weight: bold"><spring:message code="label.username"/></h5>

            <div class="col-lg-10">
                <input type='text' name='j_username' value='' class="form-control">
            </div>
        </div>

        <div class="form-group">
            <h5 style="font-weight: bold"><spring:message code="label.password"/></h5>

            <div class="col-lg-10">
                <input type='password' name='j_password' class="form-control"/>
            </div>
        </div>

        <input name="submit" type="submit" class="btn btn-default"
               value="<spring:message code="label.login"/>"/>

        <div class="form-group" style="margin-top: 2em">
            <input id="remember_me" type='checkbox'
                   name='_spring_security_remember_me' style="margin-left: 2em"/>
            <label for="remember_me" class="inline" style="margin-left: 5px; font-weight: normal">
                <spring:message code="label.remember"/>
            </label>
        </div>

    </form>
</div>
