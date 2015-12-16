<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'lookupTypes.label', default: 'XmlExamples')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-lookupTypes" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<g:render template="/utils/menu"/>
		<div class="btn btn-default">
		<g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link>
		</div>
		<div class="btn btn-default">
		<g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
		</div>
		<div id="show-lookupTypes" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list lookupTypes">
			
		
			
				<g:if test="${lookupTypesInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="lookupTypes.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${lookupTypesInstance}" field="name"/></span>
					
				</li>
				</g:if>
					<g:if test="${lookupTypesInstance?.content}">
				<li class="fieldcontain">
					<span id="content-label" class="property-label"><g:message code="lookupTypes.content.label" default="formatting" /></span>
					
						<span class="property-value" aria-labelledby="formatting-label"><g:fieldValue bean="${lookupTypesInstance}" field="formatting"/></span>
					
				</li>
				</g:if>
			</ol>
			<g:form url="[resource:lookupTypesInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${lookupTypesInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
