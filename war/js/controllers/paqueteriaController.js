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

	this.AddEnvio = function(idVenta,usuario,datos){
		var d = $q.defer();
		$http.post("envio/add/"+idVenta+"/"+usuario,datos).then(
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
		$http.get("venta/generaTicket/"+idEnvio+"/"+usuario).then(
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
	
	this.getEnvioxVenta = function(idventa){
		var d = $q.defer();
		$http.get("envio/getEnviosxVenta/"+idventa).then(
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
	
	this.eliminarVenta = function(idventa){
		var d = $q.defer();
		$http.get("venta/cancelar/"+idventa).then(
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
	
	this.cancelarEnvio = function(idEnvio,idVenta){
		var d = $q.defer();
		$http.get("envio/delete/"+idEnvio+"/"+idVenta).then(
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
	$scope.paquete={guia:null};
	$scope.venta={fecha: new Date()};
	$scope.idVenta=null;
	paqueteriaService.getVenta().then(function(data) {
		$scope.ventas=data;
		$scope.usuario=$cookieStore.get('usuario');
		console.log("List Ventas", $scope.ventas, "Usuario",$scope.usuario);
	});
	$('.datepicker').datepicker({format: 'mm-dd-yyyy '});
	
	$('.input-daterange').datepicker({
	    format: "mm-dd-yyyy"
	});
	
	$('.input-daterange input').each(function() {
	    $(this).datepicker("format","mm-dd-yyyy");
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
	$scope.idVenta=null;
	$scope.ver = function(id){
		$scope.ventaInf = id;
		$scope.idVenta = id.id;
		paqueteriaService.getEnvioxVenta(id.id).then(function(data) {
			$scope.envios = data;
			
			console.log("Envios ",$scope.envios, );
			
			$("#modalEnvios").modal();
	});
	}
	$scope.guardarEnvio=function(){
		paqueteriaService.AddEnvio($scope.idVenta,$cookieStore.get('usuario'),$scope.paquete).then(function(data) {
			alert("Se ha guardado el Envio");
			$window.location.reload();
	});
	}
	
	$scope.prePaquete = function(inf){
		$scope.idVenta=inf.id;
		paqueteriaService.getGuiaByName($cookieStore.get('usuario')).then(function(data) {
			console.log("La Guia",inf);
			$scope.paquete.guia=data.numero;
			
			if(!$scope.paquete.guia){
				 var r = confirm("No hay Guias en la Sucursal \n <" + $cookieStore.get('sucursal') + ">\n Click en Aceptar para Agregar o Asignar Guias");
				    if (r == true) {
				    	
				    	$location.path("/guia");
				    }   	
				    
				}else{
					$("#modalEnvio").modal();
				}
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
		
		$scope.guardarEnvio();
	}
	
	$scope.products =[];
    $scope.addItem = function () {
        $scope.errortext = "";
        if (!$scope.addMe) {return;}
        if ($scope.products.indexOf($scope.addMe) == -1) {
//            $scope.products.push($scope.addMe,$scope.addMe2);
            $scope.products.push({descripcion:$scope.addMe, cantidad:$scope.addMe2});
            console.log($scope.products);
            $scope.paquete.materiales=$scope.products;
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
	$scope.showPDF = function(id){
		$scope.url = "venta/generaTicket/"+id+"/"+$scope.usuario;
		$("#modalPDF").modal();
	}
	$scope.showPDFM = function(id){
		$scope.url = "envio/generaGuiaMervel/"+id+"/"+$scope.idVenta+"/"+$scope.usuario;
		$("#modalPDF").modal();
	}
	$scope.eliminarVenta = function(datos){
		 var r = confirm("Click en Aceptar para Eliminar la Venta. \n" + datos.folio);
		    if (r == true) {
		    	paqueteriaService.eliminarVenta(datos.id).then(function(data) {
					$window.location.reload();
				});
		    }   	
		    
		}
	$scope.eliminarEnvio = function(datos){
		 var r = confirm("Click en Aceptar para Eliminar el envio. \n Guia: " + datos.guia + "\n Rastro: " + datos.rastreo);
		    if (r == true) {
		    	paqueteriaService.cancelarEnvio(datos.id,$scope.idVenta).then(function(data) {
					$window.location.reload();
				});
		    }   	
		    
		}	
	
} ]);
app.controller("paqueteriaController",['$scope','$rootScope','$window', '$location', '$cookieStore','$cookies','paqueteriaService','sessionService',function($scope,$rootScope, $window, $location, $cookieStore,$cookies, paqueteriaService,sessionService){
	sessionService.isAuthenticated();
	$scope.usuario=$cookieStore.get('usuario');
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

