app.service("componentesServices",['$http', '$q','$location', function($http, $q, $location){
	this.altaComponente=function(data){
		var d = $q.defer();
		$http.post("/componente/add",data).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				if(response.status==403){
					alert("No esta autorizado para realizar esta accion");
				}
			});
		return d.promise;
	}
	
	this.listaComponente=function(){
		var d = $q.defer();
		$http.get("/componente/findAll").then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				if(response.status==403){
					alert("No esta autorizado para realizar esta accion");
				}
				if(response.status!=403){
					alert("No se ha podido procesar la solicitud");
				}
			});
		return d.promise;
	}
	
	this.actualizaComponente=function(data){
		var d = $q.defer();
		$http.post("/componente/update",data).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				if(response.status==403){
					alert("No esta autorizado para realizar esta accion");
				}
				if(response.status!=403){
					alert("No se ha podido procesar la solicitud");
				}
			});
		return d.promise;
	}
	
	this.eliminaComponente=function(id){
		var d = $q.defer();
		$http.get("/componente/delete/"+id).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				if(response.status==403){
					alert("No esta autorizado para realizar esta accion");
				}
				if(response.status!=403){
					alert("No se ha podido procesar la solicitud");
				}
			});
		return d.promise;
	}
	this.getListaHistoriaByComponents = function(id){
		var d = $q.defer();
		$http.get("/historialComp/findAll/"+id).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				if(response.status==403){
					alert("No esta autorizado para realizar esta accion");
				}
			});
		return d.promise;
	}
	this.getListaTareasByComponents = function(id){
		var d = $q.defer();
		$http.get("/tarea/findAll/"+id).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				if(response.status==403){
					alert("No esta autorizado para realizar esta accion");
				}
			});
		return d.promise;
	}
	this.addHistorial = function(data){
		var d = $q.defer();
		$http.post("/historialComp/add ",data).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				if(response.status==403){
					alert("No esta autorizado para realizar esta accion");
				}
			});
		return d.promise;
	}
	this.deleteHistorial = function(id){
		var d = $q.defer();
		$http.post("/historialComp/delete/ "+id).then(
			function(response) {
				d.resolve(response.data);
			}, function(response) {
				if(response.status==403){
					alert("No esta autorizado para realizar esta accion");
				}
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
		componentesServices.actualizaComponente(data).then(function(resp){
			alert("Se ha actualizacon correctamente")
			window.location.reload();
		});
	}
	$scope.onEliminarComponente = function(data){
		componentesServices.eliminaComponente(data.id).then(function(){
			alert("Se ha eliminado correctamente")
		});
	}
	
	$scope.onNuevaTarea = function(){
		$scope.tarea.idComponente = $scope.componente.id
		$scope.tarea.marcaAeronave = $scope.componente.marcaAeronave
		$scope.tarea.modeloAeronave = $scope.componente.modeloAeronave
		console.log($scope.tarea)
		
		tareaServices.altaTarea($scope.tarea).then(function(){
			$('#mdlNvaTarea').modal('toggle')
			swal("Exito!", "Tarea se ha creada correctamente", "success").then(function(){
				$scope.onVerTarea($scope.componente);
			});
			
		})
	}
	$scope.onVerHistorial = function(data){
		$scope.componente = data;
		$scope.loadHistorial(data.id);
		$('#mdlHistorial').modal('toggle')
		
	}
	$scope.loadHistorial = function(id){
		componentesServices.getListaHistoriaByComponents(id).then(function(data){
			$scope.listaHistorial = data
			
		});
	}
	$scope.onAgregarHistorial = function(){
		$('#mdlHistorial').modal('toggle')
		$('#mdlAddHistorial').modal('toggle')
		
	}
	$scope.addHistorial = function(data){
		var send = data;
		send.idComponente = $scope.componente.id
		componentesServices.addHistorial(send).then(function(){
			$scope.newHistorial = {}
			$('#mdlAddHistorial').modal('toggle')
			$scope.onVerHistorial($scope.componente)
		})
		
		
	}
	$scope.onEliminar = function(data){
		var r = confirm("Desea eliminar el siguiente registro:\nFecha:\n"+data.fecha+"\nDescripcion:\n"+data.Descripcion);
		if(r){
			componentesServices.deleteHistorial(data.id).then(function(){
				$scope.loadHistorial($scope.componente.id)

			})
		}
		
	}
	
	$scope.onVerTarea = function(data){
		$scope.componente = data;
		$('#mdlVerTareas').modal('toggle')
		$scope.loadTareas(data.id)
		
	}
	$scope.loadTareas = function(id){
		componentesServices.getListaTareasByComponents(id).then(function(data){
			$scope.listaTareas = data
			console.log(data)
			
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