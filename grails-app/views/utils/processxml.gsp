	<b>${attempt }</b>		
			
<div class="internalbtn2">${url }</div><div class="internalbtn2">Status ${status }</div>
<g:if test="${resHeader }">
<g:each in="${resHeader}" var="ss">
<div class="dbutton">
${ss }
</div>
<div class="btn">|</div>
</g:each>

</g:if>

<g:textArea id="responseXML" name="xmlinput" rows="15" cols="60" >${output}</g:textArea>

<g:if test="${rmap }">

<table>
<tr><th>ID</th><th>NAME:</th><th>ATTRIBUTE</th></tr>
<g:each in="${rmap }" var="maps">
	<g:if test="${maps.attribute }">
	<tr><td>${maps.nid }</td><td>${maps.name }</td><td>
	<g:each in="${maps.attribute }" var="attrs">
	${attrs }
	</g:each>
	<g:if test="${maps.val.size()==1 && maps.nid.toInteger()>4 }">
	<g:each in="${maps.val }" var="val">
		<b><font color="red">${val }</font></b>
	</g:each>
	</g:if>
	</td></tr>
	</g:if>
</g:each>
</table>
</g:if>