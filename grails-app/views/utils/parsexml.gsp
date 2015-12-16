<!DOCTYPE html>
<html>
	<head>
	<meta name="layout" content="main">
	</head>
<body>
<div class="container">
<div class="container">
<g:render template="menu"/>


		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
<h1>Your Pretty XML is : </h1>
<div id="processXML">
<pre>
${input.encodeAsXML() }
</pre>
</div>
</div>
</div>
</body>
</html>
