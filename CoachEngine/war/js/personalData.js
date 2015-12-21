//var exemple={"Date": "12/04/15", "idUser": 23, "planTitle": "Apprendre Volley", "exTitle": "lancer ball", "Status": "Success"};

$.get("/personalData",function(resp){
	
	data=JSON.parse(resp);
	alert(data);
for (i=0;i<data.length;i++){
if(data[i].Status=="Success"){
	completed="Yes";
}
else{
	completed="No";
}
var Html="<tr><td style='border: 3px solid black; padding: 5%;'>"+data[i].date+"</td><td style='border: 3px solid black; padding: 5%;'>"+data[i].exerciceTitle+"</td><td style='border: 3px solid black; padding: 5%;'> "+data[i].timeExpected+"</td><td style='border: 3px solid black; padding: 5%;'>"+data[i].duration+"</td><td style='border: 3px solid black; padding: 5%;'>"+completed+"</td>";
$("#personalDataTable").append(Html);
}
});