package com.tikal.mensajeria.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.tikal.mensajeria.dao.PersonaDao;
import com.tikal.mensajeria.modelo.entity.Persona;
import com.tikal.mensajeria.modelo.entity.Venta;
import com.tikal.util.AsignadorDeCharset;
import com.tikal.util.JsonConvertidor;


@Controller
@RequestMapping("/persona")
public class PersonaController {

	
	@Autowired
	@Qualifier("personaDao")
	PersonaDao personaDao;
	
	
	
	@RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo Persona prueba");

	    }
	 
		//@RequestMapping(value = { "/add/{userName}" }, method = RequestMethod.GET)
	 //public void crearPerfil(HttpServletRequest request, HttpServletResponse response, 
//				@RequestBody String json, @PathVariable String userName)throws IOException {
		@RequestMapping(value = { "/add" }, method = RequestMethod.POST, consumes = "Application/Json")
		public void crearPerfil(HttpServletRequest request, HttpServletResponse response)throws IOException {
//			if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
			//	AsignadorDeCharset.asignar(request, response);
			//	System.out.println(" edgar manda:"+json);
				Persona p = new Persona();
				
				personaDao.save(p);
//			} else {
//				response.sendError(403);
//			}
		}
		
	
		 @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
			public void findAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
				AsignadorDeCharset.asignar(request, response);
				List<Persona> lista = personaDao.findAll();
				if (lista == null) {
					lista = new ArrayList<Persona>();
				}
				response.getWriter().println(JsonConvertidor.toJson(lista));

			}


		 @RequestMapping(value = { "/getByNombre/{nombre}" }, method = RequestMethod.GET, produces = "application/json")
			public void getNombre(HttpServletResponse response, HttpServletRequest request, @PathVariable String nombre) throws IOException {
				AsignadorDeCharset.asignar(request, response);
				Persona p = personaDao.getByNombre(nombre);
				
				response.getWriter().println(JsonConvertidor.toJson(p));

			}

	
}
