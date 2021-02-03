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
	this.actualizaAvion=function(data){
		var d = $q.defer();
		$http.post("/aeronave/update/",data).then(
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
	$scope.avion = {
		matricula:null,
		marca:null,
		modelo:null,
		numeroSerie:null,
		aterrizaje:null,
		tiempovuelo:null,
		planeador:null,
		motor1:null,
		motor2:null,
		marcas:null,
		nacionalidad:null
	}
	

	
	$scope.guardaAvion = function(data){
		avionesServices.altaAvion(data).then(function(response){
			window.location.reload();
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
	$scope.editaAvion = function(data){
		avionesServices.actualizaAvion(data).then(function(){
			alert("Se ha actualizacon correctamente")
			$('#mdlEdita').modal('toggle')
//			window.location.reload();
		});
	}
	
		$scope.getAll();
				
		$scope.ASelect= function(data){
			$scope.avionSeleccionado = data;
		}
			
			
}]);