
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

				<div class="formRow2">
					<div class="tbutton">URL:</div>
					<g:select class="form-control1" name="xmlloader"
						from="${xmlclient?.XmlUrls?.list()}" optionKey="url"
						optionValue="name" onchange="updateUrl(this.value);"
						noSelection="['': 'Choose URL']" />


<g:javascript>
	function updateUrl(entry){ 
		$('#xmlurl').val(entry);
	}
	function resetView() { 
		$('#processXML').html('Processing');
	}
</g:javascript>
					<g:field class="form-control1" class="inputCell" type="xmlurl"
						id="xmlurl" name="xmlurl" value="" />
					<g:select class="form-control1" name="xmltype"
						from="${[ 'post', 'get', 'put' ]}" />
					<g:select class="form-control1" name="rtype"
						from="${[ 'xml', 'json' ]}" />

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
