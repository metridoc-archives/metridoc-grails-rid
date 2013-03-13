<%@ page import="metridoc.rid.RidUserGoal" %>

<div class="control-group fieldcontain required">
	<label class="control-label" for="name">
		<g:message code="ridUserGoal.name.label" default="Name" />
        <span class="required-indicator">*</span>
	</label>
    <div class="controls">
	    <g:textField class="userInput" name="name" required="" value="${ridUserGoalInstance?.name}"/>
    </div>
</div>

<div class="control-group fieldcontain">
    <label class="control-label" for="inForm">
        <g:message code="ridUserGoal.inForm.label" default="In Form" />
        <span class="required-indicator">*</span>
    </label>
    <div class="controls">
        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
        <g:select name="inForm" from="${choices}" required=""
                  value="${ridUserGoalInstance?.inForm}" keys="${ridUserGoalInstance.constraints.inForm.inList}" />
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: ridUserGoalInstance, field: 'ridReportType', 'error')} required">
    <label class="control-label" for="ridReportType">
        <g:message code="ridUserGoalInstance.ridReportType.label" default="Report Type" />
        <span class="required-indicator">*</span>
    </label>
    <div class="controls">
        <g:select id="ridReportType" style="width:120px" name="ridReportType.id" from="${metridoc.rid.RidReportType.list()}"
                  optionKey="id" required="" value="${ridUserGoalInstance?.ridReportType?.id}" class="many-to-one"/>
    </div>
</div>