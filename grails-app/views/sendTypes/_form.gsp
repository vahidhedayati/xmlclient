
<div class="fieldcontain ${hasErrors(bean: sendTypesInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="sendTypes.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${sendTypesInstance?.name}"/>

</div>



