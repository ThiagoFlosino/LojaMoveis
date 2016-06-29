function trocaTipoComodo(){
	var comodo = document.getElementById("tipoComodo").value;
	document.getElementById("Modificado").value = comodo;
};

function listarMobilia(){
	document.getElementById('formMobilia').style.visibility = 'visible';
}
function mostarTabela(){
	if(document.getElementByName("visibilidade") == "false"){
		document.getElementById("tabela").style.visibility = 'visible';
	}
}
