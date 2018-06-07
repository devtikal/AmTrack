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
	


	
}]);


app.controller("sucursalController",['$scope','$window', '$location', '$cookieStore','sucursalService',function($scope, $window, $location, $cookieStore, sucursalService){


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
