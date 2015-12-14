$(".form-control").on("keydown keyup",function(){
	var q = $(this).val();
	//console.log(q);
	$.get("/search",{q:q},function(response){
		try{
		var json = JSON.parse(response);
		}catch(e){
			
		}
		$("#result").html("<h1>Training</h1><div></div><h1>Exercice</h1><div></div>");
		$.each(json.training,function(key,value){
			$("#result div:eq(0)").append("<a href=\""+value.url+"\">"+value.name+"</a>");
		});

		$.each(json.exercice,function(key,value){
			$("#result div:eq(1)").append("<a href=\""+value.url+"\">"+value.name+"</a>");
		});

	})
})
//$(".form-control").val("rr");