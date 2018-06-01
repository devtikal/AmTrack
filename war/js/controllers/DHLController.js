app.service("dhlService",['$http', '$q', function($http, $q){
	
} ]);


	

app.controller("DHLController",['$rootScope', '$scope','$cookieStore', '$window', '$location', 'dhlService',
	function($rootScope, $scope, $cookieStore, $window, $location, dhlService){
	$scope.page=null;
	$('html, body').animate({scrollTop:0}, 'slow');
	$scope.getPage = function(){
		
		
		$scope.value= document.getElementById("AWB").value;
		var url="http://www.dhl.com.mx/es/express/rastreo.html?brand=DHL&AWB=" + $scope.value;
		$scope.Page=url;
		
		console.log(url);
		var iframe = document.getElementById("objectx");
		
		iframe.setAttribute("data", url)
		console.log("Datos de la Pagina: ", $('object').contents().find('container'));
		setTimeout(function(){
//			var txt = iframe.document.getElementsByTagName("div")[49];
//			document.getElementById('contendDHL').value = txt;
//			console.log("dasada",txt);
			$scope.objeto = $("#objectx");
			console.log("Datos de la Pagina: ", $scope.objeto);
		}, 5000); 
		
		
//
//		 $('#contendDHL').load(frame );
	}

//	$( "iframe" ).change(function() {
//		  alert( "Handler for .change() called." );
//		  $scope.getPage();
//		});			
			} ]);
