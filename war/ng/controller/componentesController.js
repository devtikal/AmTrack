app.service("componentesServices",['$http', '$q','$location', function($http, $q, $location){
	this.altaComponente=function(data){
		var d = $q.defer();
		$http.post("/componente/add",data).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				checkResponse(response);
			});
		return d.promise;
	}
	
	this.listaComponente=function(){
		var d = $q.defer();
		$http.get("/componente/findAll").then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				checkResponse(response);
			});
		return d.promise;
	}
	
	this.actualizaComponente=function(data){
		var d = $q.defer();
		$http.post("/componente/update",data).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				checkResponse(response);
			});
		return d.promise;
	}
	
	this.eliminaComponente=function(id){
		var d = $q.defer();
		$http.get("/componente/delete/"+id).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				checkResponse(response);
			});
		return d.promise;
	}
	this.getListaHistoriaByComponents = function(id){
		var d = $q.defer();
		$http.get("/historialComp/findAll/"+id).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				checkResponse(response);
			});
		return d.promise;
	}
	
	this.addHistorial = function(data){
		var d = $q.defer();
		$http.post("/historialComp/add ",data).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				checkResponse(response);
			});
		return d.promise;
	}
	this.deleteHistorial = function(id){
		var d = $q.defer();
		$http.post("/historialComp/delete/ "+id).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				checkResponse(response);
			});
		return d.promise;
	}
	
}]);


app.controller("componentesListaController",['$rootScope','$scope','$window', '$location', '$cookieStore','componentesServices','$rootScope','tareaServices', function($rootScope,$scope, $window, $location, $cookieStore, componentesServices,$rootScope,tareaServices){

	$scope.getListaComponentes = function(){
		componentesServices.listaComponente().then(function(data){
			$scope.listaComponentes = data
		});
	}
	
	$scope.EComponente = function(data){
		data.fechaApertura = new Date(data.fechaApertura)
		$scope.componente = data;
	}
	$scope.editaComponente = function(data){
			$('#mdlEdita').modal('toggle')
			swal({
				  title: "¿Esta Seguro de Editar?",
				  text: "Una vez Editado el Componente la informacion previa no se podra recuperar",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				})
				.then((willDelete) => {
				  if (willDelete) {
					  componentesServices.actualizaComponente(data).then(function(){
						  swal("Se ha Editado Correctamente el Componente", {
						      icon: "success",
						    }).then(function(){
						    	$scope.getListaComponentes();	
						    	
						    	
						    });	
				    })
				    
				  } else {
					  $scope.getListaComponentes();	
					  
				  }
				});
	}
	$scope.onEliminarComponente = function(data){
		swal({
			  title: "¿Esta Seguro de Eliminar?",
			  text: "Tenga en cuenta que una vez Eliminado el Componente, la informacion no se podra recuperar",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  componentesServices.eliminaComponente(data.id).then(function(){
					  swal("Se ha Eliminado Correctamente el Componente", {
					      icon: "success",
					    }).then(function(){
					    	$scope.getListaComponentes();	
					    	
					    	
					    });	
					});
			    
			  } else {
				  $scope.getListaComponentes();	
				  
			  }
			});
		
	}
	
	$scope.onNuevaTarea = function(){
		$scope.tarea.idComponente = $scope.componente.id
		$scope.tarea.marcaAeronave = $scope.componente.marcaAeronave
		$scope.tarea.modeloAeronave = $scope.componente.modeloAeronave
		console.log($scope.tarea)
		
		tareaServices.altaTarea($scope.tarea).then(function(){
			$scope.tarea = {}
			$('#mdlNvaTarea').modal('toggle')
			swal("Exito!", "Tarea se ha creada correctamente", "success").then(function(){
				$scope.onVerTarea($scope.componente);
			});
			
		})
	}
	$scope.onVerHistorial = function(data){
		$scope.componente = data;
		$scope.loadHistorial(data.id);
		
	}
	$scope.loadHistorial = function(id){
		componentesServices.getListaHistoriaByComponents(id).then(function(data){
			$scope.listaHistorial = data
			$('#mdlHistorial').modal('toggle')
			
		});
	}
	$scope.onAgregarHistorial = function(){
		$('#mdlHistorial').modal('toggle')
		$('#mdlAddHistorial').modal('toggle')
		
	}
	$scope.addHistorial = function(data){
		var send = data;
		send.idComponente = $scope.componente.id
		if(data.fecha && data.Descripcion){
			$('#mdlAddHistorial').modal('toggle')
			componentesServices.addHistorial(send).then(function(){
				swal("Historial Agregado Correctamente", {
				      icon: "success",
				    }).then(function(){
				    	$scope.newHistorial = {}
						$scope.onVerHistorial($scope.componente)				    	
				    	
				    });	
				
			})
		}else {
			if(!send.fecha){
				document.getElementById("hFecha").focus();
			}
			if(!send.Descripcion){
				document.getElementById("hDescripcion").focus();
			}
		}
		
		
		
	}
	$scope.onEliminarHistorial = function(data){
		$('#mdlHistorial').modal('toggle')
		swal({
			  title: "¿Esta Seguro de Eliminar?",
			  text: "Una vez eliminado el Historial no se podra recuperar\n\nFecha: "+data.fecha+" Descripcion: "+data.Descripcion,
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  componentesServices.deleteHistorial(data.id).then(function(){
					  swal("Se ha Eliminado Correctamente el Historial", {
					      icon: "success",
					    }).then(function(){
					    	$scope.loadHistorial($scope.componente.id)
					    	
					    	
					    });	
						

					})
			    
			  } else {
				  $('#mdlHistorial').modal('toggle')
			  }
			});
		
	}
	
	$scope.onVerTarea = function(data){
		$scope.componente = data;
		$scope.loadTareas(data.id)
		
	}
	$scope.loadTareas = function(id){
		tareaServices.getListaTareasByComponents(id).then(function(data){
			$scope.listaTareas = data
			console.log(data)
			$('#mdlVerTareas').modal('toggle')
			
		});
	}
	$scope.onEliminarTarea =  function(tarea){
		$('#mdlVerTareas').modal('toggle')
		swal({
			  title: "¿Esta Seguro de Eliminar?",
			  text: "Una vez eliminada la Tarea " +tarea.nombre+" no se podra recuperar",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  tareaServices.eliminaTarea(tarea.id).then(function(){
					  swal("Se ha Eliminado Correctamente la Tarea", {
					      icon: "success",
					    }).then(function(){
					    		$scope.loadTareas($scope.componente.id)
					    	
					    	
					    });	
			    })
			    
			  } else {
				  $('#mdlVerTareas').modal('toggle')
			  }
			});
	}
	$scope.onEditaTarea = function(tarea){
		$scope.tareaEdita = tarea;
		$('#mdlVerTareas').modal('toggle')
		$('#mdlEditaTarea').modal('toggle')
		
	}
	$scope.actualizaTarea = function(){
		$('#mdlEditaTarea').modal('toggle')
		swal({
			  title: "¿Esta Seguro de Actualizar?",
			  text: "Una vez Actualizada la Tarea la informacion previa no se podra recuperar",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  tareaServices.actualizaTarea($scope.tareaEdita).then(function(){
					  swal("Se ha Actualizado Correctamente la Tarea", {
					      icon: "success",
					    }).then(function(){
					    		$scope.loadTareas($scope.componente.id)
					    	
					    	
					    });	
			    })
			    
			  } else {
				  $scope.loadTareas($scope.componente.id)
				  $('#mdlVerTareas').modal('toggle')
				  
			  }
			});
		
	}
	
			
	$scope.getListaComponentes();		
}]);


app.controller("componentesAltaController",['$rootScope','$scope','$window', '$location', '$cookieStore','componentesServices','$rootScope', function($rootScope,$scope, $window, $location, $cookieStore, componentesServices,$rootScope){

	$scope.componente = {
			nombre:null,
			claveInterna:null,
			claveManual:null,
			noSerie:null,
			cantidad:null,
			maximo:null,
			minimo:null,
			anaquel:null,
			repisa:null,
			imagen:null,
			certificado8130:false,
			modeloAeronave:null,
			marcaAeronave:null,
			observaciones:null
}
	$scope.guardaComponente = function(data){
		componentesServices.altaComponente(data).then(function(){
			alert("Se ha registrado correctamente el componente")
			window.location.reload();
			
		});
	}
	
			
			
}]);