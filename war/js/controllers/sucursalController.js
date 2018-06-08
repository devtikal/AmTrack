app.service("sucursalService",['$http', '$q','$window', function($http, $q,$window){
	this.crearSucursal = function(sucursal) {
		var d = $q.defer();
		$http.post("sucursal/add", sucursal).then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==400){
					alert("No se puede crear "
							+ usuario.usuario + " usuario o correo no disponibles");
					}if(response.status==403){
						//alert("No tiene permiso de realizar esta acción");
//						$location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
	this.getSucursal = function(id) {
		var d = $q.defer();
		$http.get("/sucursal/find/"+id).then(
			function(response) {
				d.resolve(response.data);
			});
		return d.promise;
	}
	
	this.getAllSucursal = function() {
		var d = $q.defer();
		$http.get("/sucursal/findAll/").then(
			function(response) {
				d.resolve(response.data);
			});
		return d.promise;
	}

	
}]);

app.controller("getSucursalController",['$scope','$rootScope','$window', '$location', '$cookieStore','sucursalService','sessionService',function($scope,$rootScope, $window, $location, $cookieStore, sucursalService,sessionService){
	sessionService.isAuthenticated();
	 $scope.idSuc = $cookieStore.get('idSucursal');
	sucursalService.getSucursal( $scope.idSuc).then(function(data) {
		$scope.sucursalData=data;
		$scope.SucursalName=data.nombre;
	
		console.log("La Sucursal",$scope.sucursalData);
	})
} ]);	
app.controller("sucursalController",['$scope','$rootScope','$window', '$location', '$cookieStore','sucursalService','sessionService',function($scope,$rootScope, $window, $location, $cookieStore, sucursalService,sessionService){
	sessionService.isAuthenticated();

$scope.EnviarFormulario = function() {
	//console.log($scope.form.pass.$valid);
//	$scope.validate();
	
	sucursalService.crearSucursal($scope.sucursal).then(function(data) {
						var x = document.getElementById("snackbar")
					    x.className = "show";
						setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
					    setTimeout(function(){ if($scope.sucursal){window.location="#/AltaSucursal"; $window.location.reload(); } }, 3000);
//						
					})}

	

} ]);

