
<!DOCTYPE html>
<html>
	<head>
		
		<meta name="layout" content="main">

</head>
<body>
<div class="container">
<div class="container">
<br><br><br>
<g:render template="menu"/>

<h1>Provide your compressed XML in this form to get pretty XML</h1>
<g:form method="post" controller="utils" action="parsexml">
<g:textArea id="inputXML"  name="input" rows="5" cols="40" />

<input type="submit" value="pretty XML">



</g:form>
</div></div>
</body>
</html>