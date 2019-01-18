function passwordCheck(){
	if($('#inputPassword5')[0].value!=$('#inputPassword4')[0].value || $('#inputPassword4')[0].value=='' ){
		$('#inputPassword5')[0].setCustomValidity('Les mots de passe doivent être identiques.');
	}else {
		$('#inputPassword5')[0].setCustomValidity('');
	  }
}

function tjrfaux(){

		$('#inputPassword5')[0].setCustomValidity('');
	
}

function checkPasswordConformity(){
	 var letterNumber = /^(?=.*[a-zA-Z])(?=.*[0-9])/;
	if($('#inputPassword4')[0].value.length >=8  && letterNumber.test($('#inputPassword4')[0].value)==true){
		
		$('#inputPassword4')[0].setCustomValidity('');
	}else {
		$('#inputPassword4')[0].setCustomValidity('Le mot de passe doit faire au moins 8 caractères');
	  }

}