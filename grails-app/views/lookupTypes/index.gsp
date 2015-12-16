<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'lookupTypes.label', default: 'XmlExamples')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-lookupTypes" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<g:render template="/utils/menu"/>
		<div class="btn btn-default">
		<g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
		</div>
		<div id="list-lookupTypes" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					<g:sortableColumn property="name" title="${message(code: 'lookupTypes.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="formatting" title="${message(code: 'lookupTypes.formatting.label', default: 'formatting')}" />
					
						
					</tr>
				</thead>
				<tbody>
				<g:each in="${lookupTypesInstanceList}" status="i" var="lookupTypesInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td><g:link action="show" id="${lookupTypesInstance.id}">${fieldValue(bean: lookupTypesInstance, field: "name")}</g:link></td>
					<td><g:link action="show" id="${lookupTypesInstance.id}">${fieldValue(bean: lookupTypesInstance, field: "formatting")}</g:link></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${lookupTypesInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
