package com.tikal.aero.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.tikal.aero.dao.AeronaveDao;
import com.tikal.aero.modelo.entity.Aeronave;
import com.tikal.aero.security.PerfilDAO;
import com.tikal.aero.security.UsuarioDAO;
//import com.tikal.aero.service.ComponenteService;
import com.tikal.aero.util.AsignadorDeCharset;
import com.tikal.aero.util.JsonConvertidor;
import com.tikal.aero.util.Util;
import com.tikal.aero.modelo.entity.Contador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/aeronave")


public class AeronaveController  {
	
//    @Autowired
//    private AeronaveService aeronaveService;
   
    @Autowired
    AeronaveDao aeronaveDao;
    
  
	@Autowired
	UsuarioDAO usuarioDao;

		
	@Autowired
	PerfilDAO perfilDAO; 

 

   
   @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
   
   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
	   response.getWriter().println("Prueba del mètodo PROBAR");

    }
   
 @RequestMapping(value= {"/add_"}, method = RequestMethod.GET)
 public void addAeroGet(HttpServletResponse response, HttpServletRequest request) throws IOException{
		   System.out.println("si entra a Aeronave controller");   
		   Aeronave entry= new Aeronave();
		   
		  
		   		entry.setMarca("MARCA-aerofly");
		   		entry.setModelo("MODELO_111AAAXXX");
		   		entry.setNumeroAeronave("1");
		   		entry.setMatricula("AAW-21-37");
		   		entry.setNumeroSerie("23432587Z");
				entry.setModelo("A-4000");
				entry.setTiempovuelo(4590);
				entry.setAterrizaje("23 en toluca ...");
				entry.setNacionalidad("MEXICANA");
				//Contador.reinicia();
				
	       
		   System.out.println("ya");	    
	        aeronaveDao.save(entry);   //implementa el dao 
	        System.out.println("ya guardo la entity de aeronave");
	    	Contador.incremeta();
	       // return "Orden_de_trabajo"; ///poner el html de aeronave alta
	        response.getWriter().println(JsonConvertidor.toJson(entry));
		}
        
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addAeronave(HttpServletResponse response, HttpServletRequest request,
			   @RequestBody String json) throws IOException{
	    	System.out.println("si entra al add por POST"+json);
	    //	if(Util.verificarPermiso(request, usuarioDao, perfilDAO,0)){   
		
		        	AsignadorDeCharset.asignar(request, response);
		        	Aeronave a =(Aeronave) JsonConvertidor.fromJson(json, Aeronave.class);        	
		        	
		        	
						a.setNumeroAeronave(String.valueOf(Contador.getFolio()));
						Contador.incremeta();
				
		        	aeronaveDao.save(a);	            

//	    	}else{
//				response.sendError(403);
//			}
	       
	    }
    
    /////////////////////////////////////////////////////////////////////////////////////////**********************
	   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllComp(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Aeronave> lista = aeronaveDao.getAll();
			if (lista == null) {
				lista = new ArrayList<Aeronave>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	   
	   @RequestMapping(value = {"/delete/{id}" }, method = RequestMethod.POST)
	   public void deleteAeronave(HttpServletResponse response, HttpServletRequest request,
			   @PathVariable Long id) throws IOException {
		   ////////////ojo cuando borra aeronave, checr muy bien lo de Static en el dao y el @override de daoimpl
		//	if(Util.verificarPermiso(request, usuarioDao, perfilDAO,0)){    
				   System.out.println("si esta en delete"+id);
				   aeronaveDao.delete(aeronaveDao.consult(id));
				   System.out.println("aeronave eliminada....");
				   response.getWriter().println("ok");
//		   }else{
//				response.sendError(403);
//			}
	   }
	   
	   @RequestMapping(value = {"/update" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request,
				@RequestBody String json , @PathVariable String userName)throws IOException {
			System.out.println("obj de edgar:"+json);
			//if(Util.verificarPermiso(request, usuarioDao, perfilDAO,0)){   
			
				AsignadorDeCharset.asignar(request, response);
				Aeronave a = (Aeronave) JsonConvertidor.fromJson(json, Aeronave.class);
	
				aeronaveDao.update(a);
				response.getWriter().println(JsonConvertidor.toJson(a));
//			}else{
//				response.sendError(403);
//			}
			
		}
	   
	   //////////////////////////////////////////////////////////////////////////////////////////*******************
     
   
}