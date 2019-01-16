function createRowCarte(carte){
	//CREATION DE COLONNES
	var myColId = $('<td>' + carte.id + '</td>');
	var myColLibelle = $('<td>' + carte.libelle + '</td>');
	var myColChoix = $('<td> <a class="btn btn-primary" href="#modifierCarte">modifier</a></td>');
	//<a class="btn btn-primary" href="#inscription">Inscription</a>
	//CREATION DE LA LIGNE
	var myLigne = $('<tr />');
	
	//ASSOCIER LES COLONNES A LA LIGNE
	myLigne.append(myColId);
	myLigne.append(myColLibelle);
	myLigne.append(myColChoix);
	
	//INSERER LA LIGNE AU TABLEAU
	$('table tbody').append(myLigne);
}

function createListeDeroulanteCarte(carte){
	//CREATION DES LIGNES
	var myId = $('<option value="'+ carte.id + '">' + carte.libelle + '</option>');
	
	//INSERER LA LIGNE AU TABLEAU
	$('select[id="nomCarte"]').append(myId);
}

$.ajax({
	method : 'GET',
	url : 'http://192.168.1.110/codenames-ajax/carte',
	success : function(cartes) {
		for(let carte of cartes){
			createRowCarte(carte);
			createListeDeroulanteCarte(carte);
		}
	}
});

function ajouterCarte(){
	var maCarte = {
		libelle : $('input[name="nom"]').val(),
		//couleur : $('#couleur option:selected').text()
	}
	//REQUETE AJAX POUR AJOUTER LE PRODUIT
	$.ajax({
		method : 'POST',
		url : 'http://192.168.1.110/codenames-ajax/carte',
		data : JSON.stringify(maCarte),	//CONVERTIR L'OBJET JS EN JSON
		contentType : 'application/json',	//DE QUOI EST FAIT LE FLUX
		success : function(carte) {		//LA REPONSE DU SERVEUR
			createRowCarte(carte);
		}
	});
}

function modifierCarte(){
	var maCarte = {
		id: $('select[name="nomCarte"]').val(),
		libelle : $('input[name="newNom"]').val()
	}
	//REQUETE AJAX POUR AJOUTER LE PRODUIT
	$.ajax({
		method : 'POST',
		url : 'http://192.168.1.110/codenames-ajax/carte',
		data : JSON.stringify(maCarte),	//CONVERTIR L'OBJET JS EN JSON
		contentType : 'application/json',	//DE QUOI EST FAIT LE FLUX
		success : function(carte) {		//LA REPONSE DU SERVEUR
			createRowCarte(carte);
		}
	});
}

$('table[id="cartes"]').bind('click', function() {
//	alert("A MALIBU");
//	this -> lien cliqué
//	alert($(this).attr('href'));
	alert("test");
	$($(this).attr('td a')).show();
	return false;
})