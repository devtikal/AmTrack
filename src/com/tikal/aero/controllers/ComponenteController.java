package com.tikal.aero.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.tikal.aero.dao.ComponenteDao;
import com.tikal.aero.modelo.entity.Componente;
import com.tikal.aero.security.PerfilDAO;
import com.tikal.aero.security.UsuarioDAO;
import com.tikal.aero.util.AsignadorDeCharset;
import com.tikal.aero.util.JsonConvertidor;
import com.tikal.aero.util.Util;




@Controller
@RequestMapping(value="/componente")
public class ComponenteController {
	
	 
	
	 
	 @Autowired
		UsuarioDAO usuarioDao;
	 

		
		@Autowired
		PerfilDAO perfilDAO; 
		
	 
	 @Autowired

	 ComponenteDao componenteDao;


	 
	 @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR en Componente");

	    }
	 
	 ////////////////////////////////////////////////*****************************************************************
	 
	 @RequestMapping(value = "/add", method = RequestMethod.GET)
	 public void addAeroGet(HttpServletResponse response, HttpServletRequest request) throws IOException{
		   System.out.println("si entra a Orden controller");   
		   Componente entry= new Componente();
		   		
		   		//entry.setId(Long.parseLong("1001"));
		   		entry.setclaveInterna("GASKET");
		   		entry.setnombre("nombre del componente");
		   		entry.setclaveInterna("PARTE_1");
		   		//entry.setD_pendientes(3);
		   		entry.setD_cantidad(100);
		   		entry.setIdCategoria(Long.parseLong("5910974510923776"));
		   		entry.setIdCondicion(Long.parseLong("6473924464345088"));
		   		entry.setIdUnidad(Long.parseLong("5348024557502464"));
		   		entry.setMaximo(900);
		   		entry.setMinimo(2);
		   		//entry.setD_requisicion("num de requisicion");
		   		//entry.setD_vale("numero de vale");
		   		entry.setFechaApertura("01/12/2017");
		  
	            System.out.println("si asign/ valor"+entry);
	      
		   System.out.println("yaaaaa");	    
	        componenteDao.save(entry);   //implementa el dao  
	        response.getWriter().println(JsonConvertidor.toJson(entry));
		}
	
	 /////////////////////////////////////////////////////********************************************************

	 
	 @RequestMapping(value = {"/add/{userName}"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addComponente(HttpServletResponse response, HttpServletRequest request, @RequestBody String json, @PathVariable String userName) throws IOException{
	    	  System.out.println("si entra al add por POST"+json);
	    	 	if(Util.verificarPermiso(request, usuarioDao, perfilDAO,0)){   
		        try {
		        	AsignadorDeCharset.asignar(request, response);
		        	// System.out.println("request......."+request);
		        	// System.out.println("request......."+response);
		        	Componente cmp =(Componente) JsonConvertidor.fromJson(json, Componente.class);
		        	// System.out.println("el nuevo objeto: "+orden );
		        	//pegar el valor de empresa, aeronave y contacato
		        	//cmp.setD_pendientes(50);//aqui va funcion para calcular cuantas piezas pendientes hay de cada componente
		        	//orden.setFolio(1111);
		        	componenteDao.save(cmp);	            
		        } catch (RuntimeException ignored) {
		        	ignored.printStackTrace();
		            // getUniqueEntity should throw exception
		        }
	      }else{
			response.sendError(403);
		  }
	       
	    }
	 
	   /////////////////////////////////////////////////////////////////////////////////////////**********************
	   @RequestMapping(value = { "/findAll_antes" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllComp(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Componente> lista = componenteDao.getAll();
			if (lista == null) {
				lista = new ArrayList<Componente>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	   
	   @RequestMapping(value = { "/findByDiscrepancia/{idDiscrepancia}" }, method = RequestMethod.GET, produces = "application/json")
		public void findByDis(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idDiscrepancia) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Componente> lista = componenteDao.getByDiscrepancia(idDiscrepancia);
			if (lista == null) {
				lista = new ArrayList<Componente>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   /// trae TODOS los componentes
	   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllComplete(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			//List<ComponenteVo> cvos= new ArrayList<ComponenteVo>();
			List<Componente> lista = componenteDao.getAll();			
							
			response.getWriter().println(JsonConvertidor.toJson(lista));
		}
	   
	   

	 
	   
	   @RequestMapping(value = { "/getByCategoria/{idCategoria}" }, method = RequestMethod.POST, produces = "application/json")
		public void getByCat(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idCategoria) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			System.out.println("si entraxxxxxxx ");
			//List<ComponenteVo> cvos= new ArrayList<ComponenteVo>();
			List<Componente> lista = componenteDao.getByCategoria(idCategoria);			
//					for (Componente c : lista){
//							c.setD_pendientes(requisicionDao.getPendientes(c.getId()));
//							componenteDao.update(c);						   
//					}					
			response.getWriter().println(JsonConvertidor.toJson(lista));
		}
	   
	   
	   @RequestMapping(value = {"/delete/{folio}/{userName}" }, method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	   public void deleteOrden(HttpServletResponse response, HttpServletRequest request, @RequestBody String json,
		@PathVariable Long id, @PathVariable String userName) throws IOException {
		 	if(Util.verificarPermiso(request, usuarioDao, perfilDAO,0)){   
		   		componenteDao.delete(componenteDao.consult(id));
		   }else{
				response.sendError(403);
			}
	   }
	   
	   //////////////////////////////////////////////////////////////////////////////////////////*******************
	   //////// update de existencias segun las requisiciones
	   /////////////   //////////id componente, folio de la req
	   
	 
	 //  @RequestMapping(value = {
//		"/update" }, method = RequestMethod.POST, consumes = "application/json")
//public void update(HttpServletResponse response, HttpServletRequest request, @RequestBody String json)
//		throws IOException {
	   
	   /**
		
		 * @param response
		 * @param request
		 * @param json
		 * @throws IOException
		 */
	  
	/*   @RequestMapping(value = {"/upExistencias_"}, method = RequestMethod.POST, consumes = "application/json")
	   public void updateExistencias_(HttpServletResponse response, HttpServletRequest request, @RequestBody String json) 
		throws IOException {
		   System.out.println("si entra a actualizar existencias");
		   AsignadorDeCharset.asignar(request, response);
		   RequisicionEntity req = (RequisicionEntity) JsonConvertidor.fromJson(json, RequisicionEntity.class);
		   System.out.println("el  id del objeto que me manda es: "+req.getIdComponente());
		   
		   Componente old = componenteDao.consult(req.getIdComponente());
		
		   Integer existencias = old.getD_cantidad()-req.getCantidad();
		   Integer pendientes = old.getD_pendientes()-req.getCantidad();
		   System.out.println("EXISTENCIAS:"+existencias);
		   System.out.println("PENDIENTES:"+pendientes);
		   old.setD_cantidad(existencias);
		   old.setD_pendientes(pendientes);  
		   componenteDao.update(old);
		  // response.getWriter().println(JsonConvertidor.toJson(old));

	   }*/
	   
	   
	
	   
	   
	   @RequestMapping(value = {"/update/{userName}" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request, @RequestBody String json, @PathVariable String userName)
				throws IOException {
		 	if(Util.verificarPermiso(request, usuarioDao, perfilDAO,0)){   
				AsignadorDeCharset.asignar(request, response);
				Componente c = (Componente) JsonConvertidor.fromJson(json, Componente.class);
				//Empleado e= evo.getEmpleado();
				componenteDao.update(c);
				response.getWriter().println(JsonConvertidor.toJson(c));
		   }else{
				response.sendError(403);
			}
		}
	    
	
}
 