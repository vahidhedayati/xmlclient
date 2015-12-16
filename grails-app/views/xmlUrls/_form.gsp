<%@ page import="xmlclient.XmlUrls" %>



<div class="fieldcontain ${hasErrors(bean: xmlUrlsInstance, field: 'examples', 'error')} ">
	<label for="examples">
		<g:message code="xmlUrls.examples.label" default="Examples" />
		
	</label>
	<g:select name="examples" from="${xmlclient.XmlExamples.list()}" multiple="multiple" optionKey="id" size="5" value="${xmlUrlsInstance?.examples*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: xmlUrlsInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="xmlUrls.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${xmlUrlsInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: xmlUrlsInstance, field: 'url', 'error')} required">
	<label for="url">
		<g:message code="xmlUrls.url.label" default="Url" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="url" required="" value="${xmlUrlsInstance?.url}"/>

</div>

