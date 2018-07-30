app.service("welcomeService",['$http', '$q', function($http, $q){
	
} ]);


	

app.controller("welcomeController",['$rootScope', '$scope','$cookieStore', '$window', '$location', 'dhlService','sessionService',
	function($rootScope, $scope, $cookieStore, $window, $location, dhlService,sessionService){
	sessionService.isAuthenticated();
//	 $scope.showMaps=function(){
////		var url = "https://www.google.com/maps/embed/v1/place?key=AIzaSyCrlLXFntBgBODxEDBVV0C6aI59brxVy5A&q="+$cookieStore.get('sucursal_name')+$cookieStore.get('sucursal_dirs');
//		var url = 'https://www.google.com/maps/embed/v1/place?key=AIzaSyCrlLXFntBgBODxEDBVV0C6aI59brxVy5A&q=MERVEL EXPRESS Edo de Mexico'
//		console.log("Url", url)
//		
//		return url;
//	
//		  
//	 }
	$( document ).ready(function() {
		var nombre= $scope.quitaacentos($cookieStore.get('sucursal_name'));
		var ubicacion = $scope.quitaacentos($cookieStore.get('sucursal_dirs'));
		 var url = "https://www.google.com/maps/embed/v1/place?key=AIzaSyCrlLXFntBgBODxEDBVV0C6aI59brxVy5A&q="+nombre+" "+ubicacion;
		 console.log("Url", url)
		 document.getElementById("mapaMvl").src=url;
	});
	
	$scope.quitaacentos= function(s) {
		 if (s.normalize != undefined) {
		        s = s.normalize ("NFKD");
		    }
		    return s.replace (/[\u0300-\u036F]/g, "");
		}
			} ]);
