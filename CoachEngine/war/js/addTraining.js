
tab={};
tab.Exercice=[];
DateOKLM = 0;


$("#validate").click(function() {
	console.log("coucou");
	tab.Title=$("#inputTitle").val();
	tab.Description=$("#inputDescription").val();
	tab.Domain=$("#e1").val();
	tab.Date = DateOKLM;
	console.log(tab.Date);
	$.post("/addtraining",{training: tab});
	
});

Number.prototype.toHHMMSS = function () {
    var sec_num = parseInt(this, 10); // don't forget the second param
    var hours   = Math.floor(sec_num / 3600);
    var minutes = Math.floor((sec_num - (hours * 3600)) / 60);
    var seconds = sec_num - (hours * 3600) - (minutes * 60);

    if (hours   < 10) {hours   = "0"+hours;}
    if (minutes < 10) {minutes = "0"+minutes;}
    if (seconds < 10) {seconds = "0"+seconds;}
    var time    = hours+':'+minutes+':'+seconds;
    return time;
}

$("#buttonAddEx").click(function() {
  
	
	currentExo={"Title": "undefinedConnard"};
	currentExo.Title=$("#titleDescription").val();
	currentExo.Description=$("#exerciceDescription").val();
	currentExo.Hours=$("#hours").val();
	currentExo.Minutes=$("#minutes").val();
	currentExo.Seconds=$("#seconds").val();
	
	
	DateTmp=parseInt(currentExo.Hours,10)*3600+parseInt(currentExo.Minutes,10)*60+parseInt(currentExo.Seconds,10)
	DateOKLM=DateOKLM+DateTmp;
	
	/*
	console.log(Exercice[0].Title);
	console.log(Exercice[0].Description);
	console.log(Exercice[0].Hours);
	console.log(Exercice[0].Minutes);
	console.log(Exercice[0].Seconds);
	
	*/
	
	var Html="<tr><td>1</td><td>"+currentExo.Title+"</td><td class='hidden-xs'><p>"+currentExo.Description+"</p></td><td>"+currentExo.Hours+" Hours "+currentExo.Minutes+" Minutes "+currentExo.Seconds+" Seconds </td><td><button type='submit' class='btn btn-danger btn-sm'><span class='glyphicon glyphicon-remove'></span></button></td></tr><tr>";
	$("#listExercices").append(Html);
	
	tab.Exercice.push(currentExo);
	console.log(tab.Exercice[0]);
	
	currentExo.Title=$("#titleDescription").val("");
	currentExo.Description=$("#exerciceDescription").val("");
	currentExo.Hours=$("#hours").val("");
	currentExo.Minutes=$("#minutes").val("");
	currentExo.Seconds=$("#seconds").val("");
	
});