function rafraichir(){
	deleteRowCarte();
	$.ajax({
		method : 'GET',
		url : 'http://192.168.1.110/codenames-ajax/carte',
		success : function(cartes) {
			for(let carte of cartes){
				createRowCarte(carte);
			}
			//APPUI BOUTON DE MODIFICATION DE CARTES
			$('tr td input[value="Modifier"]').bind('click', function(){
				//désaciver les boutons modifier et supprimer
				$(this).closest('tr').find('input[value="Modifier"]').attr('disabled', true);
				$(this).closest('tr').find('input[value="Supprimer"]').attr('disabled', true);
				//cacher le libellé de la carte
				$(this).closest('tr').find('td span').hide();
				//afficher le formulaire de saisie pour modifier le libellé du mot
				$(this).closest('tr').find('input[class="input-field"]').show();
				//afficher les boutons valider et annuler
				$(this).closest('tr').find('input[id="ValiderModif"]').show();
				$(this).closest('tr').find('input[id="cancel"]').show();
				return false;
			});
			//MODIFIER LA CARTE AVEC L'ID DE LA LIGNE ET LE NOUVEAU LIBELLÉ
			$('tr td input[id="ValiderModif"]').bind('click', function(){
				//je pars du bouton(this) 
				//je remonte  la racine (tr) du bouton
				//je cherche dans la racine(tr) le champe de saisie (input[class="input-field"]) du libellé
				//et je l'envoie dans la fonction de modification de carte
				modifierCarte($(this).closest('tr').data('id'), $(this).closest('tr').find('input[class="input-field"]').val());
				//désaciver les boutons
				$(this).closest('tr').find('input[value="Supprimer"]').attr('disabled', false);
				$(this).closest('tr').find('input[value="Modifier"]').attr('disabled', false);
				//réafficher le nouveau libellé de la carte
				$(this).closest('tr').find('td span').show();
				//cacher des champs
				$(this).closest('tr').find('input[class="input-field"]').hide();
				$(this).closest('tr').find('input[id="ValiderModif"]').hide();
				return false;
			});
			//SUPPRIMER LA CARTE
			$('tr td input[value="Supprimer"]').bind('click', function(){
				supprimerCarte($(this).closest('tr').data('id'));
				return false;
			});
			//ANNULER LA MODIFICATION DE LA CARTE
			$('tr td input[id="cancel"]').bind('click', function(){
				//réactiver les boutons
				$(this).closest('tr').find('input[value="Modifier"]').attr('disabled', false);
				$(this).closest('tr').find('input[value="Supprimer"]').attr('disabled', false);
				//ré-afficher le libellé de la carte
				$(this).closest('tr').find('td span').show();
				//cacher le formulaire de modification et les boutons
				$(this).closest('tr').find('input[class="input-field"]').hide();
				$(this).closest('tr').find('input[id="ValiderModif"]').hide();
				$(this).closest('tr').find('input[id="cancel"]').hide();
				return false;
			});
		}
	});
}

function createRowCarte(carte){
	//CREATION DE COLONNES
	var myColId = $('<td class="col1">' + carte.id + '</td>');
	var myColLibelle = $('<td> <span>' + carte.libelle + '</span>'+
							  '<input type="text" class="input-field" id="saisieNewNom" name="newNom" value="' + carte.libelle + '" required/><br/>'+
							  '<input class="btn btn-sucess" type="submit" id="ValiderModif" value="Valider"/> ' + 
							  '<input class="btn btn-sucess" type="submit" id="cancel" value="Annuler" /></td>');
	var myColButton = $('<td class="col3"> <input class="btn btn-sucess" type="submit" value="Modifier" />' + 
							' <input class="btn btn-sucess" type="submit" value="Supprimer" /> </td>');
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

function cancel () {
    document.execCommand('Stop')
}


rafraichir();