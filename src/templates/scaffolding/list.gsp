<% import grails.persistence.Event %>
<%=packageName%>
<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}"/>

<md:report>
    <div style="font-size: 12px">
        <g:render template="/ridTransactionAdmin/tabs" plugin="metridoc-rid"/>
    </div>

    <div class="md-application-content">

        <div id="list-${domainClass.propertyName}" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]"/></h1>
            <g:if test="\${flash.message}">
                <div class="message" role="status">\${flash.message}</div>
            </g:if>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <% excludedProps = Event.allEvents.toList() << 'id' << 'version'
                    allowedNames = domainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
                    props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && it.type != null && !Collection.isAssignableFrom(it.type) }
                    Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
                    props.eachWithIndex { p, i ->
                        if (i < 6) {
                            if (p.isAssociation()) { %>
                    <th><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}"/></th>
                    <% } else { %>
                    <g:sortableColumn property="${p.name}"
                                      title="\${message(code: '${domainClass.propertyName}.${p.name}.label', default: '${p.naturalName}')}"/>
                    <% }
                    }
                    } %>
                </tr>
                </thead>
                <tbody>
                <g:each in="\${${propertyName}List}" status="i" var="${propertyName}">
                    <tr class="\${(i % 2) == 0 ? 'even' : 'odd'}">
                        <% props.eachWithIndex { p, i ->
                            if (i == 0) { %>
                        <td><g:link action="show"
                                    id="\${${propertyName}.id}">\${fieldValue(bean: ${propertyName}, field: "${p.name}")}</g:link></td>
                        <% } else if (i < 6) {
                            if (p.type == Boolean || p.type == boolean) { %>
                        <td><g:formatBoolean boolean="\${${propertyName}.${p.name}}"/></td>
                        <% } else if (p.type == Date || p.type == java.sql.Date || p.type == java.sql.Time || p.type == Calendar) { %>
                        <td><g:formatDate date="\${${propertyName}.${p.name}}"/></td>
                        <% } else { %>
                        <td>\${fieldValue(bean: ${propertyName}, field: "${p.name}")}</td>
                        <% }
                        }
                        } %>
                    </tr>
                </g:each>
                </tbody>
            </table>
            <g:if test="\${${propertyName}Total > 10}">
                <div class="pagination">
                    <g:paginate total="\${${propertyName}Total}"/>
                </div>
            </g:if>
        </div>
    </div>
</md:report>
