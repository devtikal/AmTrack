package com.tikal.aero.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tikal.aero.dao.ComponenteDao;
import com.tikal.aero.dao.HistorialCompDao;
import com.tikal.aero.modelo.entity.HistorialComponente;
import com.tikal.aero.modelo.entity.Tarea;
import com.tikal.aero.security.PerfilDAO;
import com.tikal.aero.security.UsuarioDAO;
import com.tikal.aero.util.AsignadorDeCharset;
import com.tikal.aero.util.JsonConvertidor;
import com.tikal.aero.util.Util;

@Controller
@RequestMapping(value="/historialComp")
public class HistorialCompController {

	
	
	
	@Autowired
	UsuarioDAO usuarioDao;

		
	@Autowired
	PerfilDAO perfilDAO; 

	 @Autowired
	 ComponenteDao componenteDao;
	 
	 @Autowired
	 HistorialCompDao historialCompDao;
	 
	 
	 @RequestMapping(value = {"/add"}, method = RequestMethod.POST, consumes = "application/json") 
	   public void addTarea(HttpServletResponse response, HttpServletRequest request,
			   @RequestBody String json) throws IOException{
	    	System.out.println("si entra al add por POST"+json);
//	    	if(Util.verificarPermiso(request, usuarioDao, perfilDAO,0)){   
//		        try {
		        	AsignadorDeCharset.asignar(request, response);
		        	HistorialComponente a =(HistorialComponente) JsonConvertidor.fromJson(json, HistorialComponente.class);        	
		        	
		        	historialCompDao.save(a);	            
//		        } catch (RuntimeException ignored) {
//		        	ignored.printStackTrace();
//		        }
//	    	}else{
//				response.sendError(403);
//			}
	       
	    }

/////////////////////////////////////////////////////////////////////////////////////////**********************
	   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllComp(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<HistorialComponente> lista = historialCompDao.getAll();
			if (lista == null) {
				lista = new ArrayList<HistorialComponente>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	   
	   @RequestMapping(value = { "/findAll/{idComponente}" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllComp(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idComponente) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<HistorialComponente> lista = historialCompDao.byComponente(idComponente);
			if (lista == null) {
				lista = new ArrayList<HistorialComponente>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	   @RequestMapping(value = {"/delete/{id}" }, method = RequestMethod.POST)
	   public void delete(HttpServletResponse response, HttpServletRequest request,
			   @PathVariable Long id) throws IOException {
		   ////////////ojo cuando borra Tarea, checr muy bien lo de Static en el dao y el @override de daoimpl
			if(Util.verificarPermiso(request, usuarioDao, perfilDAO,0)){    
				   System.out.println("si esta en delete"+id);
				   historialCompDao.delete(historialCompDao.consult(id));
				   System.out.println("historial eliminado....");
				   response.getWriter().println("ok");
		   }else{
				response.sendError(403);
			}
	   }
	   
	   @RequestMapping(value = {"/update" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request,@RequestBody String json)throws IOException {
			System.out.println("obj:"+json);
			if(Util.verificarPermiso(request, usuarioDao, perfilDAO,0)){   
			
				AsignadorDeCharset.asignar(request, response);
				HistorialComponente a = (HistorialComponente) JsonConvertidor.fromJson(json, HistorialComponente.class);
	
				historialCompDao.update(a);
				response.getWriter().println(JsonConvertidor.toJson(a));
			}else{
				response.sendError(403);
			}
			
		}

	
}
