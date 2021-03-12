app.service("tareaServices",['$http', '$q','$location', function($http, $q, $location){
	
	
	this.altaTarea=function(data){
		var d = $q.defer();
		$http.post("/tarea/add",data).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				checkResponse(response);
			});
		return d.promise;
	}
	this.getListaTareasByComponents = function(id){
		var d = $q.defer();
		$http.get("/tarea/findAll/"+id).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				checkResponse(response);
			});
		return d.promise;
	}
	this.eliminaTarea = function(id){
		var d = $q.defer();
		$http.post("/tarea/delete/"+id).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				checkResponse(response);
			});
		return d.promise;
	}
	this.actualizaTarea = function(tarea){
		var d = $q.defer();
		$http.post("/tarea/update",tarea).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				checkResponse(response);
			});
		return d.promise;
	}
	
}]);

app.controller("tareaController",['$rootScope','$scope','$window', '$location', '$cookieStore','componentesServices','$rootScope','tareaServices', function($rootScope,$scope, $window, $location, $cookieStore, componentesServices,$rootScope,tareaServices){

}]);
