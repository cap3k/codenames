function rafraichir(){
	deleteRowCarte();
	$.ajax({
		method : 'GET',
		url : 'http://192.168.1.110/codenames-ajax/carte',
		success : function(cartes) {
			for(let carte of cartes){
				createRowCarte(carte);
			}
			$('tr td input[value="Modifier"]').bind('click', function(){
				//je pars du bouton(this) je remonte  la racine (tr) du bouton, je cherche dans la racine(tr) le champe de saisie (input[class="input-field"]) e et je l'affiche
				//$(this).closest('tr').find('input[value="Supprimer"]').setAttribute("disabled", "disabled");;
				$(this).closest('tr').find('input[class="input-field"]').show();
				$(this).closest('tr').find('input[value="Valider"]').show();
				return false;
			});
			$('tr td input[value="Valider"]').bind('click', function(){
				//modifier la carte avec l'id de la ligne et le nouveau libellé
				modifierCarte($(this).closest('tr').data('id'), $(this).closest('tr').find('input[class="input-field"]').val());
				//cacher des champs
				$(this).closest('tr').find('input[class="input-field"]').hide();
				$(this).closest('tr').find('input[value="Valider"]').hide();
				$(this).closest('tr').find('input[value="Supprimer"]').show();
				$(this).closest('tr').find('tr td input[value="Supprimer"]').disabled = false;
				return false;
			});
			$('tr td input[value="Supprimer"]').bind('click', function(){
				supprimerCarte($(this).closest('tr').data('id'));
				return false;
			});
		}
	});
}

function createRowCarte(carte){
	//CREATION DE COLONNES
	var myColId = $('<td>' + carte.id + '</td>');
	var myColLibelle = $('<td>' + carte.libelle + '</td>');
	var myColButton = $('<td> <input class="btn btn-sucess" type="submit" value="Modifier"/>' + 
							' <input class="btn btn-sucess" type="submit" value="Supprimer"/> ' + 
							' <input type="text" class="input-field" id="saisieNewNom" name="newNom" value="' + carte.libelle + '" required/> '+
							' <input class="btn btn-sucess" type="submit" value="Valider"/> </td>');
	
	//CREATION DE LA LIGNE
	var myLigne = $('<tr data-id="' + carte.id + '" />');
	
	//ASSOCIER LES COLONNES A LA LIGNE
	myLigne.append(myColId);
	myLigne.append(myColLibelle);
	myLigne.append(myColButton);
	
	//INSERER LA LIGNE AU TABLEAU
	$('table tbody').append(myLigne);
}

function deleteRowCarte(){	
	//SUPPRIMER LES LIGNES DU TABLEAU
	$('table tbody td').remove();
}

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
			rafraichir();
		}
	});
}

function modifierCarte(id, libelle){
	var maCarte = {
		id: id,
		libelle : libelle
	}
	//REQUETE AJAX POUR AJOUTER LE PRODUIT
	$.ajax({
		method : 'POST',
		url : 'http://192.168.1.110/codenames-ajax/carte',
		data : JSON.stringify(maCarte),	//CONVERTIR L'OBJET JS EN JSON
		contentType : 'application/json',	//DE QUOI EST FAIT LE FLUX
		success : function(carte) {		//LA REPONSE DU SERVEUR
			rafraichir();
		}
	});
}

function supprimerCarte(id){
	var maCarte = {
		id: id
	}
	//REQUETE AJAX POUR AJOUTER LE PRODUIT
	$.ajax({
		method : 'DELETE',
		url : 'http://192.168.1.110/codenames-ajax/carte/' + maCarte.id,
		success : function(carte) {		//LA REPONSE DU SERVEUR
			rafraichir();
		}
	});
}



rafraichir();