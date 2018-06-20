app.service("guiaService",['$http', '$q','$window', function($http, $q,$window){
	this.addGuia = function(number1,number2) {
		var d = $q.defer();
		$http.get("guia/addM/"+number1+"/"+number2).then(
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
	
	this.getGuia = function() {
		var d = $q.defer();
		$http.get("guia/findAll/").then(
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


app.controller("guiaController",['$scope','$rootScope','$window', '$location', '$cookieStore','$cookies','guiaService','sessionService',function($scope,$rootScope, $window, $location, $cookieStore,$cookies, guiaService,sessionService){
	sessionService.isAuthenticated();
//	$cookieStore.get('usuario');
	guiaService.getGuia().then(function(data) {
		$scope.guias=data;
	});
	$scope.addguias = function(dato1,dato2) {
		
		guiaService.addGuia(dato1,dato2).then(function(data) {
			alert("Guias Agregadas correctamente");
			$window.location.reload();
		});
	}
	


} ]);

