app.service("componentesServices",['$http', '$q','$location', function($http, $q, $location){
	this.altaComponente=function(data){
		var d = $q.defer();
		$http.post("/componente/add",data).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				if(response.status==403){
					alert("No esta autorizado para realizar esta accion");
				}
			});
		return d.promise;
	}
	
	this.listaComponente=function(){
		var d = $q.defer();
		$http.get("/componente/findAll").then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				if(response.status==403){
					alert("No esta autorizado para realizar esta accion");
				}
				if(response.status!=403){
					alert("No se ha podido procesar la solicitud");
				}
			});
		return d.promise;
	}
	
	this.actualizaComponente=function(){
		var d = $q.defer();
		$http.post("/componente/update").then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				if(response.status==403){
					alert("No esta autorizado para realizar esta accion");
				}
				if(response.status!=403){
					alert("No se ha podido procesar la solicitud");
				}
			});
		return d.promise;
	}
	
	this.eliminaComponente=function(id){
		var d = $q.defer();
		$http.get("/componente/delete/"+id).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				if(response.status==403){
					alert("No esta autorizado para realizar esta accion");
				}
				if(response.status!=403){
					alert("No se ha podido procesar la solicitud");
				}
			});
		return d.promise;
	}
	
}]);


app.controller("componentesListaController",['$rootScope','$scope','$window', '$location', '$cookieStore','componentesServices','$rootScope', function($rootScope,$scope, $window, $location, $cookieStore, componentesServices,$rootScope){

	$scope.getListaComponentes = function(){
		componentesServices.listaComponente().then(function(data){
			$scope.listaComponentes = data
		});
	}
	
	$scope.EComponente = function(data){
		data.fechaApertura = new Date(data.fechaApertura)
		$scope.componente = data;
	}
	$scope.editaComponente = function(data){
		componentesServices.actualizaComponente(data).then(function(resp){
			alert("Se ha actualizacon correctamente")
			window.location.reload();
		});
	}
	$scope.onEliminarComponente = function(data){
		componentesServices.eliminaComponente(data.id).then(function(){
			alert("Se ha eliminado correctamente")
		});
	}
			
	$scope.getListaComponentes();		
}]);


app.controller("componentesAltaController",['$rootScope','$scope','$window', '$location', '$cookieStore','componentesServices','$rootScope', function($rootScope,$scope, $window, $location, $cookieStore, componentesServices,$rootScope){

	$scope.componente = {
			nombre:null,
			claveInterna:null,
			claveManual:null,
			noSerie:null,
			cantidad:null,
			pendientes:null,
			fechaApertura:null,
			idCategoria:null,
			idUnidad:null,
			idCondicion:null,
			maximo:null,
			minimo:null,
			anaquel:null,
			repisa:null,
			imagen:null,
			certificado8130:false,
			modeloAeronave:null,
			marcaAeronave:null,
			observaciones:null
}
	$scope.guardaComponente = function(data){
		componentesServices.altaComponente(data).then(function(){
			alert("Se ha registrado correctamente el componente")
			window.location.reload();
			
		});
	}
	
			
			
}]);