
<%@ page import="xmlclient.XmlExamples" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'xmlExamples.label', default: 'XmlExamples')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-xmlExamples" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<g:render template="/utils/menu"/>
		<div class="btn btn-default">
		<g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
		</div>
		<div id="list-xmlExamples" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					<g:sortableColumn property="name" title="${message(code: 'xmlExamples.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="content" title="${message(code: 'xmlExamples.content.label', default: 'Content')}" />
					
						
					</tr>
				</thead>
				<tbody>
				<g:each in="${xmlExamplesInstanceList}" status="i" var="xmlExamplesInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td><g:link action="show" id="${xmlExamplesInstance.id}">${fieldValue(bean: xmlExamplesInstance, field: "name")}</g:link></td>
					
						<td>
						
<g:textArea id="inputXML"  readonly="readonly" name="content" rows="5" cols="40" value="${xmlExamplesInstance?.content}" />
						</td>
					
						
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${xmlExamplesInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
