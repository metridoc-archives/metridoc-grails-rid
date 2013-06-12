<g:if test="${session.display == "dropdown"}">
    <tmpl:toggleDD/>

</g:if>
<g:elseif test="${session.display == "tabs"}">
</g:elseif>
<g:else>
    <ul class="nav nav-pills">

        <g:render
                template="toggleLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridTransaction',
                        linkAction: 'consultation',
                        linkText: 'Consultation']"/>
        <g:render
                template="toggleLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridTransaction',
                        linkAction: 'instructional',
                        linkText: 'Instructional']"/>

        <tmpl:adminToggle/>

    </ul>
</g:else>