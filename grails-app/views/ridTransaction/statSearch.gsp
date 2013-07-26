<%@ page import="metridoc.rid.RidDepartment; metridoc.rid.RidConsTransaction; metridoc.rid.RidInsTransaction" %>
<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidConsTransaction')}"/>

<md:report>
    <div class="md-application-content">

        <tmpl:toggle/>
        <tmpl:tabs/>
        <g:if test="${session.transType == "consultation"}">
            <div id="search-ridConsTransaction" class="content scaffold-search" role="main">
            <!--<h1><g:message code="RidTransaction Search"/></h1>-->
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>

                <g:form class="form-horizontal" action="statQuery" method="GET">
                    <fieldset class="form">
                        <div class="row-fluid">
                            <div class="span2 ">
                                <div class="control-group fieldcontain">
                                    <label class="control-label" for="dateOfConsultation">
                                        <g:message code="ridTransaction.dateOfConsultation.label"
                                                   default="Consultation Date Between"/>
                                    </label>

                                    <div class="controls">
                                        <input type="text" name="dateOfConsultation_start" style="width: 150px"
                                               id="dpd1"/>
                                        <span style="font-size: 12px; color: #666666">&nbsp;&nbsp;and&nbsp;&nbsp;</span>
                                        <input type="text" name="dateOfConsultation_end" style="width: 150px"
                                               id="dpd2"/>
                                    </div>
                                </div>
                            </div>

                            <div class="span2 offset2">
                                <div class="control-group fieldcontain">
                                    <label class="control-label" for="ridLibraryUnit">
                                        <g:message code="ridTransaction.ridLibraryUnit.label" default="Library Unit"/>
                                    </label>

                                    <div class="controls">
                                        <g:select id="ridLibraryUnitSearch" style="width:150px"
                                                  name="ridLibraryUnitSearch"
                                                  noSelection="${['0': 'All Units']}" optionKey="id" multiple="true"
                                                  value="0"
                                                  from="${metridoc.rid.RidLibraryUnit.list()}"/>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row-fluid">
                            <div class="span2 ">
                                <div class="control-group fieldcontain">
                                    <label class="control-label" for="staffPennkey">
                                        <g:message code="ridTransaction.staffPennkey.label" default="Staff Pennkey"/>
                                    </label>

                                    <div class="controls">
                                        <g:textField id="staffPennkey" style="width:150px" class="userInput"
                                                     name="staffPennkey"
                                                     value=""/>
                                    </div>
                                </div>

                            </div>

                        </div>

                        <div class="row-fluid">

                            <div class="span2 ">
                                <div class="control-group fieldcontain">
                                    <label class="control-label" for="ridSchool">
                                        <g:message code="ridTransaction.ridSchool.label" default="School"/>
                                    </label>

                                    <div class="controls">
                                        <g:select id="ridSchoolSearch" style="width:150px" name="ridSchoolSearch"
                                                  noSelection="${['0': 'All Schools']}" optionKey="id" multiple="true"
                                                  value="0"
                                                  from="${metridoc.rid.RidSchool.where { inForm == 1 }.list()}"/>
                                    </div>
                                </div>
                            </div>

                            <div class="span2 offset2">
                                <div class="control-group fieldcontain">
                                    <label class="control-label" for="ridDepartment">
                                        <g:message code="ridTransaction.ridDepartment.label" default="Department"/>
                                    </label>

                                    <div class="controls">
                                        <g:select id="ridDepartmentSearch" style="width:150px"
                                                  name="ridDepartmentSearch"
                                                  noSelection="${['0': 'All Departments']}" multiple="true" value="0"
                                                  optionKey="id"
                                        //optionValue="${{ it.name.empty ? 'NOT SPECIFIED' : it.name }}"
                                        //from="${metridoc.rid.RidDepartment.list()}"
                                                  from="${metridoc.rid.RidDepartment.where { name != "" }.list()}"/>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div>
                            Iterate results?
                            <g:checkBox name="iterateAll" value="${false}"/>
                        </div>

                    </fieldset>
                    <fieldset class="buttons">
                        <input id="resetButton" class="btn btn-danger" type="reset" value="Reset"/>
                        <g:submitButton name="search" class="btn btn-success"
                                        value="${message(code: 'default.button.search.label', default: 'Search')}"/>
                    </fieldset>
                </g:form>
            </div>
        </g:if>
        <g:else>
            Not Yet Implemented
        </g:else>
    </div>
</md:report>