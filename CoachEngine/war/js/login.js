//display login information on the top page
var userId = 0;
$.get("/login", function (data) {
	try {
		var json = JSON.parse(data);
	} catch (e) {

	}
	//redirect to main page
	if (!json.login) {
		if (location.pathname !== "/search.html") {
			//comment to demontration
			//location.href="/search.html";
		}
		return;
	} else {
		if ($(".connectGoogle").length == 2) {
			$(".connectGoogle:eq(0)").hide();
			$(".connectGoogle:eq(1)").show();
		}
		$(".name").text(json.login);
		$(".pict").prop("src", json.picture);
		$(".email").html(json.email);
		userId = json.id;
		console.info("ready");
		$("#logout").click(function () {
			$.post("/login", {
				reset: true
			}, function () {

				document.location.href = "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://coachengine-1147.appspot.com/search.html";


			});
		});


	}

});