<g:if test="${xmlus }">

<g:select name="xmlexamples2" from="${xmlus?.examples}"  
optionKey="name" optionValue="name" 
 noSelection="['': 'Choose Template']" 

 onchange="updateXMLView(this.value);" />


</g:if>