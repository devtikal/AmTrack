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
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
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
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
	
	this.getVenta = function(p) {
		var d = $q.defer();
		$http.get("venta/findAllP/"+p).then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==400){
					alert("No se puede crear "
							+ usuario.usuario + " usuario o correo no disponibles");
					}if(response.status==403){
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
	this.getVentaByUser = function(userName,p) {
		var d = $q.defer();
		$http.get(" venta/findAllPage/"+userName+"/"+p).then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==400){
					alert("No se puede crear "
							+ usuario.usuario + " usuario o correo no disponibles");
					}if(response.status==403){
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
	this.liberaGuias = function(noGuia) {
		var d = $q.defer();
		$http.get("guia/liberarGuia/"+noGuia).then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==400){
					alert("No se puede crear "
							+ usuario.usuario + " usuario o correo no disponibles");
					}if(response.status==403){
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
	document.addEventListener('keyup', event => {
		  if (event.ctrlKey && event.shiftKey && event.keyCode === 40) {
			  var win = window.open("https://www.google.com", '_blank');
			  win.focus();
		  }
		}, false)
	this.getGuiaByName = function(tipo,empresa,user){
		var d = $q.defer();
		$http.get("guia/getGuia/"+tipo+"/"+empresa+"/"+user).then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==400){
					alert("No se puede crear "
							+ usuario.usuario + " usuario o correo no disponibles");
					}
					if(response.status==500){
						alert("   Error en Obtener la Guia \n Verifique si hay guias con el Kilataje Seleccionado");
						}
					if(response.status==403){
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
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
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
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
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
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
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
	this.factura = function(idVenta,datos){
		var d = $q.defer();
		$http.post("/factura/facturar/"+idVenta,datos).then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==400){
					alert("No se puede crear "
							+ usuario.usuario + " usuario o correo no disponibles");
					}if(response.status==403){
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
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
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
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
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
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
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
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
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
	this.getCP = function(cp){
		var d = $q.defer();
		$http.get("https://api-codigos-postales.herokuapp.com/v2/codigo_postal/"+cp).then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==403){
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
	this.cancelFact = function(idVenta){
		var d = $q.defer();
		$http.post("factura/cancelarAck/"+idVenta).then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==403){
						
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
	this.buscaCliente = function(name){
		var d = $q.defer();
		$http.get("persona/getByNombre/"+name).then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==403){
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
	
	this.getPages = function(){
		var d = $q.defer();
		$http.get("venta/numPaginas").then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==403){
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
	this.getPagesBySuc = function(idSucursal){
		var d = $q.defer();
		$http.get("venta/numPaginasSuc/"+idSucursal).then(
				function(response) {
					console.log(response);
					d.resolve(response.data);
				},
				function(response) {
					if(response.status==403){
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
}]);

app.controller("EnvioController",['$scope','$rootScope','$window', '$location', '$cookieStore','$cookies','paqueteriaService','sessionService','sucursalService',function($scope,$rootScope, $window, $location, $cookieStore,$cookies, paqueteriaService,sessionService,sucursalService){
	sessionService.isAuthenticated();
	$scope.perfil = $cookieStore.get('perfil');
	$scope.paquete={guia:null};
	$scope.venta={fecha: new Date(),cantidad: 0 };
	$scope.idVenta=null;
	//$scope.venta={cantidad:0};
	$scope.paginaActual=1;
	sucursalService.getAllSucursal().then(function(data) {
		$scope.sucursal = data;
		
	});
	$scope.loadVentas = function(page){
	if($scope.perfil == "Administrador" || $scope.perfil =="SuperAdministrador"){
		paqueteriaService.getPages().then(function(data) {
			$scope.maxPage=data;
			$scope.llenarPags();
		});
		paqueteriaService.getVenta(page).then(function(data) {
			$scope.ventas=data;
			$scope.usuario=$cookieStore.get('usuario');
			$scope.llenarPags();
//			console.log("List Ventas", $scope.ventas, "Usuario",$scope.usuario);
		});
	}else{
		paqueteriaService.getPagesBySuc($cookieStore.get('idSucursal')).then(function(data) {
			$scope.maxPage=data;
			$scope.llenarPags();
		});
	paqueteriaService.getVentaByUser($cookieStore.get('usuario'),page).then(function(data) {
		$scope.ventas=data;
		$scope.usuario=$cookieStore.get('usuario');
		$scope.llenarPags();
//		console.log("List Ventas", $scope.ventas, "Usuario",$scope.usuario);
	});
	}
	}
	$scope.loadVentas(1);
	
	$scope.cargarPagina=function(pag){
		if($scope.paginaActual!=pag){
			$scope.paginaActual=pag;
			$scope.loadVentas(pag);
		}
	}
	$scope.llenarPags=function(){
		var inicio=0;
		if($scope.paginaActual>5){
			inicio=$scope.paginaActual-5;
		}
		var fin = inicio+9;
		if(fin>$scope.maxPage){
			fin=$scope.maxPage;
		}
		$scope.paginas=[];
		for(var i = inicio; i< fin; i++){
			$scope.paginas.push(i+1);
		}
		for(var i = inicio; i<= fin; i++){
			$('#pagA'+i).removeClass("active");
			$('#pagB'+i).removeClass("active");
		}
		$('#pagA'+$scope.paginaActual).addClass("active");
		$('#pagB'+$scope.paginaActual).addClass("active");
	}
	
	
	$('.datepicker').datepicker({format: 'mm-dd-yyyy '});
	
	$('.input-daterange').datepicker({
	    format: "mm-dd-yyyy"
	});
	
	$('.input-daterange input').each(function() {
	    $(this).datepicker("format","mm-dd-yyyy");
	});
	
	console.log($scope.venta.fecha);
// paqueteriaService.getEnvio().then(function(data) {
// $scope.envios=data;
// console.log("Datos de Envio", $scope.envios);
// });
	$scope.generaPDF = function (idEnvio){
		paqueteriaService.makePDF(idEnvio,$cookieStore.get('usuario')).then(function(data) {
			
		});
		
	}
	$scope.disContinuar=false;
	$scope.validaPeso = function(){
		
		var PesoVol = (($scope.paquete.paquete.largo*$scope.paquete.paquete.ancho*$scope.paquete.paquete.alto)/5000);
		$scope.pesoVol = PesoVol;
		$scope.pesoVol=redondea($scope.pesoVol) * 1;
		var valor = 0;
		if($scope.paquete.paquete.largo && $scope.paquete.paquete.ancho && $scope.paquete.paquete.alto){
			switch ($scope.paquete.tipoGuia) {
		    case "1Kg Sobre Sig Dia":
		    	valor = 1;
		    	if(PesoVol > valor){
		    		alert("El Peso Volumetrio sobre pasa al Kilataje");
		    		$scope.disContinuar=true;
		    	}else{
		    		$scope.disContinuar=false;
		    	}
		    	
		        break;
			 case "1Kg Paquete Sig Dia":
				 valor = 1;
			    	if(PesoVol > valor){
			    		alert("El Peso Volumetrio sobre pasa al Kilataje");
			    	}  else{
			    		$scope.disContinuar=false;
			    	}	
				 break;
			 case "11:30 Sig Dia":
				 valor = 30;
			    	if(PesoVol > valor){
			    		alert("El Peso Volumetrio sobre pasa al Kilataje");
			    	}else{
			    		$scope.disContinuar=false;
			    	}
			     break;
			 case "11:30 Sobre Sig Dia":
				 valor = 30;
			    	if(PesoVol > valor){
			    		alert("El Peso Volumetrio sobre pasa al Kilataje");
			    	}else{
			    		$scope.disContinuar=false;
			    	}
			     break;
			 case "3Kg Sig Dia":
				 valor = 3;
			    	if(PesoVol > valor){
			    		alert("El Peso Volumetrio sobre pasa al Kilataje");
			    	}else{
			    		$scope.disContinuar=false;
			    	}
			     break;
			 case "5Kg Terrestre":
				 valor = 5;
			    	if(PesoVol > valor){
			    		alert("El Peso Volumetrio sobre pasa al Kilataje");
			    	}else{
			    		$scope.disContinuar=false;
			    	}
			     break;
			 case "10Kg Terrestre":
				 valor = 10;
			    	if(PesoVol > valor){
			    		alert("El Peso Volumetrio sobre pasa al Kilataje");
			    	}else{
			    		$scope.disContinuar=false;
			    	}
			     break;
			 case "15Kg Terrestre":
				 valor = 15;
			    	if(PesoVol > valor){
			    		alert("El Peso Volumetrio sobre pasa al Kilataje");
			    	}else{
			    		$scope.disContinuar=false;
			    	}
			     break;
			 case "20Kg Terrestre":
				 valor = 20;
			    	if(PesoVol > valor){
			    		alert("El Peso Volumetrio sobre pasa al Kilataje");
			    	}else{
			    		$scope.disContinuar=false;
			    	}
			     break;
			 case "25Kg Terrestre":
				 valor = 25;
			    	if(PesoVol > valor){
			    		alert("El Peso Volumetrio sobre pasa al Kilataje");
			    	}else{
			    		$scope.disContinuar=false;
			    	}
			     break;
			 case "30Kg Terrestre":
				 valor = 30;
			    	if(PesoVol > valor){
			    		alert("El Peso Volumetrio sobre pasa al Kilataje");
			    	}else{
			    		$scope.disContinuar=false;
			    	}
			     break;
			 case "35Kg Terrestre":
				 valor = 35;
			    	if(PesoVol > valor){
			    		alert("El Peso Volumetrio sobre pasa al Kilataje");
			    	}else{
			    		$scope.disContinuar=false;
			    	}
			     break;
			 case "40Kg Terrestre":
				 valor = 40;
			    	if(PesoVol > valor){
			    		alert("El Peso Volumetrio sobre pasa al Kilataje");
			    	}else{
			    		$scope.disContinuar=false;
			    	}
			     break;
			 case "45Kg Terrestre":
				 valor = 45;
			    	if(PesoVol > valor){
			    		alert("El Peso Volumetrio sobre pasa al Kilataje");
			    	}else{
			    		$scope.disContinuar=false;
			    	}
			     break;
		 
		}
		}
		
		
		
	}
	$scope.desMedidas = false;
	$scope.cSobre= function(){
		if($scope.paquete.paquete.tipoPaquete=="SOBRE"){
			$scope.desMedidas = true;
		}else{
			$scope.desMedidas = false;
		}
	}	
	$scope.paquete={
			tipoEnvio:null , 
			paquete:{tipoPaquete:null}
	}
	$scope.isSobre= function (data) {
		//$scope.libGuia($scope.guardGuia);
		if (data=='1Kg Sobre Sig Dia' || data=='11:30 Sobre Sig Dia'){
			$scope.paquete.paquete.tipoPaquete="SOBRE";
			$scope.paquete.tipoEnvio="Dia Siguiente";
			$scope.desMedidas = true;
		}else{
			$scope.paquete.paquete.tipoPaquete="";
			$scope.paquete.tipoEnvio="";
			$scope.desMedidas = false;
		}
	
	if($scope.paquete.empresa=="ESTAFETA" || $scope.paquete.empresa=="MERVEL"){	
		$scope.paquete.guia = null;
		paqueteriaService.getGuiaByName(data,$scope.paquete.empresa,$cookieStore.get('usuario')).then(function(data) {
			$scope.guardGuia=data;
			$scope.paquete.guia=data;
			$scope.disGuia=true;
			if(!$scope.paquete.guia){
				 var r = confirm("No hay Guias en la Sucursal \n <" + $cookieStore.get('sucursal') + ">\n Click en Aceptar para Agregar o Asignar Guias");
				    if (r == true) {
				    	
				    	$location.path("/guia");
				    	 window.location.reload();
				    }   	
				    
				}
	});
		if(!$scope.paquete.guia){
			$scope.paquete.guia = null;
		}
	}
	}
	$scope.cleanMdl = function() {
		$window.location.reload(true);
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
	
	$scope.prePaquete = function(inf,cant){
		$scope.idVenta=inf;
		if(cant >=0){
			$scope.venta.cantidad = cant;
		}
		
		$("#modalEnvio").modal();
	
	
	}
	$scope.libGuia= function(g){
		if($scope.paquete.empresa == "ESTAFETA")
		paqueteriaService.liberaGuias(g).then(function(data) {
			console.log("se ha eliminado la guia ", g);
		});
	}
	$scope.delVenta = function(){
		
		$scope.libGuia($scope.guardGuia);
		if($scope.venta.cantidad == 0){
			var data = {id: $scope.idVenta}
			$scope.eliminarVenta(data);
		}
		
	}
	$scope.limpiarFecha = function(){
		alert("Entro a la funcion");
		$scope.fechaInicio.value=" ";
		$scope.fechaFin.value=" ";
	}
	
	$('.report').on("click",function(){
		setTimeout(function(){ $window.location.reload() }, 1000);
		})

	$scope.generarFolio = function(){
		
		var r = confirm("Click en Aceptar para Confirmar Nueva Venta");
	    if (r == true) {
		
		paqueteriaService.getFolio().then(function(data) {
			$scope.venta.folio = data;
		});
		
		
		paqueteriaService.addVenta($cookieStore.get('usuario'), $scope.venta).then(function(data) {
			$scope.idVenta = data;
			$scope.prePaquete(data);
		});
	    }
	}
	
	$scope.savePaquete = function(data){
		console.log("Datos de Paquete", data);
		if(!$scope.paquete.costoSeguro){
			$scope.paquete.costoSeguro= 0;
		}
		if(!$scope.paquete.paquete.peso){
			$scope.paquete.paquete.peso= 0;
		}
		if(!$scope.paquete.precio){
			$scope.paquete.precio= 0;
		}
		if($scope.paquete.rastreo == null){
			alert("No se ha capturado \n El Numero de Rastreo");
			nextPrev(-3);
			return
		}
		if($scope.paquete.empresa == "ESTAFETA" ||$scope.paquete.empresa == "MERVEL" ){
			var LCP = $scope.paquete.cliente.codigoPostal;
			var len =LCP.toString().length;
			if( len != 5){
				alert("El C\u00f3digo Postal debe ser de 5 d\u00edgitos y s\u00f3lo captur\u00f3 "+len);
				nextPrev(-2);
				
				return;
			}
		}
//		if($scope.paquete.empresa=='ESTAFETA'){
//			if($scope.adicional=='-'){
//				alert("No se ha capturado el Numero a agregar a la GUIA");
//				return
//			}
//		}
		
		if($scope.paquete.precio == 0){
			alert("No se ha capturado \n El Precio");
			nextPrev(-3);
			return
		}
		
		var r = confirm("¿Esta seguro de realizar el Envio?");
	    if (r == true) {
	    	$scope.guardarEnvio();
	    	
	    }  	
		
	}
	
	$scope.products =[];
    $scope.addItem = function () {
        $scope.errortext = "";
        if (!$scope.addMe) {return;}
        if ($scope.products.indexOf($scope.addMe) == -1) {
// $scope.products.push($scope.addMe,$scope.addMe2);
            $scope.products.push({descripcion:$scope.addMe, cantidad:$scope.addMe2 , costo:$scope.addMe3});
            console.log($scope.products);
            $scope.paquete.materiales=$scope.products;
        } else {
            $scope.errortext = "Ya se encuentra en la lista.";
        }
        $scope.addMe="";
        $scope.addMe2="";
        $scope.addMe3="";
    }
    $scope.removeItem = function (x) {
        $scope.errortext = "";    
        $scope.products.splice(x, 1);
        console.log( $scope.products);
    }
	$scope.maxchar = 10;
	$scope.hide=true;
	$scope.isDFU=false;
	$scope.requerido=true;
	$scope.disKilataje=true;
	$scope.kilatajeHide=false;
	$scope.mdhide=false;
	$scope.isCajaFd = function(dato){
		if(($scope.paquete.empresa=="DHL" || $scope.paquete.empresa=="FEDEX" || $scope.paquete.empresa=="UPS") && dato=="Caja"){
		$scope.mdhide=false;
		}else if($scope.paquete.empresa=="ESTAFETA"){
		$scope.mdhide=false;
		}else{
		$scope.mdhide=true;
		}
	}
	$scope.isEstafeta = function() {
		$scope.libGuia($scope.guardGuia);
		$scope.disKilataje=false;
		$scope.maxchar = 10;
		if ($scope.paquete.empresa=="DHL"){
			$scope.maxchar = 10;
			$scope.kilatajeHide=true;
		}
		if ($scope.paquete.empresa=="FEDEX"){
			$scope.maxchar = 12;
			
			$scope.kilatajeHide=true;
			$scope.mdhide=true;
				}else{
					
					$scope.mdhide=false
				}
		if ($scope.paquete.empresa=="UPS"){
			$scope.maxchar = 18;
			$scope.kilatajeHide=true;
		}
		if ($scope.paquete.empresa=="DHL" || $scope.paquete.empresa=="FEDEX" || $scope.paquete.empresa=="UPS"){
			$scope.isDFU=true;
			$scope.tipoPaq=catalogoPqueteFedex();
		}else{
			$scope.isDFU=false;
			$scope.kilatajeHide=false;
			$scope.tipoPaq=catalogoPquete();
		}
		
		
		if ($scope.paquete.empresa=="ESTAFETA" || $scope.paquete.empresa=="MERVEL"){
			$scope.hide=false;
			$scope.requerido=false;
			document.getElementById("delTap").classList.add("tab");
			
		
			
		}else{
			$scope.hide=true;
			$scope.requerido=true;
			document.getElementById("delTap").classList.remove("tab");
			$scope.paquete.guia=null;
			$scope.disGuia=false;
		}
		
		$scope.paquete.tipoGuia=null;
		$scope.paquete.guia = null;
		$scope.paquete.rastreo = null;
	}
	$scope.disGuia=true;
	$scope.showPDF = function(id){
		$("#mdlLoad").modal();
		$scope.url = "venta/generaTicket/"+id+"/"+$scope.usuario;
		setTimeout(() => {
			$("#mdlLoad").modal('toggle');
			$("#modalPDF").modal();
			
		}, 3000);
		
	}
	$scope.infGuida=null;
	$scope.inGuia = false;
	$scope.detalleGuia = function(data){
		console.log(data);
		$scope.infGuia = data;
		$scope.sumMat = 0;
		for(i in data.materiales){
			$scope.sumMat = $scope.sumMat + data.materiales[i].costo;
		}
		$scope.inGuia=true;
		
	}
	$scope.showPDFM = function(datos){
		$("#mdlLoad").modal();
		if(datos.empresa=='ESTAFETA'){
			$scope.url = "envio/generaGuiaEstafeta/"+datos.id+"/"+$scope.idVenta+"/"+$scope.usuario;
		}else{
			$scope.url = "envio/generaGuiaMervel/"+datos.id+"/"+$scope.idVenta+"/"+$scope.usuario;
		}
		setTimeout(() => {
			$('#mdlLoad').modal('toggle');	
			$("#modalPDF").modal();
		}, 2000);
		
	}
	$scope.eliminarVenta = function(datos){
		 var r = confirm("Click en Aceptar para Eliminar la Venta. \n" + datos.folio);
		    if (r == true) {
		    	paqueteriaService.eliminarVenta(datos.id).then(function(data) {
		    		alert(data);
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
//	window.onbeforeunload = function(e) {
//        return 'leave?';
//      }
	$scope.datosCP =[];
	$scope.maskGuia = function(){
		var guia = document.getElementById('guia').value;
		if (guia.length == 10){
			document.getElementById('guia').value = document.getElementById('guia').value + "-";
		}
	}
	$scope.addGuiaPre = function(valor){
		
	    	$scope.paquete.guia= valor + "" + $scope.guardGuia;
		
	}
	$scope.getCP = function(tipo){
		if (tipo == "remitente"){
//			var cp = $scope.paquete.cliente.codigoPostal;
			 var value = document.getElementById('cpR').value;

			if (value.length == 5){
			$.getJSON("https://api-codigos-postales.herokuapp.com/v2/codigo_postal/"+value , function(data) {
				$scope.colonia=data.colonias;
				$scope.paquete.cliente.municipio=data.municipio;
				$scope.paquete.cliente.estado=data.estado;
			});
			}
	}else{
		
		 var value = document.getElementById('cpD').value;

			if (value.length == 5){
			$.getJSON("https://api-codigos-postales.herokuapp.com/v2/codigo_postal/"+value , function(data) {
				$scope.coloniaD=data.colonias;
				$scope.paquete.destinatario.municipio=data.municipio;
				$scope.paquete.destinatario.estado=data.estado;
			});
			}
		}
	}
	$scope.getIdVenta=null;
	$scope.preFacturar = function(venta){
		$scope.getIdVenta=venta.id;
		console.log("Venta a Facturar", venta);
		$("#modalFactura").modal();
	}
	
	$scope.preCancelar = function(dato){
		$scope.datoFact= dato;
		$("#modalCancelFacct").modal();
	}
	$scope.cancelarFactura = function(){
		paqueteriaService.cancelFact($scope.datoFact).then(function(data) {
			alert(data);
			$window.location.reload();
		})
	}
	$scope.loading=function(){
		$("#mdlLoad").modal();
		setTimeout(() => {
			$('#mdlLoad').modal('toggle');
		}, 5000);
	}
	$scope.facturar=function(){
		$("#mdlLoad").modal();
		paqueteriaService.factura($scope.getIdVenta, $scope.factura).then(function(data) {
			$('#mdlLoad').modal('toggle');
			alert("Se ha facturado Correctamente");
			$window.location.reload();
		})
	}
	$scope.buscaUsuario=function(cli){
		if($scope.paquete.empresa == 'MERVEL' || $scope.paquete.empresa == 'ESTAFETA'){
			if(cli=="re"){
		paqueteriaService.buscaCliente($scope.paquete.cliente.nombre).then(function(data){
			$scope.listaCliente = data;
			if(data[0].nombre){
			$('#modalEnvio').modal('toggle');
			
			$("#mdlSelClient").modal('show');
			
			
			console.log("Datos del Cliente ", data);
			}
		})
		}else{
			paqueteriaService.buscaCliente($scope.paquete.destinatario.nombre).then(function(data){
				$scope.listaCliente = data;
				if(data[0].nombre){
				$('#modalEnvio').modal('toggle');
				$("#mdlSelClientDes").modal();
				}});
		}
		}
	}
	$scope.cancelSeleccion = function(cli){
		if(cli=="re"){
			$('#mdlSelClient').modal('toggle');
		}else{
			$('#mdlSelClientDes').modal('toggle');
		}
		$('#modalEnvio').modal();
	}
	$scope.ClienteSeleccionado = function(data,tipo){
		if(tipo=="re"){
			$('#mdlSelClient').modal('toggle');
			$('#modalEnvio').modal();
			$scope.paquete.cliente.enAtencion = data.enAtencion;
			$scope.paquete.cliente.codigoPostal = data.codigoPostal;
			$scope.paquete.cliente.calle = data.calle;
			$scope.paquete.cliente.noExterior = data.noExterior;
			$scope.paquete.cliente.noInterior = data.noInterior;
			//document.getElementById("slCol").value = [data.colonia];
			$scope.colonia=[data.colonia];
			$scope.paquete.cliente.localidad = data.localidad;
			$scope.paquete.cliente.municipio = data.municipio;
			$scope.paquete.cliente.estado = data.estado;
			$scope.paquete.cliente.telefono = data.telefono;
			$scope.paquete.cliente.referencias = data.referencias;
		}else{
			$('#mdlSelClientDes').modal('toggle');
			$('#modalEnvio').modal();
			$scope.paquete.destinatario.enAtencion = data.enAtencion;
			$scope.paquete.destinatario.codigoPostal = data.codigoPostal;
			$scope.paquete.destinatario.calle = data.calle;
			$scope.paquete.destinatario.noExterior = data.noExterior;
			$scope.paquete.destinatario.noInterior = data.noInterior;
			//document.getElementById("slDes").value = [data.colonia];
			$scope.coloniaD=[data.colonia];
			//$scope.paquete.destinatario.colonia=data.colonia;
			$scope.paquete.destinatario.localidad = data.localidad;
			$scope.paquete.destinatario.municipio = data.municipio;
			$scope.paquete.destinatario.estado = data.estado;
			$scope.paquete.destinatario.telefono = data.telefono;
			$scope.paquete.destinatario.referencias = data.referencias;
		}

		
	}
//	$scope.$watch('busca',function(){
////		if($scope.busca.length>3){
//			$scope.buscar();
////		}
//	},true);
//	
//	$scope.buscar=function(){
//		paqueteriaService.buscaCliente($scope.busca).then(function(data){
//			
//			$scope.found=[];
//			for(var i=0; i< data.length; i++){
//				$scope.found.push(data[i].nombre);
//				
//			}
//
//			
//			$('#buscaCliente').typeahead({
//
//			    source: $scope.found,
//
//			    updater:function (item) {
//			    	$scope.paquete.cliente.nombre= item;
//			        return item;
//			    }
//			
//			});
//			$('#buscaCliente').data('typeahead').source=$scope.found;
//		});
//	}
	
	 $("#modalFactura").on('hidden.bs.modal', function () {
		 $scope.factura=[];
		 $scope.factura.mail=null;
 });
	 $scope.showSuc=function(id){
		  for (var i = 0; i < $scope.sucursal.length; i++){
		        if ($scope.sucursal[i].id == id){
		          return $scope.sucursal[i].nombre;
	 }
		  }}
} ]);
app.controller("paqueteriaController",['$scope','$rootScope','$window', '$location', '$cookieStore','$cookies','paqueteriaService','sessionService',function($scope,$rootScope, $window, $location, $cookieStore,$cookies, paqueteriaService,sessionService){
	sessionService.isAuthenticated();
	$scope.usuario=$cookieStore.get('usuario');
	$scope.paqueteria={guia:null};
//	paqueteriaService.getGuiaByName($cookieStore.get('usuario')).then(function(data) {
//		$scope.paqueteria.guia=data.numero;
//	});
	$scope.products =[];
    $scope.addItem = function () {
        $scope.errortext = "";
        if (!$scope.addMe) {return;}
        if ($scope.products.indexOf($scope.addMe) == -1) {
// $scope.products.push($scope.addMe,$scope.addMe2);
            $scope.products.push({descripcion:$scope.addMe, cantidad:$scope.addMe2, costo:$scope.addMe3});
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
	// console.log($scope.form.pass.$valid);
// $scope.validate();
	$scope.paqueteria.materiales=$scope.products;
	paqueteriaService.crearPaquete($cookieStore.get('usuario'),$scope.paqueteria).then(function(data) {
						var x = document.getElementById("snackbar")
					    x.className = "show";
						setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
					    setTimeout(function(){ if($scope.paqueteria){window.location="#/altaPaquete"; $window.location.reload(); } }, 3000);
//						
					})}





} ]);
