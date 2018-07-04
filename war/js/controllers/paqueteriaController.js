app.service("paqueteriaService",['$http', '$q','$window', function($http, $q,$window){
	this.crearPaquete = function(user,paqueteria) {
		var d = $q.defer();
		$http.post("envio/add/"+user, paqueteria).then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==400){
					alert("No se puede crear "
							+ usuario.usuario + " usuario o correo no disponibles");
					}if(response.status==403){
						//alert("No tiene permiso de realizar esta acción");
//						$location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
	this.addVenta = function(user,venta) {
		var d = $q.defer();
		$http.post("venta/add/"+user, venta).then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==400){
					alert("No se puede crear "
							+ usuario.usuario + " usuario o correo no disponibles");
					}if(response.status==403){
						//alert("No tiene permiso de realizar esta acción");
//						$location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
	
	this.getVenta = function() {
		var d = $q.defer();
		$http.get("venta/findAll").then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==400){
					alert("No se puede crear "
							+ usuario.usuario + " usuario o correo no disponibles");
					}if(response.status==403){
						//alert("No tiene permiso de realizar esta acción");
//						$location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}

	this.getGuiaByName = function(user){
		var d = $q.defer();
		$http.get("guia/getGuia/"+user).then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==400){
					alert("No se puede crear "
							+ usuario.usuario + " usuario o correo no disponibles");
					}if(response.status==403){
						//alert("No tiene permiso de realizar esta acción");
//						$location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
	
	this.getEnvio = function(){
		var d = $q.defer();
		$http.get("envio/findAll").then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==400){
					alert("No se puede crear "
							+ usuario.usuario + " usuario o correo no disponibles");
					}if(response.status==403){
						//alert("No tiene permiso de realizar esta acción");
//						$location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
	
	this.getFolio = function(){
		var d = $q.defer();
		$http.get("venta/getFolio").then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==400){
					alert("No se puede crear "
							+ usuario.usuario + " usuario o correo no disponibles");
					}if(response.status==403){
						//alert("No tiene permiso de realizar esta acción");
//						$location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}

	
	this.makePDF = function(idEnvio,usuario){
		var d = $q.defer();
		$http.get("envio/generaTicket/"+idEnvio+"/"+usuario).then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==400){
					alert("No se puede crear "
							+ usuario.usuario + " usuario o correo no disponibles");
					}if(response.status==403){
						//alert("No tiene permiso de realizar esta acción");
//						$location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
}]);

app.controller("EnvioController",['$scope','$rootScope','$window', '$location', '$cookieStore','$cookies','paqueteriaService','sessionService',function($scope,$rootScope, $window, $location, $cookieStore,$cookies, paqueteriaService,sessionService){
	sessionService.isAuthenticated();
	
	$scope.venta={fecha: new Date()};
	paqueteriaService.getVenta().then(function(data) {
		$scope.ventas=data;
		console.log("List Ventas", $scope.ventas);
	});
	console.log($scope.venta.fecha);
//	paqueteriaService.getEnvio().then(function(data) {
//		$scope.envios=data;
//		console.log("Datos de Envio", $scope.envios);
//	});
	$scope.generaPDF = function (idEnvio){
		paqueteriaService.makePDF(idEnvio,$cookieStore.get('usuario')).then(function(data) {
			
		});
		
	}
	$scope.prePaquete = function(data){
		paqueteriaService.getGuiaByName($cookieStore.get('usuario')).then(function(data) {
			$scope.paquete.guia=data.numero;
	});
	}
	$scope.newVenta= function (){
		$scope.venta.cantidad=0;
		paqueteriaService.addVenta($cookieStore.get('usuario'), $scope.venta).then(function(data) {
			$window.location.reload();
		});
		
	}
	$scope.generarFolio = function(){
		
		paqueteriaService.getFolio().then(function(data) {
			$scope.venta.folio = data;
		});
	}
	$scope.savePaquete = function(data){
		console.log("Datos de Paquete", data);
		alert("La wea CMS");
	}
	
	$scope.products =[];
    $scope.addItem = function () {
        $scope.errortext = "";
        if (!$scope.addMe) {return;}
        if ($scope.products.indexOf($scope.addMe) == -1) {
//            $scope.products.push($scope.addMe,$scope.addMe2);
            $scope.products.push({descripcion:$scope.addMe, cantidad:$scope.addMe2});
            console.log($scope.products);
        } else {
            $scope.errortext = "Ya se encuentra en la lista.";
        }
        $scope.addMe="";
        $scope.addMe2="";
    }
    $scope.removeItem = function (x) {
        $scope.errortext = "";    
        $scope.products.splice(x, 1);
        console.log( $scope.products);
    }
	
	$scope.hide=true;
	$scope.requerido=true;
	$scope.isEstafeta = function() {
		if ($scope.paquete.empresa=="ESTAFETA" || $scope.paquete.empresa=="MERVEL"){
			$scope.hide=false;
			$scope.requerido=false;
			document.getElementById("delTap").classList.add("tab");
		}else{
			$scope.hide=true;
			$scope.requerido=true;
			document.getElementById("delTap").classList.remove("tab");
		}
		
		
	}
} ]);
app.controller("paqueteriaController",['$scope','$rootScope','$window', '$location', '$cookieStore','$cookies','paqueteriaService','sessionService',function($scope,$rootScope, $window, $location, $cookieStore,$cookies, paqueteriaService,sessionService){
	sessionService.isAuthenticated();
	$scope.paqueteria={guia:null};
	paqueteriaService.getGuiaByName($cookieStore.get('usuario')).then(function(data) {
		$scope.paqueteria.guia=data.numero;
	});
	$scope.products =[];
    $scope.addItem = function () {
        $scope.errortext = "";
        if (!$scope.addMe) {return;}
        if ($scope.products.indexOf($scope.addMe) == -1) {
//            $scope.products.push($scope.addMe,$scope.addMe2);
            $scope.products.push({descripcion:$scope.addMe, cantidad:$scope.addMe2});
            console.log($scope.products);
        } else {
            $scope.errortext = "Ya se encuentra en la lista.";
        }
        $scope.addMe="";
        $scope.addMe2="";
    }
    $scope.removeItem = function (x) {
        $scope.errortext = "";    
        $scope.products.splice(x, 1);
        console.log( $scope.products);
    }
	
	$scope.paqueteria.fecha = new Date();
	$scope.hide=true;
	$scope.requerido=true;
	$scope.isEstafeta = function() {
		if ($scope.paqueteria.empresa=="ESTAFETA" || $scope.paqueteria.empresa=="MERVEL"){
			$scope.hide=false;
			$scope.requerido=false;
		}else{
			$scope.hide=true;
			$scope.requerido=true;
			
		}
		
		
	}
	
$scope.EnviarFormulario = function() {
	//console.log($scope.form.pass.$valid);
//	$scope.validate();
	$scope.paqueteria.materiales=$scope.products;
	paqueteriaService.crearPaquete($cookieStore.get('usuario'),$scope.paqueteria).then(function(data) {
						var x = document.getElementById("snackbar")
					    x.className = "show";
						setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
					    setTimeout(function(){ if($scope.paqueteria){window.location="#/altaPaquete"; $window.location.reload(); } }, 3000);
//						
					})}





} ]);

