package com.tikal.mensajeria.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tikal.mensajeria.dao.EmpresaDao;
import com.tikal.mensajeria.modelo.entity.Empresa;
import com.tikal.util.AsignadorDeCharset;
import com.tikal.util.JsonConvertidor;


@Controller
@RequestMapping("/empresa")
public class EmpresaController {

	
	@Autowired
	@Qualifier("empresaDao")
	EmpresaDao empresaDao;
	
	
	
	@RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo Empresa prueba");

	    }
	 
		//@RequestMapping(value = { "/add/{userName}" }, method = RequestMethod.GET)
	 //public void crearPerfil(HttpServletRequest request, HttpServletResponse response, 
//				@RequestBody String json, @PathVariable String userName)throws IOException {
		@RequestMapping(value = { "/add_" }, method = RequestMethod.GET)
		public void crearPerfil(HttpServletRequest request, HttpServletResponse response)throws IOException {
//			if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
			//	AsignadorDeCharset.asignar(request, response);
			//	System.out.println(" edgar manda:"+json);
				Empresa empresa = new Empresa();
				empresa.setDescripcion("UPS__");
				empresaDao.save(empresa);
//			} else {
//				response.sendError(403);
//			}
		}
		
		@RequestMapping(value = { "/add" },  method = RequestMethod.POST, consumes = "Application/Json")
		public void crearPerfil(HttpServletRequest request, HttpServletResponse response, 
				@RequestBody String json)throws IOException {
		
		//				if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
			AsignadorDeCharset.asignar(request, response);
			System.out.println(" edgar manda:"+json);
			Empresa empresa = new Empresa();
			//empresa.setDescripcion("DHL__");
			empresaDao.save(empresa);
//				} else {
//					response.sendError(403);
//				}
			}
		
		
		 @RequestMapping(value = { "/find/{id}" }, method = RequestMethod.POST, consumes = "Application/Json")
			public void findFolio(HttpServletResponse response, HttpServletRequest request,
					@PathVariable Long id) throws IOException {
			   System.out.println("xxxxxxxxx");
				AsignadorDeCharset.asignar(request, response);
				//DetalleDiscrepanciaVo dd = getDetalleDiscrepancia(id);
				System.out.println("aaaaaaaaaa");
				//System.out.println("find/id"+dd);
				Empresa e=empresaDao.consult(id);
				response.getWriter().println(JsonConvertidor.toJson(e));
			
			}

		
	
}
