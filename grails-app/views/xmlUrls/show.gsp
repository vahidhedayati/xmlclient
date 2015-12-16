
<%@ page import="xmlclient.XmlUrls" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'xmlUrls.label', default: 'XmlUrls')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-xmlUrls" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<g:render template="/utils/menu"/>
		<div class="btn btn-default">
		<g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link>
		</div>
		<div class="btn btn-default">
		<g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
		</div>
		<div id="show-xmlUrls" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list xmlUrls">
			
				<g:if test="${xmlUrlsInstance?.examples}">
				<li class="fieldcontain">
					<span id="examples-label" class="property-label"><g:message code="xmlUrls.examples.label" default="Examples" /></span>
					
						<g:each in="${xmlUrlsInstance.examples}" var="e">
						<span class="property-value" aria-labelledby="examples-label"><g:link controller="xmlExamples" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${xmlUrlsInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="xmlUrls.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${xmlUrlsInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${xmlUrlsInstance?.url}">
				<li class="fieldcontain">
					<span id="url-label" class="property-label"><g:message code="xmlUrls.url.label" default="Url" /></span>
					
						<span class="property-value" aria-labelledby="url-label"><g:fieldValue bean="${xmlUrlsInstance}" field="url"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:xmlUrlsInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${xmlUrlsInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
