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
	$routeProvider.when('/componente/alta', {
		templateUrl : "pages/componentes/alta.html",
		controller : "componentesAltaController"
	});
	$routeProvider.when('/componente/lista', {
		templateUrl : "pages/componentes/lista.html",
		controller : "componentesListaController"
	});
	$routeProvider.otherwise({
		redirectTo : '/',
		templateUrl : "pages/inicio.html",
		controller : "avionesAltaController"
	});
	
}]);
app.controller('navigation', [ '$window', '$rootScope', '$scope','$http', '$location',
	function( $window,$rootScope, $scope, $http, $location) {
		
		$scope.cerrarSesion = function(){
			$http.get("/usuario/cerrarSesion").success(function(data) {
				window.location.href="/login/"
			})
		};
} ]);
app.run(['$rootScope','$http','$cookieStore',function ($rootScope,$http,$cookieStore) {
	$http.get("/currentSession").success(function() {
		$rootScope.usr = JSON.parse(Cookies.get('usuario'))
	}).error(function(data) {
	    Cookies.remove('usuario');
	    window.location.href="/login/"
	});
	
}]);