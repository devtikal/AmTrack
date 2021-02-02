var app=angular.module("app",['ngRoute', 'ngCookies',]);
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
	'$cookieStore',
	function($rootScope, $http, $location, $q,userFactory,$cookieStore) {
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
					Cookies.set('usuario',data);
					$rootScope.variable = true;
					$cookieStore.put("userPerfil",data.perfil);
					
					window.location.href="../"
					
				} else {
					window.location.href="../"
				}
			}).error(function(data) {
				alert("Usuario o Contraseña incorrectos");
				$location.path("/");
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
			$http.get("/currentSession").success(function(data) {
				$rootScope.authenticated = true;
				d.resolve(data);
			}).error(function(data) {
				Cookies.remove('usuario');
			});
			return d.promise;
		}
		this.cerrarSesion = function() {
			var d = $q.defer();
			$http.get("/usuario/cerrarSesion").success(function(data) {
				d.resolve(data);
				window.location.href="/";
			}).error(function(data) {
				//$location.path("/login");
			});
			return d.promise;
		}
} ]);

app.controller('navigation', [ 'sessionService','$window', '$rootScope', '$scope','$http', '$location','userFactory',
	function(sessionService, $rootScope, $scope, $http, $location,userFactory) {
		$scope.credentials = {};
		$scope.login = function() {
			sessionService.authenticate($scope.credentials, function(data) {
				if ($rootScope.authenticated) {
					$scope.error = false;
//					
					window.location.href="../"
				} else {
					$location.path("/");
					$scope.error = true;
				}
			});
		};
		$scope.CerrarSesion = function(){
			sessionService.cerrarSesion().then(function(response) {
				$rootScope.variable = false;
				
			
			});
		};
		$scope.restablecer=function(email){
			sessionService.reset(email).then(function(data){
			
					alert("Correo enviado correctamenta para restablecer su contrase\u00f1a");
					location.reload();		
			
		});}
} ]);
app.run(['$rootScope','$http','sessionService','userFactory','$cookieStore','$location',function ($rootScope,$http,sessionService,userFactory,$cookieStore,$location) {
	   
	sessionService.isAuthenticated().then(function(data){
		var us= data;
		var usr = JSON.parse(Cookies.get('usuario'))
		if(usr.usuario){
			console.log("Existe Una Sesion en Cookies");
			window.location.href="../"
		}else{
			console.log("No existe Una Sesion en Cookies");
		}
		userFactory.setUsuarioFirmado(us);
		

	});
}]);