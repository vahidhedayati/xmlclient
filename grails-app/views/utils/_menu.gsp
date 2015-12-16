<div class="btn btn-warning">
<g:link controller="utils" action="admin">Admin</g:link>
</div>


<div class="btn btn-warning">
<g:link controller="utils" action="index">Pretty XML</g:link>
</div>
<div class="btn btn-success">
<g:link controller="utils" action="testxml">Post basic XML</g:link>
</div>
<div class="btn btn-success">
<g:link controller="utils" action="advanced">Post Advanced XML</g:link>
</div>

<g:javascript>
function updateXMLView(entry){
 $.get('${createLink(controller:"utils", action: "exampleContent")}?id='+entry,function(data){
 
$('#inputXML').val(data);
});

}
</g:javascript>