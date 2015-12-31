"use strict";
var userId;
var _hash = hash();
if(_hash.access_token !== undefined){
	$(".connectGoogle").toggle();
}
var access_token = _hash.access_token;
var url = 'https://www.googleapis.com/plus/v1/people/me?access_token='+access_token;
if(access_token!==undefined){
$.ajax({
  type: 'GET',
  url: url,
  success: function(userInfo) {
    //info about user
    console.log(userInfo);
    var login = userInfo.displayName;
    var email = userInfo.emails[0].value;
    var picture = userInfo.image.url;
    $(".name").text(login);
    $(".pict").prop("src",picture);
    $(".email").text(email);
    userId = userInfo.id;
    $.post("/login",{login:login,email:email,picture:picture,id:userId});
  },
  error: function(e) {
    console.log('error');

  }
});
}