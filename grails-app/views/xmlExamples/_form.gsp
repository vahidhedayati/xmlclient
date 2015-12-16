<%@ page import="xmlclient.XmlExamples" %>

<div>
	<label for="xmlurl">
		<g:message code="xmlExamples.xmlurl.label" default="Bind to XML URL" />
	
	</label>
	<g:select name="xmlUrls" id="xmlUrl" from="${xmlclient.XmlUrls.list()}" optionKey="id" optionValue="name"/>

</div>



<div class="fieldcontain ${hasErrors(bean: xmlExamplesInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="xmlExamples.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${xmlExamplesInstance?.name}"/>

</div>


<div class="fieldcontain ${hasErrors(bean: xmlExamplesInstance, field: 'content', 'error')} required">
	<label for="content">
		<g:message code="xmlExamples.content.label" default="Content" />
		<span class="required-indicator">*</span>
	</label>

<g:textArea id="inputXML"  name="content" rows="5" cols="40" value="${xmlExamplesInstance?.content}" />
</div>


