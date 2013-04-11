<%@ page import="metridoc.rid.RidDepartment;metridoc.rid.RidSchool;metridoc.rid.RidRank;java.text.SimpleDateFormat;metridoc.rid.RidUserGoal;metridoc.rid.RidLibraryUnit;metridoc.rid.RidLibraryUnit;metridoc.rid.RidDepartment;metridoc.rid.RidCourseSponsor;metridoc.rid.RidTransaction" %>

<r:external dir="datepicker/css" file="datepicker.css" plugin="metridoc-rid"/>
<r:external dir="datepicker/js" file="bootstrap-datepicker.js" plugin="metridoc-rid"/>
<r:external dir="css" file="ridtrans.css" plugin="metridoc-rid"/>
<r:external dir="js" file="RidTransaction.js" plugin="metridoc-rid"/>

<div class="row-fluid">
    <div class="span3">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'ridLibraryUnit', 'error')}">
            <label for="ridLibraryUnit">
                <g:message code="ridTransaction.ridLibraryUnit.label" default="Library Unit"/>
                <span class="required-indicator">*</span>
            </label>
            <g:select id="ridLibraryUnit" style="width:160px" name="ridLibraryUnit.id"
                      from="${metridoc.rid.RidLibraryUnit.list()}"
                      optionKey="id" required="" value="${ridTransactionInstance?.ridLibraryUnit?.id}"
                      class="many-to-one"/>
        </div>
    </div>

    <div class="span3">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'dateOfConsultation', 'error')} required">
            <label for="dateOfConsultation">
                <g:message code="ridTransaction.dateOfConsultation.label" default="Date Of Consultation"/>
                <span class="required-indicator">*</span>
            </label>
            %{--<g:datePicker style="width: 150px" name="dateOfConsultation" precision="day"  --}%
            %{--value="${ridTransactionInstance?.dateOfConsultation}"  />--}%
            <% def dateString = ridTransactionInstance?.dateOfConsultation ? new SimpleDateFormat("MM/dd/yyyy").format(ridTransactionInstance?.dateOfConsultation) : ""; %>
            <input type="text" name="dateOfConsultation" style="width: 150px"
                   value="${dateString}" id="dp1" required=""/>
        </div>
    </div>

    <div class="span3">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'staffPennkey', 'error')} ">
            <label for="staffPennkey">
                <g:message code="ridTransaction.staffPennkey.label" default="Staff Pennkey"/>
                <span class="required-indicator">*</span>
            </label>
            <g:textField style="width:150px" class="userInput" name="staffPennkey" maxlength="100"
                         required="" value="${ridTransactionInstance?.staffPennkey}"/>
        </div>
    </div>
</div>


<div class="row-fluid">
    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'userName', 'error')} ">
            <label for="userName">
                <g:message code="ridTransaction.user.label" default="User Name"/>
            </label>
            <g:textField class="userInput" name="userName" style="width: 120px" maxlength="100"
                         value="${ridTransactionInstance?.userName}"/>
        </div>
    </div>

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'rank', 'error')} required">
            <label for="rank">
                <g:message code="ridTransaction.rank.label" default="Rank"/>
                <span class="required-indicator">*</span>
            </label>
            <%
                rankList = RidRank.findAllByInForm(1)
                if (ridTransactionInstance?.rank?.inForm == 0)
                    rankList.add(0, RidRank.findById(
                            ridTransactionInstance?.rank?.id))
                rankList = rankList.sort{ it.name }
                rankList.addAll(RidRank.findAllByInForm(2))
            %>
            <select style="width:120px" id="rank" name="rank.id" required="" class="many-to-one">
                <g:each in="${rankList}">
                    <option value="${it.id}" inForm="${it.inForm}"
                            <g:if test="${ridTransactionInstance?.rank?.id == it.id}">selected=""</g:if>>
                        ${it.name}
                    </option>
                </g:each>
            </select>
        </div>

        <div id="otherRankDiv" style="display:none"
             class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'otherRank', 'error')} ">
            <label for="otherRank">
                <g:message code="ridTransaction.otherRank.label" default="Other Rank"/>
            </label>
            <g:textField class="userInput" name="otherRank" style="width:120px" maxlength="50"
                         value="${ridTransactionInstance?.otherRank}"/>
        </div>
    </div>

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'school', 'error')} required">
            <label for="school">
                <g:message code="ridTransaction.school.label" default="School"/>
                <span class="required-indicator">*</span>
            </label>
            <% schoolList = RidSchool.findAllByInForm(1) %>
            <% if (ridTransactionInstance?.school?.inForm == 0)
                schoolList.add(0, RidSchool.findById(
                        ridTransactionInstance?.school?.id))
            schoolList = schoolList.sort{ it.name }
            %>
            <% schoolList.addAll(RidSchool.findAllByInForm(2)) %>
            <select style="width:120px" id="school" name="school.id" required="" class="many-to-one">
                <g:each in="${schoolList}">
                    <option value="${it.id}" inForm="${it.inForm}"
                            <g:if test="${ridTransactionInstance?.school?.id == it.id}">selected=""</g:if>>
                        ${it.name}
                    </option>
                </g:each>
            </select>
        </div>

        <div id="otherSchoolDiv" style="display:none;"
             class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'otherSchool', 'error')} ">
            <label for="otherSchool">
                <g:message code="ridTransaction.otherSchool.label" default="Other School"/>
            </label>
            <g:textField class="userInput" style="width:120px" name="otherSchool" maxlength="50"
                         value="${ridTransactionInstance?.otherSchool}"/>
        </div>
    </div>
    %{--<div class="span2">--}%
    %{--<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'patronEmail', 'error')} ">--}%
    %{--<label for="patronEmail">--}%
    %{--<g:message code="ridTransaction.patronEmail.label" default="Patron Email"/>--}%
    %{--</label>--}%
    %{--<g:field style="width:120px" class="userInput" type="email" name="patronEmail" maxlength="100" --}%
    %{--value="${ridTransactionInstance?.patronEmail}"/>--}%
    %{--</div>--}%
    %{--</div>--}%
    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'interactTimes', 'error')} required">
            <label for="interactTimes">
                <g:message code="ridTransaction.interactTimes.label" default="Interact Times"/>
                <span class="required-indicator">*</span>
            </label>
            <g:field style="width:120px" class="userInput" name="interactTimes" type="number" max="50"
                     value="${ridTransactionInstance.interactTimes}" required=""/>
        </div>
    </div>
</div>

<div class="row-fluid">
    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'modeOfConsultation', 'error')} required">
            <label for="modeOfConsultation">
                <g:message code="ridTransaction.modeOfConsultation.label" default="Mode Of Consultation"/>
                <span class="required-indicator">*</span>
            </label>

            <div id="currentModeOfConsultation"
                 style="display: none;">${ridTransactionInstance?.modeOfConsultation?.id}</div>
            <%
                modeList = metridoc.rid.RidModeOfConsultation.findAllByInFormAndRidLibraryUnit(1,
                        ridTransactionInstance?.ridLibraryUnit ?: RidLibraryUnit.get(1))
                if (ridTransactionInstance?.modeOfConsultation?.inForm == 0)
                    modeList.add(0, metridoc.rid.RidModeOfConsultation.findById(
                            ridTransactionInstance?.modeOfConsultation?.id))
                modeList = modeList.sort{ it.name }
                modeList.addAll(metridoc.rid.RidModeOfConsultation.findAllByInFormAndRidLibraryUnit(2,
                        ridTransactionInstance?.ridLibraryUnit ?: RidLibraryUnit.get(1)))
            %>
            <select style="width:120px" id="modeOfConsultation" name="modeOfConsultation.id" required=""
                    class="many-to-one">
                <g:each in="${modeList}">
                    <option value="${it.id}" inForm="${it.inForm}"
                            <g:if test="${ridTransactionInstance?.modeOfConsultation?.id == it.id}">selected=""</g:if>>
                        ${it.name}
                    </option>
                </g:each>
            </select>
            %{--<g:select style="width:120px" id="modeOfConsultation" name="modeOfConsultation.id" from="${modeList}"--}%
            %{--optionKey="id" required="" value="${ridTransactionInstance?.modeOfConsultation?.id}" class="many-to-one"/>--}%
        </div>

        <div id="otherModeOfConsultationDiv" style="display:none;"
             class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'otherModeOfConsultation', 'error')} ">
            <label for="otherModeOfConsultation">
                <g:message code="ridTransaction.otherModeOfConsultation.label" default="Other Consult-Mode"/>
            </label>
            <g:textField class="userInput" name="otherModeOfConsultation" style="width:120px"
                         maxlength="100" value="${ridTransactionInstance?.otherModeOfConsultation}"/>
        </div>
    </div>

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'serviceProvided', 'error')} required">
            <label for="serviceProvided">
                <g:message code="ridTransaction.serviceProvided.label" default="Service Provided"/>
                <span class="required-indicator">*</span>
            </label>

            <div id="currentServiceProvided" style="display: none;">${ridTransactionInstance?.serviceProvided?.id}</div>
            <%
                serviceList = metridoc.rid.RidServiceProvided.findAllByInFormAndRidLibraryUnit(1,
                        ridTransactionInstance?.ridLibraryUnit ?: RidLibraryUnit.get(1))
                if (ridTransactionInstance?.serviceProvided?.inForm == 0)
                    serviceList.add(0, metridoc.rid.RidServiceProvided.findById(
                            ridTransactionInstance?.serviceProvided?.id))
                serviceList = serviceList.sort{ it.name }
                serviceList.addAll(metridoc.rid.RidServiceProvided.findAllByInFormAndRidLibraryUnit(2,
                        ridTransactionInstance?.ridLibraryUnit ?: RidLibraryUnit.get(1)))
            %>
            <select style="width:120px" id="serviceProvided" name="serviceProvided.id" required="" class="many-to-one">
                <g:each in="${serviceList}">
                    <option value="${it.id}" inForm="${it.inForm}"
                            <g:if test="${ridTransactionInstance?.serviceProvided?.id == it.id}">selected=""</g:if>>
                        ${it.name}
                    </option>
                </g:each>
            </select>
        </div>

        <div id="otherServiceDiv" style="display:none;"
             class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'otherService', 'error')} ">
            <label for="otherService">
                <g:message code="ridTransaction.otherService.label" default="Other Service"/>
            </label>
            <g:textField class="userInput" name="otherService" style="width:120px"
                         maxlength="100" value="${ridTransactionInstance?.otherService}"/>
        </div>
    </div>

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'userGoal', 'error')}">
            <label for="userGoal">
                <g:message code="ridTransaction.userGoal.label" default="User Goal"/>
            </label>
            <%
                goalList = metridoc.rid.RidUserGoal.findAllByInFormAndRidLibraryUnit(1,
                        ridTransactionInstance?.ridLibraryUnit ?: RidLibraryUnit.get(1))
                if (ridTransactionInstance?.userGoal?.inForm == 0)
                    goalList.add(0, metridoc.rid.RidUserGoal.findById(
                            ridTransactionInstance?.userGoal?.id))
                goalList = goalList.sort{ it.name }
                goalList.addAll(metridoc.rid.RidUserGoal.findAllByInFormAndRidLibraryUnit(2,
                        ridTransactionInstance?.ridLibraryUnit ?: RidLibraryUnit.get(1)))
            %>
            <div id="currentUserGoal" style="display: none;">${ridTransactionInstance?.userGoal?.id}</div>
            <select <g:if test="${goalList.size() == 0}">disabled=""</g:if>
                    style="width:120px" id="userGoal" name="userGoal.id" class="many-to-one">
                <g:each in="${goalList}">
                    <option value="${it.id}" inForm="${it.inForm}"
                            <g:if test="${ridTransactionInstance?.userGoal?.id == it.id}">selected=""</g:if>>
                        ${it.name}
                    </option>
                </g:each>
            </select>
        </div>

        <div id="otherUserGoalDiv" style="display:none;"
             class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'otherUserGoal', 'error')} ">
            <label for="otherUserGoal">
                <g:message code="ridTransaction.otherUserGoal.label" default="Other User Goal"/>
            </label>
            <g:textField class="userInput" name="otherUserGoal" style="width:120px"
                         maxlength="100" value="${ridTransactionInstance?.otherUserGoal}"/>
        </div>
    </div>

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'prepTime', 'error')} required">
            <label for="prepTime">
                <g:message code="ridTransaction.prepTime.label" default="Prep Time (minutes)"/>
                <span class="required-indicator">*</span>
            </label>
            <g:field class="userInput" style="width:120px"
                     name="prepTime" type="number" value="${ridTransactionInstance.prepTime}" required=""/>
        </div>
    </div>

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'eventLength', 'error')} required">
            <label for="eventLength">
                <g:message code="ridTransaction.eventLength.label" default="Event Length (minutes)"/>
                <span class="required-indicator">*</span>
            </label>
            <g:field style="width:120px" class="userInput" name="eventLength" type="number"
                     value="${ridTransactionInstance.eventLength}" required=""/>
        </div>
    </div>
</div>

<div class="row-fluid">
    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'courseName', 'error')} ">
            <label for="courseName">
                <g:message code="ridTransaction.courseName.label" default="Course Name"/>
            </label>
            <g:textField class="userInput" name="courseName" style="width: 120px" maxlength="100"
                         value="${ridTransactionInstance?.courseName}"/>
        </div>
    </div>

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'courseSponsor', 'error')} required">
            <label for="courseSponsor">
                <g:message code="ridTransaction.courseSponsor.label" default="Course Sponsor"/>
                <span class="required-indicator">*</span>
            </label>
            <% courseSponsorList = RidCourseSponsor.findAllByInForm(1) %>
            <% if (ridTransactionInstance?.courseSponsor?.inForm == 0)
                courseSponsorList.add(0, RidCourseSponsor.findById(
                        ridTransactionInstance?.courseSponsorn?.id))
            courseSponsorList = courseSponsorList.sort{ it.name }
            %>
            <% courseSponsorList.addAll(RidCourseSponsor.findAllByInForm(2)) %>
            <select style="width:120px" id="courseSponsor" name="courseSponsor.id" required="" class="many-to-one">
                <g:each in="${schoolList}">
                    <option value="${it.id}" inForm="${it.inForm}"
                            <g:if test="${ridTransactionInstance?.courseSponsor?.id == it.id}">selected=""</g:if>>
                        ${it.name}
                    </option>
                </g:each>
            </select>
        </div>

        <div id="otherCourseSponsorDiv" style="display:none;"
             class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'otherCourseSponsor', 'error')} ">
            <label for="otherCourseSponsor">
                <g:message code="ridTransaction.otherCourseSponsor.label" default="Other Course Sponsor"/>
            </label>
            <g:textField class="userInput" name="otherCourseSponsor" style="width: 120px" maxlength="50"
                         value="${ridTransactionInstance?.otherCourseSponsor}"/>
        </div>
    </div>

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bea: ridTransactionInstance, field: 'department', 'error')}">
            <label for="department">
                <g:message code="ridTransaction.department.label" default="Department"/>
            </label>
            <g:select style="width:120px" id="department" name="department.id"
                      from="${RidDepartment.list().sort{it.name}}" optionKey="id"
                      value="${ridTransactionInstance?.department?.id}" class="many-to-one"/>
        </div>
    </div>

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'courseNumber', 'error')} ">
            <label for="courseNumber">
                <g:message code="ridTransaction.courseNumber.label" default="Course Number"/>
            </label>
            <g:textField class="userInput" name="courseNumber" style="width: 120px" maxlength="100"
                         value="${ridTransactionInstance?.courseNumber}"/>
        </div>
    </div>
    %{--<div class="span2">--}%
    %{--<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'librarian', 'error')} ">--}%
    %{--<label for="librarian">--}%
    %{--<g:message code="ridTransaction.librarian.label" default="Librarian"/>--}%
    %{--</label>--}%
    %{--<g:textField class="userInput" name="librarian" style="width: 120px" maxlength="100" --}%
    %{--value="${ridTransactionInstance?.librarian}"/>--}%
    %{--</div>--}%
    %{--</div>--}%
    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'facultySponsor', 'error')} ">
            <label for="facultySponsor">
                <g:message code="ridTransaction.facultySponsor.label" default="Faculty Sponsor"/>
            </label>
            <g:textField class="userInput" name="facultySponsor" style="width: 120px" maxlength="300"
                         value="${ridTransactionInstance?.facultySponsor}"/>
        </div>
    </div>
    %{--<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'facultySponsor', 'error')} ">--}%
    %{--<label for="facultySponsor">--}%
    %{--<g:message code="ridTransaction.facultySponsor.label" default="Faculty Sponsor"/>--}%

    %{--</label>--}%
    %{--<g:textArea class="userInput" name="facultySponsor" cols="40" rows="5" maxlength="300"--}%
    %{--value="${ridTransactionInstance?.facultySponsor}"/>--}%
    %{--</div>--}%
</div>

<div class="row-fluid">
    <div class="span4">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'userQuestion', 'error')} ">
            <label for="userQuestion">
                <g:message code="ridTransaction.userQuestion.label" default="User Question"/>
            </label>
            <br/>
            <g:textArea class="userInput" name="userQuestion" cols="40" rows="5" maxlength="500"
                        value="${ridTransactionInstance?.userQuestion}"/>
        </div>
    </div>

    <div class="span4">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'notes', 'error')} ">
            <label for="notes">
                <g:message code="ridTransaction.notes.label" default="Notes"/>
            </label>
            <br/>
            <g:textArea class="userInput" name="notes" cols="40" rows="5" maxlength="500"
                        value="${ridTransactionInstance?.notes}"/>
        </div>
    </div>
</div>



%{--<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'followUpContact', 'error')} ">--}%
%{--<label for="followUpContact">--}%
%{--<g:message code="ridTransaction.followUpContact.label" default="Follow Up Contact"/>--}%

%{--</label>--}%
%{--<g:textField class="userInput" name="followUpContact" maxlength="50" value="${ridTransactionInstance?.followUpContact}"/>--}%
%{--</div>--}%


%{--<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'facultySponsor', 'error')} ">--}%
%{--<label for="facultySponsor">--}%
%{--<g:message code="ridTransaction.facultySponsor.label" default="Faculty Sponsor"/>--}%

%{--</label>--}%
%{--<g:textArea class="userInput" name="facultySponsor" cols="40" rows="5" maxlength="300"--}%
%{--value="${ridTransactionInstance?.facultySponsor}"/>--}%
%{--</div>--}%