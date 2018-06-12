package com.tikal.mensajeria.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tikal.mensajeria.dao.EnvioDao;
import com.tikal.mensajeria.dao.GuiaDao;
import com.tikal.mensajeria.dao.UsuarioDao;

@Controller
@RequestMapping("/guia")
public class GuiaController {
	
	@Qualifier("usuarioDao")
	UsuarioDao usuarioDao;
	
	@Qualifier("guiaDao")
	GuiaDao guiaDao;
	
	@Qualifier("envioDao")
	EnvioDao envioDao;
	
	

}
