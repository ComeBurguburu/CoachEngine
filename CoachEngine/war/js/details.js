"use strict";
$.get("/addtraining",function(response){
	
	var json = JSON.parse(response);
	console.log(json);
	var index = 0;
	$.each(json.Exercice, function(key, value){
		console.log(index + "  " + (json.Exercice.length -1));

		setTitle(index, value.Title);
		setTime(index, value.Time);
		setDescription(index,value.Description);
		index++;
		if(index < json.Exercice.length - 1){
			$("table").append($("tr:eq(0)").clone());
		}else{
			console.info(index + "  " + (json.Exercice.length -1));
		}
	});
	
	
});
function setTitle(index, value){
	$(".title-exercice").eq(index).text(value);
}
function setTime(index, value){
	$(".time-exercice").eq(index).text(value);
}
function setDescription(index, value){
	$(".description-exercice").eq(index).text(value);
}