
<!DOCTYPE html>
<html>
<head>

<meta name="layout" content="main">

</head>
<body>
	<div class="container">
		<div class="container">
			<br>
			<br>
			<br>

			<g:render template="menu" />


			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>

			<g:formRemote id="aa" name="test" update="processXML"
				url="[ controller: 'utils', action: 'processxml']">


				<div class="formRow">
					<div class="tbutton">URL:</div>

					<g:select class="form-control1" name="xmlloader" id="xmlloader"
						from="${xmlclient?.XmlUrls?.list()}" optionKey="url"
						optionValue="name"
						onchange="updateUrl(this.value);updateExamples();"
						noSelection="['': 'Choose URL']" />
					<div class="xmlexample" style="display: inline;"></div>

<g:javascript>
function updateExamples() {
	var key = document.getElementById('xmlloader').options[document.getElementById('xmlloader').selectedIndex].text;
	$.get('${createLink(controller:"utils", action: "listExamples")}?id='+key,function(data){
		$('.xmlexample').html(data);
	});
}

function updateUrl(entry){ 
	$('#xmlurl').val(entry);
}
function resetView() { 
	$('#processXML').html('Processing');
}
</g:javascript>

					<g:field class="inputCell" type="xmlurl" id="xmlurl" name="xmlurl"
						value="" />

					<g:select class="form-control1" name="xmltype"
						from="${xmlclient.SendTypes.list()}"  optionKey="name" optionValue="name"/>

					<g:select class="form-control1" name="encoding"
						from="${xmlclient.LookupEncoding.list()}"  optionKey="name" optionValue="name"/>
					
												
					<g:select class="form-control1" name="rtype"
						from="${xmlclient.LookupTypes.list()}"  optionKey="formatting" optionValue="name"/>
						
					<g:submitButton id="aa" name="postxml" value="SEND"
						onclick="resetView()" />
				</div>
				<g:textArea id="inputXML" name="xmlinput" rows="10" cols="60" />

			</g:formRemote>

			<br />
			<br />
			<div id="processXML">Awaiting results here.</div>
		</div>
	</div>


</body>
</html>
