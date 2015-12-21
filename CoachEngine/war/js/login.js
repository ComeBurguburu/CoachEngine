//display login information on the top page
var userId = 0;
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
	    userId = json.id;
	    console.info("ready");
	}
	
	
})