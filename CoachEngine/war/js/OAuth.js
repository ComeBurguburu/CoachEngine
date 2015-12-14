"use strict";
function hash() {
    var obj = {};
   $(location).prop("hash").slice(1).split("&").forEach(function (param) {var tab = param.split("="); if (tab.length === 2) {if (obj[tab[0]] === undefined) {obj[tab[0]] = tab[1]; } else {if (typeof obj[tab[0]] !== "object") {obj[tab[0]] = [obj[tab[0]]]; obj[tab[0]].push(tab[1]); } } } });
   return obj
}
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
    $(".name").html(login);
    $(".pict").prop("src",picture);
    $(".email").html(email);
    $.post("/login",{login:login,email:email,picture:picture});
  },
  error: function(e) {
    console.log('error');

  }
});
}