var app=angular.module("app",['ngRoute', 'ngCookies',]);
app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/avion/alta', {
		templateUrl : "pages/aviones/alta.html",
		controller : "avionesAltaController"
	});
	$routeProvider.when('/avion/lista', {
		templateUrl : "pages/aviones/lista.html",
		controller : "avionesListaController"
	});
	$routeProvider.otherwise({
		redirectTo : '/',
		templateUrl : "pages/inicio.html",
		controller : "avionesAltaController"
	});
	
}]);

app.run(['$rootScope','$http','$cookieStore',function ($rootScope,$http,$cookieStore) {
	
}]);