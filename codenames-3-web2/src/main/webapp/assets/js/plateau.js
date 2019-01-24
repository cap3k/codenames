function showColor(e, couleur) {
	var that = $(e.target);
	that.removeClass('btn btn-secondary btn-block');
	if (couleur == "ROUGE") {
		that.addClass('btn btn-outline-danger btn-block');
	} else if (couleur == "BLEUE") {
		that.addClass('btn btn-outline-primary btn-block');
	} else if (couleur == "NEUTRE") {
		that.addClass('btn btn-outline-warning btn-block');
	} else {
		that.addClass('btn btn-outline-dark btn-block');
		that[0].setAttribute("data-toggle", "modal");
		that[0].setAttribute("data-target", "#myModal");
	}

};
//function showAll(role) {
//	if (role == 0) {
//		$("#button").trigger('click');
//	}
//}

var eventSource = new EventSource(
		"http://192.168.1.103/codenames-3-web2/plateauDevineur/listen-new");

eventSource.onmessage = function(evt) {
	var monProduitRecuDuServeur = JSON.parse(evt.data);
	laFonctionJS2(monProduitRecuDuServeur);
}
