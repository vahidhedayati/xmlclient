<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'lookupEncoding.label', default: 'XmlExamples')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-lookupEncoding" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<g:render template="/utils/menu"/>
		<div class="btn btn-default">
		<g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
		</div>
		<div id="list-lookupEncoding" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					<g:sortableColumn property="name" title="${message(code: 'lookupEncoding.name.label', default: 'Name')}" />
					
						
					</tr>
				</thead>
				<tbody>
				<g:each in="${lookupEncodingInstanceList}" status="i" var="lookupEncodingInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td><g:link action="show" id="${lookupEncodingInstance.id}">${fieldValue(bean: lookupEncodingInstance, field: "name")}</g:link></td>
					

						
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${lookupEncodingInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
