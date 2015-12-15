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
		if(index < json.Exercice.length){
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
	$(elem).parents("tr").find(".pause").click(function(){
		
		$(this).parents("tr").find(".flip").data("start","false")
	})
	$(elem).parents("tr").find(".play").click(function(){
		$(this).parents("tr").find(".flip").data("start","true")
	})
	
    $(elem).flipcountdown({speedFlip:60,tick:function(){
   		
    		var sec = $(this).data("time")==undefined?0:parseInt($(this).data("time"),10);
             var currentTime;
             
    		if($(this).data("start")!=="false"){
    			currentTime=time-1000;
    			sec++;
    		}else{
    			currentTime = time;
    		}
             	time=currentTime;
             	date=new Date(currentTime);
             	$(elem).data("time",sec);
             return date;
         }});
}


function getDuration(index){
	return parseInt($(".flip").eq(index).data("time"),10);
}

$(".validate").click(function(){
		
		
	
	var userExerciceData = {
			date: new Date(),
			idUser: 0,
			planTitle: "",
			exerciceTitle: getTitle(0),
			duration: getDuration($(this).index()),
			status: "success"
	};
	console.log(userExerciceData);
	$.post("/personalData",{userExerciceData:JSON.stringify(userExerciceData)});
});
