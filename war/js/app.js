var app=angular.module("app",['ngRoute', 'ngCookies',]);
app.config([ '$routeProvider','$httpProvider', function($routeProvider,$httpProvider) {
	$httpProvider.defaults.headers.common = {'Access-Control-Allow-Origin': '*', 'Access-Control-Allow-Method': 'GET'};
    $httpProvider.defaults.headers.post = {};
    $httpProvider.defaults.headers.put = {};
    $httpProvider.defaults.headers.patch = {};
    $httpProvider.defaults.useXDomain = true;
    $httpProvider.defaults.withCredentials = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
    $httpProvider.defaults.headers.common["Accept"] = "application/json";
    $httpProvider.defaults.headers.common["Content-Type"] = "application/json";
	$routeProvider.when('/login', {
		templateUrl : "pages/login.html",
		controller : "navigation"
	});
	$routeProvider.when('/welcome', {
		templateUrl : "pages/welcome.html",
		controller : "navigation"
	});
	$routeProvider.when('/dhl', {
		templateUrl : "pages/dhl.html",
		controller : "DHLController"
	});
	$routeProvider.when('/fedex', {
		templateUrl : "pages/fedex.html",
		controller : "navigation"
	});
	$routeProvider.when('/Sucursal', {
		templateUrl : "pages/sucursal/navbar.html",
		controller : "navigation"
	});
	$routeProvider.when('/AltaUsuario', {
		templateUrl : "pages/altaUsuario.html",
		controller : "userController"
	});
	$routeProvider.when('/AltaSucursal', {
		templateUrl : "pages/altaSucursal.html",
		controller : "sucursalController"
	});
	$routeProvider.when('/altaPaquete', {
		templateUrl : "pages/AltaPaquete.html",
		controller : "paqueteriaController"
	});
	$routeProvider.when('/', {
		templateUrl : "pages/welcome.html",
		controller : "navigation"
	});
//	$routeProvider.otherwise({
//		redirectTo : '/NoFound',
//		templateUrl : "pages/noPage.html",
//		controller : "OTsListController"
//	});
}]);

app.factory("userFactory", function(){
	var usuarioFirmado={usuario:"", pass:"",perfil:""};
	
	    var respuesta={
	    	getUsuarioFirmado: function(){
	            return usuarioFirmado;
	        },
	        setUsuarioFirmado: function(user){
	        	usuarioFirmado = user;
	        },
	        getUsuarioPerfil: function(){
	            return usuarioFirmado.perfil;
	        }
	    }
	return respuesta;
});

app.service('sessionService', [
	'$rootScope',
	'$http',
	'$location',
	'$q',
	'userFactory',
	function($rootScope, $http, $location, $q,userFactory) {
		this.authenticate = function(credentials, callback) {

			var headers = credentials ? {
				authorization : "Basic"
						+ btoa(credentials.username + ":"
								+ credentials.password)
			} : {};
			$http.get('/user', {
				headers : headers
			}).success(function(data) {
				userFactory.setUsuarioFirmado(data);
				if (data.usuario) {
					$rootScope.authenticated = true;
					$rootScope.variable = true;
//					$http.get("/notificacion/numAlertas/"+ data.id).then(function(response){
//						$rootScope.numNotificaciones=response.data;
//					})
					$location.path("/");
				} else {
					$rootScope.authenticated = false;
				}
			}).error(function(data) {
				alert("Usuario o Contraseña incorrectos");
				$location.path("/login");
			});
		}
		this.reset=function(data){
			var d = $q.defer();
			$http.post("/usuario/reset/",data).then(
				function(response) {
					d.resolve(response.data);
				});
			return d.promise;
		}
		this.isAuthenticated = function() {
			var d = $q.defer();
			$http.get("currentSession").success(function(data) {
				$rootScope.authenticated = true;
				d.resolve(data);
			}).error(function(data) {
				$location.path("/login");
			});
			return d.promise;
		}
} ]);

app.controller('navigation', [ 'sessionService','$window', '$rootScope', '$scope','$http', '$location','userFactory',
	function(sessionService, $rootScope, $scope, $http, $location,userFactory) {
		$scope.credentials = {};
		$scope.login = function() {
			$scope.credentials.idSucursal=9999;
			sessionService.authenticate($scope.credentials, function() {
				if ($rootScope.authenticated) {
					$scope.error = false;
					$location.path("/");
				} else {
					$location.path("/login");
					$scope.error = true;
				}
			});
		};
		$('html, body').animate({scrollTop:0}, 'slow');
		$scope.restablecer=function(email){
			sessionService.reset(email).then(function(data){
			
					alert("Correo enviado correctamenta para restablecer su contrase\u00f1a");
					location.reload();
					
		//			setTimeout(window.location.reload.bind(window.location), 1000);
				
			
		});}
} ]);

app.run(['$rootScope','$http','sessionService','userFactory',function ($rootScope,$http,sessionService,userFactory) {
	sessionService.isAuthenticated().then(function(data){
		var us= data;
		userFactory.setUsuarioFirmado(us);
		$rootScope.perfilUsuario=userFactory.getUsuarioPerfil();
		$http.get("/notificacion/numAlertas/"+ us.id).then(function(response){
			$rootScope.numNotificaciones=response.data;
		})
	});
}]);