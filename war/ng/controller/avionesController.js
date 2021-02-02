app.service("avionesServices",['$http', '$q','$location', function($http, $q, $location){
	this.altaAvion=function(data){
		var d = $q.defer();
		$http.post("/aeronave/add",data).then(
			function(response) {
				console.log(response);
				d.resolve(response.data);
			}, function(response) {
				if(response.status==403){
					alert("No esta autorizado para realizar esta accion");
					$location.path("/");
				}
			});
		return d.promise;
	}
	this.listaAvion=function(){
		var d = $q.defer();
		$http.get("/aeronave/findAll/").then(
			function(response) {
				console.log(response);
				d.resolve(response.data);
			}, function(response) {
				if(response.status==403){
					alert("No esta autorizado para realizar esta accion");
					$location.path("/");
				}
			});
		return d.promise;
	}
	this.borraAvion=function(id){
		var d = $q.defer();
		$http.post("/aeronave/delete/"+id).then(
			function(response) {
				console.log(response);
				d.resolve(response.data);
			}, function(response) {
				if(response.status==403){
					alert("No esta autorizado para realizar esta accion");
					$location.path("/");
				}
			});
		return d.promise;
	}
	this.actualizaAvion=function(){
		var d = $q.defer();
		$http.post("/aeronave/update/").then(
			function(response) {
				console.log(response);
				d.resolve(response.data);
			}, function(response) {
				if(response.status==403){
					alert("No esta autorizado para realizar esta accion");
					$location.path("/");
				}
			});
		return d.promise;
	}
	
}]);
app.controller("avionesAltaController",['$rootScope','$scope','$window', '$location', '$cookieStore','avionesServices','$rootScope', function($rootScope,$scope, $window, $location, $cookieStore, avionesServices,$rootScope){
//	$scope.avion = {
//		numeroAeronave:null,
//		matricula:null,
//		marca:null,
//		modelo:null,
//		numeroSerie:null,
//		aterrizaje:null,
//		tiempovuelo:null,
//		planeador:null,
//		motor1:null,
//		motor2:null,
//		marcas:null,
//		nacionalidad:null
//	}
	
	$scope.avion = {
			numeroAeronave:"10",
			matricula:"AOL-27163",
			marca:"MKD2",
			modelo:"Md1",
			numeroSerie:"45567887654",
			aterrizaje:"612",
			tiempovuelo:"45679",
			planeador:"23456",
			motor1:"344443458",
			motor2:"987654343",
			marcas:"23453",
			nacionalidad:"MX"
		}
	
	$scope.guardaAvion = function(data){
		avionesServices.altaAvion(data).then(function(response){
			console.log("Respuesta del servidor " + response );
		});
	}
			
	$( window ).on( "load", function() {
		
				
	});
			
			
}]);




app.controller("avionesListaController",['$rootScope','$scope','$window', '$location', '$cookieStore','avionesServices','$rootScope', function($rootScope,$scope, $window, $location, $cookieStore, avionesServices,$rootScope){

	
	
	$scope.getAll = function(){
		avionesServices.listaAvion().then(function(data){
			$scope.listaAviones = data;
		})
	}
	
	$scope.EliminaAeronave = function(){
		avionesServices.borraAvion($scope.avionSeleccionado.id).then(function(data){
			$scope.getAll();
			setTimeout(() => {
				$('#mdlDelete').modal('toggle')
			}, 500);
			
		})
	}
	
		$scope.getAll();
				
		$scope.ASelect= function(data){
			$scope.avionSeleccionado = data;
		}
			
			
}]);