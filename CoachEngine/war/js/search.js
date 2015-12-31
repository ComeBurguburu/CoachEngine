"use strict";
function template(title,url,time){
return "<div class=\"row\"><div class=\"col-lg-6 col-md-6 col-sm-6 col-xs-6\">"+
"<a href=\""+url+"\" class=\"btn btn-link\">"+title+"</a>"+
"</div>"+
"<div class=\"col-lg-6 col-md-6 col-sm-6 col-xs-6\">"+
  "<label class=\"btn\"> <span class=\"glyphicon glyphicon-time\"></span> "+time+" min. </label></div></div>";
}

$(".form-control").on("keydown keyup",function(){
	var q = $(this).val();
	//console.log(q);
	$.get("/search",{q:q},function(response){
		try{
		var json = JSON.parse(response);
		}catch(e){
			
		}
		$("#result").html("<h1>Training</h1><div></div><h1>Exercice</h1><div></div>");
		$.each(json.Training,function(key,value){
			$("#result>div:eq(0)").append(template(value.name,value.url,value.time));
		});

		$.each(json.Exercice,function(key,value){
			
			$("#result>div:eq(1)").append(template(value.name,value.url,value.time));
		});

	})
})