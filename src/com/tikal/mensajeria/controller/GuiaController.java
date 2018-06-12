package com.tikal.mensajeria.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
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
	
	@Qualifier("usuarioDao")
	UsuarioDao usuarioDao;
	
	@Qualifier("guiaDao")
	GuiaDao guiaDao;
	
	@Qualifier("envioDao")
	EnvioDao envioDao;
	
	
	
	@RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo Guia prueba");

	    }
	
	@RequestMapping(value = { "/addMassive" },  method = RequestMethod.POST, consumes = "Application/Json")
	public void altaGuias(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody String json)throws IOException {
	
	//				if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		AsignadorDeCharset.asignar(request, response);
		System.out.println(" yisus manda:"+json);
		Guia guia = new Guia();
		//empresa.setDescripcion("DHL__");
		guiaDao.save(guia);
//			} else {
//				response.sendError(403);
//			}
		}

}
