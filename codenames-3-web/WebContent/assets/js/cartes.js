function ajouterCarte(){
//jQuery
	var maCarte = {
		nom : $('input[name="nom"]').val(),
		couleur : $('#couleur option:selected').text()
	}
	
	//CREATION DE COLONNES
	var myColId = $('<td />');
	var myColNom = $('<td>' + maCarte.nom + '</td>');
	var myColCouleur = $('<td>' + maCarte.couleur + '</td>');

	//AFFECTER LES VALEURS AUX COLONNES
	myColId.html($('table tbody tr').length + 1);
	
	//CREATION DE LA LIGNE
	var myLigne = $('<tr />');
	
	//ASSOCIER LES COLONNES A LA LIGNE
	myLigne.append(myColId);
	myLigne.append(myColNom);
	myLigne.append(myColCouleur);
	
	//INSERER LA LIGNE AU TABLEAU
	$('table tbody').append(myLigne);

}