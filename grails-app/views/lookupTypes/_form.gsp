
<div class="fieldcontain ${hasErrors(bean: lookupTypesInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="lookupTypes.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${lookupTypesInstance?.name}"/>

</div>


<div class="fieldcontain ${hasErrors(bean: lookupTypesInstance, field: 'formatting', 'error')} required">
	<label for="content">
		<g:message code="lookupTypes.formatting.label" default="Formatting" />
		<span class="required-indicator">*</span>
	</label>
<g:textField name="formatting" required="" value="${lookupTypesInstance?.formatting}"/>

</div>


