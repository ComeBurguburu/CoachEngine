"use strict";
$.get("/addtraining",function(response){
	
	var json = JSON.parse(response);
	console.log(json);
	window.test = json;
	var index = 0;
	$.each(json.Exercice, function(key, value){
		setTitle(index, value.Title);
		setTime(index, value.Date);
		setDescription(index,value.Description);
		alert(JSON.stringify(value));
		//setFlip(index,value.Date);
		index++;
		if(index < json.Exercice.length - 1){
			$("table").append($("tr:eq(0)").clone());
		}
	});
	
	
});
function setTitle(index, value){
	$(".title-exercice").eq(index).text(value);
}
function setTime(index, value){
	$(".time-exercice").eq(index).text(value);
	setFlip(index,value);
}
function setDescription(index, value){
	$(".description-exercice").eq(index).text(value);
}
function setFlip(index, value){
	console.assert(value!=undefined);
	var elem = $(".flip").eq(index);
	console.log('5.10.2012 '+value);
	var date=new Date('5.10.2012 '+value);
  var  time=date.getTime();

    $(elem).flipcountdown({speedFlip:60,tick:function(){
             var currentTime=time-1000;
             	time=currentTime;
             	date=new Date(currentTime);
             return date;
         }});
}