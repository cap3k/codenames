function letest(data) {
	$("#table").empty();

	var nbCol = Math.sqrt($("#taille option:selected").text());

	var monTableau = $('#table');
	var listeMots = shuffle(data);
	var k=0;
	for (var j = 0; j < nbCol; j++) {
		var myLine = $('<tr />');
		for (var i = 0; i < nbCol; i++) {
			var myCol = $('<td />');
			var test = listeMots[k].libelle;
			myCol.html('<button type="button" class="btn btn-outline-primary btn-block">'
					+ test + '</button>');
			myLine.append(myCol);
			k=k+1;
		}
		monTableau.append(myLine);
	}

}

function getMots() {
	$.ajax({
		method : 'GET',
		url : 'http://192.168.1.110/codenames-ajax/carte',
		contentType : 'application/json',
		converters : {
			"text json" : jQuery.parseJSON
		},
		success : function(data) {
			console.log(data);
			letest(data);

		}
	});
}

function shuffle(a) {
    var j, x, i;
    for (i = a.length - 1; i > 0; i--) {
        j = Math.floor(Math.random() * (i + 1));
        x = a[i];
        a[i] = a[j];
        a[j] = x;
    }
    return a;
}


function sendMots() {
	
	var maListe=["wiry","awful","excited","illegal","curl","stroke","memory","cherries","threatening","actor","curly","blot","shy","donkey","ray","peep","cheap","oranges","doctor","faithful","energetic","degree","smoke","damage","malicious","rabid","press","bang","grin","wander","strip","needle","team","careless","ear","pastoral","fix","shop","earthy","lavish","flippant","hapless","debt","wise","wave","hug","replace","smell","outrageous","slow","fear","married","air","paltry","calm","unkempt","low","inquisitive","blow","cough","used","pail","dime","tiger","lumber","brief","cracker","evasive","excellent","giants","hypnotic","head","playground","watery","condition","repulsive","afford","request","secret","spotless","rose","sick","rainstorm","writing","wretched","hot","switch","toys","smell","auspicious","nippy","pigs","reign","symptomatic","contain","slope","flower","top","gaping","verdant","circle","silent","sour","lying","terrific","jumbled","concern","muddled","guess","popcorn","riddle","value","coal","twig","title","grey","tired","ghost","cook","useful","stamp","eye","greasy","momentous","jelly","frame","gaudy","anger","dysfunctional","aback","educated","suck","chicken","six","invite","grubby","handle","frog","wrist","clover","chess","perfect","pretty","mine","suffer","cough","yarn","ball","back","compare","unpack","detect","foamy","craven","scientific","imaginary","coil","wren","suit","man","enchanting","plain","cheese","rhyme","ugliest","reason","army","knowledge","clumsy","robin","deep","twist","helpful","safe","mundane","fade","extend","knowing","fretful","destruction","steer","icy","bell","ice","kittens","report","fearful","hobbies","division","reflective","fax","snakes","four","try","lake","delightful","cynical","quick","boot","fool"];
	
for(var i=0; i<maListe.length;i++){
	var monMot={
			
			"libelle":maListe[i],
	}
	
	$.ajax({
		method : 'POST',
		url : 'http://192.168.1.110/codenames-ajax/carte',
		contentType : 'application/json',
		data : JSON.stringify(monMot),
		
	});
	
};

}

function deleteMots() {
	
	
for(var i=206; i<806;i++){
	var monMot={
			
			"id":i,
	}
	
	$.ajax({
		method : 'DELETE',
		url : 'http://192.168.1.110/codenames-ajax/carte/'+i,
		contentType : 'application/json',
		data : JSON.stringify(monMot),
		
	});
	
};

}
