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
						// alert("No tiene permiso de realizar esta acción");
// $location.path("/login");
					}
					d.reject(response);
					$window.location.reload;
				});
		return d.promise;
	}
	this.getVentaByUser = function(userName) {
		var d = $q.defer();
		$http.get("venta/findAll/"+userName).then(
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
					}if(response.status==403){
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
		$http.get("factura/cancelarAck/"+idVenta).then(
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
}]);

app.controller("EnvioController",['$scope','$rootScope','$window', '$location', '$cookieStore','$cookies','paqueteriaService','sessionService',function($scope,$rootScope, $window, $location, $cookieStore,$cookies, paqueteriaService,sessionService){
	sessionService.isAuthenticated();
	$scope.perfil = $cookieStore.get('perfil');
	$scope.paquete={guia:null};
	$scope.venta={fecha: new Date()};
	$scope.idVenta=null;
	paqueteriaService.getVentaByUser($cookieStore.get('usuario')).then(function(data) {
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
		paqueteriaService.getGuiaByName(data,$scope.paquete.empresa,$cookieStore.get('usuario')).then(function(data) {
			
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
	
	$scope.prePaquete = function(inf){
		$scope.idVenta=inf;
		$("#modalEnvio").modal();
	
	
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
		
		$scope.venta.cantidad=0;
		paqueteriaService.addVenta($cookieStore.get('usuario'), $scope.venta).then(function(data) {
			$scope.idVenta = data;
			$scope.prePaquete(data);
		});
	    }
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
// $scope.products.push($scope.addMe,$scope.addMe2);
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
	$scope.disKilataje=true;
	$scope.isEstafeta = function() {
		$scope.disKilataje=false;
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
		
		
	}
	$scope.disGuia=true;
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
	$scope.datosCP =[];
	$scope.maskGuia = function(){
		var guia = document.getElementById('guia').value;
		if (guia.length == 10){
			document.getElementById('guia').value = document.getElementById('guia').value + "-";
		}
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
			$window.location.reload();
		})
	}
	$scope.facturar=function(){
		paqueteriaService.factura($scope.getIdVenta, $scope.factura).then(function(data) {
			alert("Se ha facturado Correctamente");
			$window.location.reload();
		})
	}
	$scope.buscaUsuario=function(){
		if($scope.paquete.empresa == 'MERVEL' || $scope.paquete.empresa == 'ESTAFETA'){
	
		paqueteriaService.buscaCliente($scope.paquete.cliente.nombre).then(function(data){
			console.log("Datos del Cliente ", data);
				$scope.paquete.cliente.enAtencion = data.enAtencion;
				$scope.paquete.cliente.codigoPostal = data.codigoPostal;
				$scope.paquete.cliente.calle = data.calle;
				$scope.paquete.cliente.noExterior = data.noExterior;
				$scope.paquete.cliente.noInterior = data.noInterior;
//				$scope.paquete.cliente.colonia= data.colonia;
				$scope.colonia=[data.colonia];
//				document.getElementById("slCol").value = data.colonia;
				$scope.paquete.cliente.localidad = data.localidad;
				$scope.paquete.cliente.municipio = data.municipio;
				$scope.paquete.cliente.estado = data.estado;
				$scope.paquete.cliente.telefono = data.telefono;
				$scope.paquete.cliente.referencias = data.referencias;
			
		})
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

