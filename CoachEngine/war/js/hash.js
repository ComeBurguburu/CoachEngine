"use strict";

function hash() {
	return extract("hash");
}

function param() {
	return extract("search");
}

function extract(key) {
	var obj = {};
	$(location).prop(key).slice(1).split("&").forEach(function (param) {
		var tab = param.split("=");
		if (tab.length === 2) {
			if (obj[tab[0]] === undefined) {
				obj[tab[0]] = tab[1];
			} else {
				if (typeof obj[tab[0]] !== "object") {
					obj[tab[0]] = [obj[tab[0]]];
					obj[tab[0]].push(tab[1]);
				}
			}
		}
	});
	return obj
}