"use strict";
var tab = {};
tab.Exercice = [];
var currentDate = 0;

$("#validate").click(function () {
	tab.Title = $("#inputTitle").val();
	tab.Description = $("#inputDescription").val();
	tab.Domain = $("#e1").val();
	tab.Date = currentDate.toHHMMSS();

	$.post("/addtraining", {
		training: JSON.stringify(tab)
	});


});

Number.prototype.toHHMMSS = function () {
	var sec_num = parseInt(this, 10); // don't forget the second param
	var hours = Math.floor(sec_num / 3600);
	var minutes = Math.floor((sec_num - (hours * 3600)) / 60);
	var seconds = sec_num - (hours * 3600) - (minutes * 60);

	if (hours < 10) {
		hours = "0" + hours;
	}
	if (minutes < 10) {
		minutes = "0" + minutes;
	}
	if (seconds < 10) {
		seconds = "0" + seconds;
	}
	var time = hours + ':' + minutes + ':' + seconds;
	return time;
}

//var tabId=[];
//var id=0;

$("#buttonAddEx").click(function () {


	var currentExo = {
		"Title": "undefined"
	};
	currentExo.Title = $("#titleDescription").val();
	currentExo.Description = $("#exerciceDescription").val();
	currentExo.Hours = $("#hours").val();
	currentExo.Minutes = $("#minutes").val();
	currentExo.Seconds = $("#seconds").val();

	var duration_valid = !isNaN(currentExo.Hours) && !isNaN(currentExo.Minutes) && !isNaN(currentExo.Seconds) && currentExo.Hours != "" && currentExo.Minutes != "" && currentExo.Seconds != "";



	var DateTmp = parseInt(currentExo.Hours, 10) * 3600 + parseInt(currentExo.Minutes, 10) * 60 + parseInt(currentExo.Seconds, 10)
	currentDate = currentDate + DateTmp;
	currentExo.Date = currentDate.toHHMMSS();

	console.log(currentExo);

	if (duration_valid === true) {
		var Html = "<tr id='" + currentExo.Title + "'><td>1</td><td>" + currentExo.Title + "</td><td class='hidden-xs'><p>" + currentExo.Description + "</p></td><td>" + currentExo.Hours + " Hours " + currentExo.Minutes + " Minutes " + currentExo.Seconds + " Seconds </td><td><button id='" + currentExo.Title + "ButtonRemove' type='submit' class='btn btn-danger btn-sm'><span class='glyphicon glyphicon-remove'></span></button></td></tr><tr>";
		$("#listExercices").append(Html);


		$("#totalTime").remove();
		$("#totalTimeValue").append("<div id='totalTime'>" + currentDate.toHHMMSS() + "</div>");

		tab.Exercice.push(currentExo);
		console.log(tab.Exercice[0]);


		//for(var i in tabId)
		//{

		$("#" + currentExo.Title + "ButtonRemove").click(function () {
			console.log("Button Remove");
			var grandPere = $(this).parent().parent();
			var id = grandPere.index();
			tab.Exercice.splice(id, 1);
			grandPere.remove();
			//	tab.Exercice.sl(currentExo);
			//var x=$(this).data(id);

		});



		//}
		$("#titleDescription").val("");
		$("#exerciceDescription").val("");
		$("#hours").val("");
		$("#minutes").val("");
		$("#seconds").val("");
	}
});

$("#refreshAll").click(function () {
	location.reload();

});