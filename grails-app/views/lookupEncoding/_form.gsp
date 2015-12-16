<div class="fieldcontain ${hasErrors(bean: lookupEncodingInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="lookupEncoding.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${lookupEncodingInstance?.name}"/>

</div>



