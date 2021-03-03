app.service("dashboardServices",['$http', '$q','$location', function($http, $q, $location){
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
	
}]);


app.controller("dashboardController",['$rootScope','$scope','$window', '$location', '$cookieStore','dashboardServices', function($rootScope,$scope, $window, $location, $cookieStore, dashboardServices){

	$scope.listAviones= dashboradData();
	
}]);