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
	$(elem).data("begin",value);
	var  time=date.getTime();
	$(elem).parents("tr").find(".pause").click(function(){
		
		$(this).parents("tr").find(".flip").data("start","false")
	})
	$(elem).parents("tr").find(".play").click(function(){
		$(this).parents("tr").find(".flip").data("start","true")
	})
	$(elem).parents("tr").find(".stop").click(function(){
		var a = $(this).parents("tr").find(".flip")
		$(a).data("start","false");
		$(a).data("time",$(a).data("begin"));
		date=new Date('5.10.2012 '+$(a).data("begin"));
		time = date.getTime();
	})
	$(elem).parents("tr").find(".repeat").click(function(){
		var a = $(this).parents("tr").find(".flip")
		$(a).data("start","true");
		$(a).data("time",$(a).data("begin"));
		date=new Date('5.10.2012 '+$(a).data("begin"));
		time = date.getTime();
	});	
	
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
function getBegin(index){
	return parseInt($(".flip").eq(index).data("begin"),10);
}
function getTimeExpected(index){
	return getBegin(index);
}
function isSuccess(index){
	return $(".row").eq(index).find(".success").prop("checked");
}

$(".validate").click(function(){
		
		
	
	var userExerciceData = {
			date: new Date(),
			idUser: userId,
			planTitle: "",
			exerciceTitle: getTitle(0),
			duration: getDuration($(this).index()),
			timeExpected: getTimeExpected($(this).index()),
			status: isSuccess($(this).index())?"success":"failure"
	};
	console.log(userExerciceData);
	$.post("/personalData",{userExerciceData:JSON.stringify(userExerciceData)});
});
