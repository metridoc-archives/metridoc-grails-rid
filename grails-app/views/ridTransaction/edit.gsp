<%@ page import="metridoc.rid.RidTransaction" %>
<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}" />

<md:report>
        <div class="md-application-content">
            <g:render template="tabs" plugin="metridocRid"/>

            <div id="edit-ridTransaction" class="content scaffold-edit" role="main">
                <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                %{--<g:hasErrors bean="${ridTransactionInstance}">--}%
                %{--<ul class="errors" role="alert">--}%
                    %{--<g:eachError bean="${ridTransactionInstance}" var="error">--}%
                    %{--<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>--}%
                    %{--</g:eachError>--}%
                %{--</ul>--}%
                %{--</g:hasErrors>--}%
                <g:hasErrors bean="${ridTransactionInstance}">
                    <div class="errors">
                        <g:renderErrors bean="${ridTransactionInstance}" as="list" />
                    </div>
                </g:hasErrors>
                <g:form method="post">
                    <g:hiddenField name="id" value="${ridTransactionInstance?.id}" />
                    <g:hiddenField name="version" value="${ridTransactionInstance?.version}" />
                    <fieldset class="form">
                        <g:render template="form" plugin="metridocRid"/>
                    </fieldset>
                    <fieldset class="buttons">
                        <g:actionSubmit class="btn btn-success" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                        <g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                        formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </g:form>
            </div>
        </div>
</md:report>