

function catalogoPquete (){
	var catPaq = [
	{id:1, tipo:"BOLSA"},
	{id:2,tipo:"CAJA"},
	{id:3,tipo:"IRREGULAR"},
	{id:4,tipo:"SOBRE"}
	]
	return catPaq;
}
function catalogoPqueteFedex (){
	var catPaq = [
	{id:1,tipo: "Pak"},
	{id:2,tipo: "Box Small"},
	{id:3,tipo: "Box Medium"},
	{id:4,tipo: "Box Large"},
	{id:4,tipo: "Caja"}
	]
	return catPaq;
}


function formatoFecha(fecha){
	if(fecha){
		var date = new Date(fecha);
		var result= date.getDate()+"-"+(date.getMonth()+1)+"-"+date.getFullYear();
		return result;
	}
}
function redondea(valor){
	 var aux= valor;
		aux= aux.toFixed(4);
		return aux;
 }

function blurFunction(mont) {

	var Num = numeral(mont).format('0,0.00');
	console.log(Num);
	return Num;
}

