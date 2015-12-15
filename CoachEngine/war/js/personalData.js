var exemple={"Date": "12/04/15", "idUser": 23, "planTitle": "Apprendre Volley", "exTitle": "lancer ball", "Status": "Success"};

$.get("/personalData",function(data){alert("Data: "+data);});
if(exemple.Status=="Success"){
	completed="Yes";
}
else{
	completed="No";
}
var Html="<tr><td style='border: 3px solid black; padding: 5%;'>"+exemple.Date+"</td><td style='border: 3px solid black; padding: 5%;'>"+exemple.planTitle+"</td><td style='border: 3px solid black; padding: 5%;'> expected</td><td style='border: 3px solid black; padding: 5%;'> Time</td><td style='border: 3px solid black; padding: 5%;'>"+completed+"</td>";
$("#personalDataTable").append(Html);
