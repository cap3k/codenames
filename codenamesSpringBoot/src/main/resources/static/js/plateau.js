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

if ($('div[data-role_id]').data('role_id')=="0") {
	$('button[data-col_id="ROUGE"]').addClass('btn btn-danger btn-block');
	$('button[data-col_id="BLEUE"]').removeClass('btn btn-secondary btn-block')
	$('button[data-col_id="BLEUE"]').addClass('btn btn-primary btn-block');
	$('button[data-col_id="NEUTRE"]').addClass('btn btn-warning btn-block');
	$('button[data-col_id="NOIRE"]').addClass('btn btn-dark btn-block');
}
//var eventSource = new EventSource(
//		"http://192.168.1.103/codenames-3-web2/plateauDevineur/listen-new");
//
//eventSource.onmessage = function(evt) {
//	var monProduitRecuDuServeur = JSON.parse(evt.data);
//	laFonctionJS2(monProduitRecuDuServeur);
//}

function showColor2(e,grille,i){
	
		$.ajax({
			method : 'post',
			url : 'http://192.168.1.124:8080/plateau',
			contentType : 'application/json',
			headers: {"X-CSRF-TOKEN": $("input[name=_csrf]").val()},
			data : JSON.stringify({
				"pos": i,
				"grille": grille
				}),
			success: function(data){
				showColor(e,data);
			}

		});
};


