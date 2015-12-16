
<%@ page import="xmlclient.XmlUrls" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'xmlUrls.label', default: 'XmlUrls')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-xmlUrls" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<g:render template="/utils/menu"/>
		<div class="btn btn-default">
		<g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
		</div>
		<div id="list-xmlUrls" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'xmlUrls.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="url" title="${message(code: 'xmlUrls.url.label', default: 'Url')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${xmlUrlsInstanceList}" status="i" var="xmlUrlsInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${xmlUrlsInstance.id}">${fieldValue(bean: xmlUrlsInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: xmlUrlsInstance, field: "url")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${xmlUrlsInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
