app.service("paqueteriaService",['$http', '$q','$window', function($http, $q,$window){
	this.crearPaquete = function(paqueteria) {
		var d = $q.defer();
		$http.post("envio/add", paqueteria).then(
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


app.controller("paqueteriaController",['$scope','$window', '$location', '$cookieStore','paqueteriaService',function($scope, $window, $location, $cookieStore, paqueteriaService){
	$scope.usuariologin="femers";
	$scope.sucursalname="Toluca Centro";
	$scope.CurrentDate = new Date();
$scope.EnviarFormulario = function() {
	//console.log($scope.form.pass.$valid);
//	$scope.validate();
	
	paqueteriaService.crearPaquete($scope.paqueteria).then(function(data) {
						var x = document.getElementById("snackbar")
					    x.className = "show";
						setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
					    setTimeout(function(){ if($scope.paqueteria){window.location="#/altaPaquete"; $window.location.reload(); } }, 3000);
//						
					})}

} ]);

