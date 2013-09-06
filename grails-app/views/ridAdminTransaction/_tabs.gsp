<g:if test="${false}"><!--Dummy test for easy switching-->

    <style type="text/css">
    .nav-tabs .active a,
    .nav-tabs .active a:hover {
        font-size: 16px;
    }

    .nav-tabs  a,
    .nav-tabs  a:hover {
        font-size: 12px;
    }

    </style>
    <ul class="nav nav-tabs">
        <g:render
                template="/ridTransaction/tabLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridAdminLibraryUnit',
                        linkAction: 'list',
                        linkText: 'Library Unit',
                ]"/>

        <g:render
                template="/ridTransaction/tabLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridAdminDepartment',
                        linkAction: 'list',
                        linkText: 'Department',
                ]"/>
        <g:render
                template="/ridTransaction/tabLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridAdminSchool',
                        linkAction: 'list',
                        linkText: 'School',
                ]"/>
        <g:if test="${session.transType == "consultation"}">
            <tmpl:consTabs/>
        </g:if>
        <g:else>
            <tmpl:insTabs/>
        </g:else>
    </ul>
</g:if>
<g:else>
    <ul class="nav nav-tabs green">

        <style type="text/css">
        .green a,
        .green a:hover {
            color: green
        }

        .nav li.dropdown > .dropdown-toggle .caret {
            color: green;
            border-top-color: green;
            border-bottom-color: green;
        }

        </style>
        <li>
        <li class="dropdown">
            <a class="dropdown-toggle green" data-toggle="dropdown">
                <g:if test="${controllerName != "ridAdminTransaction"}">
                    ${controllerName.minus("ridAdmin")}
                </g:if>
                <g:else>
                    Edit/Add properties
                </g:else>
                <span class="caret"></span></a>

            <ul class="dropdown-menu">

                <g:render
                        template="/ridTransaction/tabLabel"
                        plugin="metridocRid"
                        model="[controllerName: controllerName,
                                actionName: actionName,
                                linkController: 'ridAdminLibraryUnit',
                                linkAction: 'list',
                                linkText: 'Library Unit',
                        ]"/>

                <g:render
                        template="/ridTransaction/tabLabel"
                        plugin="metridocRid"
                        model="[controllerName: controllerName,
                                actionName: actionName,
                                linkController: 'ridAdminDepartment',
                                linkAction: 'list',
                                linkText: 'Department',
                        ]"/>
                <g:render
                        template="/ridTransaction/tabLabel"
                        plugin="metridocRid"
                        model="[controllerName: controllerName,
                                actionName: actionName,
                                linkController: 'ridAdminSchool',
                                linkAction: 'list',
                                linkText: 'School',
                        ]"/>
                <g:if test="${session.transType == "consultation"}">
                    <g:render template="/ridAdminTransaction/consTabs" plugin="metridoc-rid"/>
                </g:if>
                <g:else>
                    <g:render template="/ridAdminTransaction/insTabs" plugin="metridoc-rid"/>
                </g:else>
        </li>

    </ul>

    </ul>

        </ul>
</g:else>
