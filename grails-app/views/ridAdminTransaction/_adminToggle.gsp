<shiro:hasRole name="ROLE_ADMIN">

    <g:if test="${controllerName == "ridTransaction"}">
        <g:render
                template="/ridAdminTransaction/adminToggleLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridTransaction',
                        linkAction: 'switchMode',
                        linkText: 'Admin Controls']"/>
    </g:if>

    <g:else>
        <g:render
                template="/ridAdminTransaction/adminToggleLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridAdminTransaction',
                        linkAction: 'switchMode',
                        linkText: 'Transactions']"/>
    </g:else>
</shiro:hasRole>
