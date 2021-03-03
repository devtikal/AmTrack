app.service("tareaServices",['$http', '$q','$location', function($http, $q, $location){
	
	
	this.altaTarea=function(data){
		var d = $q.defer();
		$http.post("/tarea/add",data).then(
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

app.controller("tareaController",['$rootScope','$scope','$window', '$location', '$cookieStore','componentesServices','$rootScope','tareaServices', function($rootScope,$scope, $window, $location, $cookieStore, componentesServices,$rootScope,tareaServices){

}]);
