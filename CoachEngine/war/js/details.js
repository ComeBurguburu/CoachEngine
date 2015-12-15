"use strict";
$.get("/addtraining",function(response){
	
	var json = JSON.parse(response);
	var index = 0;
	var block = $("tr:eq(0)").clone();
	$.each(json.Exercice, function(key, value){
		setTitle(index, value.Title);
		setTime(index, value.Date);
		setDescription(index,value.Description);
		setFlip(index,value.Date);
		index++;
		if(index < json.Exercice.length - 1){
			$("table").append(block.clone());
		}
	});
	
	
});
function setTitle(index, value){
	$(".title-exercice").eq(index).text(value);
}
function getTitle(index, value){
	return $(".title-exercice").eq(index).text();
}
function setTime(index, value){
	$(".time-exercice").eq(index).text(value);
}
function setDescription(index, value){
	$(".description-exercice").eq(index).text(value);
}
function setFlip(index, value){
	console.assert(value!=undefined);
	var elem = $(".flip").eq(index);
	var date=new Date('5.10.2012 '+value);
	var  time=date.getTime();

    $(elem).flipcountdown({speedFlip:60,tick:function(){
             var currentTime=time-1000;
             	time=currentTime;
             	date=new Date(currentTime);
             return date;
         }});
}

var userExerciceData = {
		date: new Date(),
		//idUser: userId,
		planTitle: "",
		exerciceTitle: getTitle(0),
		status: "success"
};
$.post("/personalData",{userExerciceData:JSON.stringify(userExerciceData)});

console.log(userExerciceData);
$("#validate").click(function(){
	alert("entraine toi");
});
