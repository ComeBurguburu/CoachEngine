//display login information on the top page
$.get("/login",function(data){
	try{
		var json = JSON.parse(data);
	}catch(e){
		
	}
	if(!json.login){
		return;
	}else{
		$(".name").html(json.login);
	    $(".pict").prop("src",json.picture);
	    $(".email").html(json.email);
	    idUser = json.id;
	}
	
	
})