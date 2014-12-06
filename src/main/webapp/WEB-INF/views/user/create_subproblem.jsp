<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">

    <H2 style="margin-left: 5em"><spring:message code="label.create"/></H2>

    <form:form method="post" action="/user/task/${taskId}/add_subproblem"
               commandName="subproblemForm">

    <form:errors path="*" cssClass="errorblock" element="div"/>

    <div class="form-narrow" id="create_subproblem">
        <div class="form-group">
            <form:label path="title"><spring:message
                    code="label.title"/></form:label>
            <div class="col-lg-10">
                <form:input path="title" cssClass="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="content"><spring:message
                    code="label.content"/></form:label>
            <div class="col-lg-10">
                <div class="wmd-panel">
                    <div id="wmd-button-bar"></div>
                    <form:textarea id="wmd-input"
                                   path="content" cssClass="wmd-input"/>
                </div>
                <div id="wmd-preview" class="wmd-panel wmd-preview"></div>
            </div>
        </div>

        <input type="submit" class="btn btn-success"
               value="<spring:message code="label.add"/>"/>

        </form:form>
    </div>


</div>

<script type="text/javascript">
    (function () {
        var converter1 = Markdown.getSanitizingConverter();

        converter1.hooks.chain("preBlockGamut", function (text, rbg) {
            return text.replace(/^ {0,3}""" *\n((?:.*?\n)+?) {0,3}""" *$/gm, function (whole, inner) {
                return "<blockquote>" + rbg(inner) + "</blockquote>\n";
            });
        });

        var editor1 = new Markdown.Editor(converter1);

        editor1.run();

        var converter2 = new Markdown.Converter();

        converter2.hooks.chain("preConversion", function (text) {
            return text.replace(/\b(a\w*)/gi, "*$1*");
        });

        converter2.hooks.chain("plainLinkText", function (url) {
            return "This is a link to " + url.replace(/^https?:\/\//, "");
        });

        var help = function () {
            alert("Do you need help?");
        }
        var options = {
            helpButton: { handler: help },
            strings: { quoteexample: "whatever you're quoting, put it right here" }
        };
        var editor2 = new Markdown.Editor(converter2, "-second", options);

        editor2.run();
    })();
</script>