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

import com.tikal.mensajeria.dao.EnvioDao;
import com.tikal.mensajeria.dao.GuiaDao;
import com.tikal.mensajeria.dao.UsuarioDao;
import com.tikal.mensajeria.modelo.entity.Empresa;
import com.tikal.mensajeria.modelo.entity.Guia;
import com.tikal.util.AsignadorDeCharset;

@Controller
@RequestMapping("/guia")
public class GuiaController {
	

	@Autowired
	@Qualifier("usuarioDao")
	UsuarioDao usuarioDao;
	

	@Autowired
	@Qualifier("guiaDao")
	GuiaDao guiaDao;
	

	@Autowired
	@Qualifier("envioDao")
	EnvioDao envioDao;
	
	
	
	@RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo Guia prueba");

	    }
	
	@RequestMapping(value = { "/addM/{inicio}/{fin}" },  method = RequestMethod.GET)
	public void altas(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable Integer inicio , @PathVariable Integer fin)throws IOException {
	
	//				if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		AsignadorDeCharset.asignar(request, response);
		//System.out.println(" yisus manda:"+json);
		
		for (int i=inicio; i<= fin; i++) {
			Guia guia = new Guia();
			guia.setEstatus("NO ASIGNADA");
			guia.setNumero(i);
			//guia.setIdSucursal(Long.valueOf("9999"));
			guia.setIdSucursal(usuarioDao.consultarUsuario("root").getIdSucursal());
			guiaDao.save(guia); 
		}
	
//			} else {
//				response.sendError(403);
//			}
		}
	
	
	@RequestMapping(value = { "/addMassive/{inicio}/{fin}" },  method = RequestMethod.POST, consumes = "Application/Json")
	public void altaGuias(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody String json,@PathVariable Integer inicio , @PathVariable Integer fin)throws IOException {
	
	//				if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		AsignadorDeCharset.asignar(request, response);
		System.out.println(" yisus manda:"+json);
		Long suc=usuarioDao.consultarUsuario("root").getIdSucursal();
		for (int i=inicio; i<= fin; i++) {
			Guia guia = new Guia();
			guia.setEstatus("NO ASIGNADA");
			guia.setNumero(i);
			guia.setIdSucursal(suc);
			//guia.setIdSucursal(Long.valueOf("9999"));
			guiaDao.save(guia);
		}
	
//			} else {
//				response.sendError(403);
//			}
		}

	@RequestMapping(value = { "/asignar/{idSucursal}/{idGuia}" },  method = RequestMethod.GET)
	public void asigna(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable Long idSucursal , @PathVariable Long idGuia)throws IOException {
	
	//				if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		AsignadorDeCharset.asignar(request, response);
		Guia guia = guiaDao.consult(idGuia);
		guia.setIdSucursal(idSucursal);
		guiaDao.update(guia);
		//System.out.println(" yisus manda:"+json);
		
	}
	
	@RequestMapping(value = { "/asignar/{inicio}/{fin}/{idSucursal}" },  method = RequestMethod.GET)
	public void asignaM(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable Integer inicio , @PathVariable Integer fin,@PathVariable Long idSucursal )throws IOException {
	
	//				if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		AsignadorDeCharset.asignar(request, response);
		for (int i=inicio; i<= fin; i++) {
			Guia guia = guiaDao.getByNumero(i);
			guia.setIdSucursal(idSucursal);
			guiaDao.update(guia);
			//System.out.println(" yisus manda:"+json);
		}
		
	}
	
	
}
