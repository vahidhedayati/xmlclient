<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'lookupTypes.label', default: 'XmlExamples')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-lookupTypes" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		
		<g:render template="/utils/menu"/>
		<div class="btn btn-default">
		<g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link>
		</div>
					
		<div class="btn btn-default">
		<g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
		</div>
		
		<div id="edit-lookupTypes" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${lookupTypesInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${lookupTypesInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:lookupTypesInstance, action:'update']" method="PUT" >
				<g:hiddenField name="version" value="${lookupTypesInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
