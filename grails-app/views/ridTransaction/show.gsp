
<%@ page import="metridoc.rid.RidTransaction" %>
<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}" />

<md:report>
        <r:external dir="css" file="ridtrans.css" plugin="metridoc-rid"/>
        <div class="md-application-content">
            <g:render template="/ridTransaction/tabs" plugin="metridocRid"/>

            <div id="show-ridTransaction" class="content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                <br/>

                <table border="0" style="font-size: 12px; border-top: 0px; width: 90%; table-layout: fixed; word-wrap: break-word" align="center">
                    <tr align="center">
                        <td colspan="2">
                            <span id="dateOfConsultation-label" class="property-label"><g:message code="ridTransaction.dateOfConsultation.label" default="Date Of Consultation" /></span>
                            :
                            <span class="property-value" aria-labelledby="dateOfConsultation-label"><g:formatDate date="${ridTransactionInstance?.dateOfConsultation}" /></span>
                        </td>
                        <td colspan="3">
                            <span id="staffPennkey-label" class="property-label"><g:message code="ridTransaction.staffPennkey.label" default="Staff Pennkey" /></span>
                            :
                            <span class="property-value" aria-labelledby="staffPennkey-label"><g:fieldValue bean="${ridTransactionInstance}" field="staffPennkey"/></span>
                        </td>
                    </tr>
                    <tr align="center">
                        <td>
                            <span id="modeOfConsutlation-label" class="property-label"><g:message code="ridTransaction.modeOfConsutlation.label" default="Mode Of Consutlation" /></span>
                            :<br/>
                            <span class="property-value" aria-labelledby="modeOfConsutlation-label"><g:link controller="ridModeOfConsutlationAdmin" action="show" id="${ridTransactionInstance?.modeOfConsutlation?.id}">${ridTransactionInstance?.modeOfConsutlation?.encodeAsHTML()}</g:link></span>
                        </td>
                        <td>
                            <span id="productConnected-label" class="property-label"><g:message code="ridTransaction.productConnected.label" default="Product Connected" /></span>
                            :<br/>
                            <span class="property-value" aria-labelledby="productConnected-label"><g:link controller="ridProductConnectedAdmin" action="show" id="${ridTransactionInstance?.productConnected?.id}">${ridTransactionInstance?.productConnected?.encodeAsHTML()}</g:link></span>
                        </td>
                        <td>
                            <span id="serviceProvided-label" class="property-label"><g:message code="ridTransaction.serviceProvided.label" default="Service Provided" /></span>
                            :<br/>
                            <span class="property-value" aria-labelledby="serviceProvided-label"><g:link controller="ridServiceProvidedAdmin" action="show" id="${ridTransactionInstance?.serviceProvided?.id}">${ridTransactionInstance?.serviceProvided?.encodeAsHTML()}</g:link></span>
                        </td>
                        <td>
                            <span id="prepTime-label" class="property-label"><g:message code="ridTransaction.prepTime.label" default="Prep Time" /></span>
                            :<br/>
                            <span class="property-value" aria-labelledby="prepTime-label"><g:fieldValue bean="${ridTransactionInstance}" field="prepTime"/></span>
                        </td>
                        <td>
                            <span id="eventLength-label" class="property-label"><g:message code="ridTransaction.eventLength.label" default="Event Length" /></span>
                            :<br/>
                            <span class="property-value" aria-labelledby="eventLength-label"><g:fieldValue bean="${ridTransactionInstance}" field="eventLength"/></span>
                        </td>
                    </tr>
                    <tr align="center">
                        <td>
                            <span id="customer-label" class="property-label"><g:message code="ridTransaction.customer.label" default="Customer" /></span>
                            :<br/>
                            <span class="property-value" aria-labelledby="customer-label"><g:link controller="ridCustomerAdmin" action="show" id="${ridTransactionInstance?.customer?.id}">${ridTransactionInstance?.customer?.encodeAsHTML()}</g:link></span>
                        </td>
                        <td>
                            <span id="entityAffiliation-label" class="property-label"><g:message code="ridTransaction.entityAffiliation.label" default="Entity Affiliation" /></span>
                            :<br/>
                            <span class="property-value" aria-labelledby="entityAffiliation-label"><g:link controller="ridEntityAffiliationAdmin" action="show" id="${ridTransactionInstance?.entityAffiliation?.id}">${ridTransactionInstance?.entityAffiliation?.encodeAsHTML()}</g:link></span>
                        </td>
                        <td>
                            <span id="patronEmail-label" class="property-label"><g:message code="ridTransaction.patronEmail.label" default="Patron Email" /></span>
                            :<br/>
                            <span class="property-value" aria-labelledby="patronEmail-label"><g:fieldValue bean="${ridTransactionInstance}" field="patronEmail"/></span>
                        </td>
                        <td>
                            <span id="interactTimes-label" class="property-label"><g:message code="ridTransaction.interactTimes.label" default="Interact Times" /></span>
                            :<br/>
                            <span class="property-value" aria-labelledby="interactTimes-label"><g:fieldValue bean="${ridTransactionInstance}" field="interactTimes"/></span>
                        </td>
                    </tr>
                    <tr align="center">
                        <td>
                            <span id="courseName-label" class="property-label"><g:message code="ridTransaction.courseName.label" default="Course Name" /></span>
                            :<br/>
                            <span class="property-value" aria-labelledby="courseName-label"><g:fieldValue bean="${ridTransactionInstance}" field="courseName"/></span>
                        </td>
                        <td>
                            <span id="departmentalAffilication-label" class="property-label"><g:message code="ridTransaction.departmentalAffilication.label" default="Departmental Affilication" /></span>
                            :<br/>
                            <span class="property-value" aria-labelledby="departmentalAffilication-label"><g:link controller="ridDepartmentalAffiliationAdmin" action="show" id="${ridTransactionInstance?.departmentalAffilication?.id}">${ridTransactionInstance?.departmentalAffilication?.encodeAsHTML()}</g:link></span>
                        </td>
                        <td>
                            <span id="courseNumber-label" class="property-label"><g:message code="ridTransaction.courseNumber.label" default="Course Number" /></span>
                            :<br/>
                            <span class="property-value" aria-labelledby="courseNumber-label"><g:fieldValue bean="${ridTransactionInstance}" field="courseNumber"/></span>
                        </td>
                        <td>
                            <span id="librarian-label" class="property-label"><g:message code="ridTransaction.librarian.label" default="Librarian" /></span>
                            :<br/>
                            <span class="property-value" aria-labelledby="librarian-label"><g:fieldValue bean="${ridTransactionInstance}" field="librarian"/></span>
                        </td>
                        <td>
                            <span id="facultySponsor-label" class="property-label"><g:message code="ridTransaction.facultySponsor.label" default="Faculty Sponsor" /></span>
                            :<br/>
                            <span class="property-value" aria-labelledby="facultySponsor-label"><g:fieldValue bean="${ridTransactionInstance}" field="facultySponsor"/></span>
                        </td>
                    </tr>
                    <tr align="center">
                        <td colspan="2">
                            <span id="customerQuestion-label" class="property-label"><g:message code="ridTransaction.customerQuestion.label" default="Customer Question" /></span>
                            :<br/>
                            <span class="property-value" aria-labelledby="customerQuestion-label"><g:fieldValue bean="${ridTransactionInstance}" field="customerQuestion"/></span>
                        </td>
                        <td>
                            <span id="notes-label" class="property-label"><g:message code="ridTransaction.notes.label" default="Notes" /></span>
                            :<br/>
                            <span class="property-value" aria-labelledby="notes-label"><g:fieldValue bean="${ridTransactionInstance}" field="notes"/></span>
                        </td>
                    </tr>


                    %{--<g:if test="${ridTransactionInstance?.followUpContact}">--}%
                    %{--<li class="fieldcontain">--}%
                        %{--<span id="followUpContact-label" class="property-label"><g:message code="ridTransaction.followUpContact.label" default="Follow Up Contact" /></span>--}%
                        %{----}%
                            %{--<span class="property-value" aria-labelledby="followUpContact-label"><g:fieldValue bean="${ridTransactionInstance}" field="followUpContact"/></span>--}%
                        %{----}%
                    %{--</li>--}%
                    %{--</g:if>--}%

                    %{--<g:if test="${ridTransactionInstance?.courseSponsor}">--}%
                        %{--<li class="fieldcontain">--}%
                            %{--<span id="courseSponsor-label" class="property-label"><g:message code="ridTransaction.courseSponsor.label" default="Course Sponsor" /></span>--}%
    %{----}%
                            %{--<span class="property-value" aria-labelledby="courseSponsor-label"><g:link controller="ridCourseSponsor" action="show" id="${ridTransactionInstance?.courseSponsor?.id}">${ridTransactionInstance?.courseSponsor?.encodeAsHTML()}</g:link></span>--}%
    %{----}%
                        %{--</li>--}%
                    %{--</g:if>--}%

                    <g:if test="${ridTransactionInstance?.otherEntityAffiliation}">
                        <li class="fieldcontain">
                            <span id="otherEntityAffiliation-label" class="property-label"><g:message code="ridTransaction.otherEntityAffiliation.label" default="Other Entity Affiliation" /></span>

                            <span class="property-value" aria-labelledby="otherEntityAffiliation-label"><g:fieldValue bean="${ridTransactionInstance}" field="otherEntityAffiliation"/></span>

                        </li>
                    </g:if>

                    <g:if test="${ridTransactionInstance?.otherCustomer}">
                    <li class="fieldcontain">
                        <span id="otherCustomer-label" class="property-label"><g:message code="ridTransaction.otherCustomer.label" default="Other Customer" /></span>
                        
                            <span class="property-value" aria-labelledby="otherCustomer-label"><g:fieldValue bean="${ridTransactionInstance}" field="otherCustomer"/></span>
                        
                    </li>
                    </g:if>

                    <g:if test="${ridTransactionInstance?.otherCourseSponsor}">
                    <li class="fieldcontain">
                        <span id="otherCourseSponsor-label" class="property-label"><g:message code="ridTransaction.otherCourseSponsor.label" default="Other Course Sponsor" /></span>
                        
                            <span class="property-value" aria-labelledby="otherCourseSponsor-label"><g:fieldValue bean="${ridTransactionInstance}" field="otherCourseSponsor"/></span>
                        
                    </li>
                    </g:if>

                    <g:if test="${ridTransactionInstance?.otherService}">
                    <li class="fieldcontain">
                        <span id="otherService-label" class="property-label"><g:message code="ridTransaction.otherService.label" default="Other Service" /></span>
                        
                            <span class="property-value" aria-labelledby="otherService-label"><g:fieldValue bean="${ridTransactionInstance}" field="otherService"/></span>
                        
                    </li>
                    </g:if>

                
                </table>
                <g:form>
                    <fieldset class="buttons">
                        <g:hiddenField name="id" value="${ridTransactionInstance?.id}" />
                        <g:actionSubmit class="btn btn-success" action="edit" id="${ridTransactionInstance?.id}" value="${message(code: 'default.button.edit.label', default: 'Edit')}" />
                        <g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </g:form>
            </div>
        </div>
</md:report>