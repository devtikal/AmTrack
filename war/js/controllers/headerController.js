app.directive('submenu',function(){
	return{
		restrict: "AE",
		scope: {perfil: '=perfil'},
		templateUrl:'pages/menuTemplate.html'
	};
});

app.controller('headerController',['$scope','$rootScope','$location','$http','$cookieStore','userFactory','sessionService',function($scope,$rootScope,$location,$http,$cookieStore,userFactory,sessionService){
	sessionService.isAuthenticated();
	$scope.CerrarSesion = function(){
		$http.get("/usuario/cerrarSesion").then(function(response) {
			$rootScope.variable = false;
			$location.path("/login");
			location.reload(true);
		}, function(response) {
			$location.path("/login");
			location.reload(true);;
		});
	};
	
//	$http.get("/usuario/check").then(function(response){
//		$rootScope.variable = true;
//	},function(response){
//		if(response.status==403){
//			$rootScope.variable = false;
//			$scope.empresas={};
//		}
//		$location.path("/login");
//	});
}]);