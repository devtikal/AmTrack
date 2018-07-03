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

import com.tikal.mensajeria.dao.EnvioDao;
import com.tikal.mensajeria.dao.GuiaDao;
import com.tikal.mensajeria.dao.SucursalDao;
import com.tikal.mensajeria.dao.UsuarioDao;
import com.tikal.mensajeria.modelo.entity.Empresa;
import com.tikal.mensajeria.modelo.entity.Guia;
import com.tikal.mensajeria.util.JsonConvertidor;
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
	@Qualifier("sucursalDao")
	SucursalDao sucursalDao;

	@Autowired
	@Qualifier("envioDao")
	EnvioDao envioDao;
	
	
	
	@RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo Guia prueba");

	    }
	
	@RequestMapping(value = { "/addM_/{inicio}/{fin}" },  method = RequestMethod.GET)
	public void altas(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable String inicio , @PathVariable String fin)throws IOException {
	
	//if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		AsignadorDeCharset.asignar(request, response);
		String gui = inicio.substring(0,15);
		
		Integer ini= Integer.parseInt(inicio.substring(15));
		Integer fini= Integer.parseInt(fin.substring(15));
		System.out.println("-------------- cuerpo guia:"+gui);
		System.out.println("-------------- ini:"+ini);
		System.out.println("-------------- fin:"+fini);
//		int ini=Integer.parseInt(inicio);
	//	int 1==9;
//		int f=Integer.parseInt(fin);
		for (int i=ini; i<=fini; i++) {
			Guia guia = new Guia();
			guia.setEstatus("NO ASIGNADA");
			guia.setNumero(gui+i);
			System.out.println("*******ini:"+i);
			System.out.println("------------guia:"+gui+i);
		//	guia.setNumero(i);
			//guia.setIdSucursal(Long.valueOf("9999"));
			guia.setIdSucursal(usuarioDao.consultarUsuario("root").getIdSucursal());
			//guia.setSucursal(sucursalDao.consult(suc).getNombre());
			guiaDao.save(guia); 
		}
	
//			} else {
//				response.sendError(403);
//			}
		}
	
	
	@RequestMapping(value = { "/addM/{inicio}/{fin}" },  method = RequestMethod.GET)
	public void altaGuias(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable String inicio , @PathVariable String fin)throws IOException {
	
		//if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
				AsignadorDeCharset.asignar(request, response);
				String gui = inicio.substring(0,15);
				
				Integer ini= Integer.parseInt(inicio.substring(15));
				Integer fini= Integer.parseInt(fin.substring(15));
				System.out.println("-------------- cuerpo guia:"+gui);
				System.out.println("-------------- ini:"+ini);
				System.out.println("-------------- fin:"+fini);
//				int ini=Integer.parseInt(inicio);
			//	int 1==9;
//				int f=Integer.parseInt(fin);
				for (int i=ini; i<=fini; i++) {
					Guia guia = new Guia();
					guia.setEstatus("NO ASIGNADA");
					guia.setNumero(gui+i);
					System.out.println("*******ini:"+i);
					System.out.println("------------guia:"+gui+i);
				//	guia.setNumero(i);
					//guia.setIdSucursal(Long.valueOf("9999"));
					guia.setIdSucursal(usuarioDao.consultarUsuario("root").getIdSucursal());
					//guia.setSucursal(sucursalDao.consult(suc).getNombre());
					guiaDao.save(guia); 
				}
			
//					} else {
//						response.sendError(403);
//					}
		}

	@RequestMapping(value = { "/asignar/{idSucursal}/{idGuia}" },  method = RequestMethod.GET)
	public void asigna(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable Long idSucursal , @PathVariable Long idGuia)throws IOException {
	
	//				if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		AsignadorDeCharset.asignar(request, response);
		Guia guia = guiaDao.consult(idGuia);
		guia.setIdSucursal(idSucursal);
		guia.setSucursal(sucursalDao.consult(idSucursal).getNombre());
		guia.setEstatus("ASIGNADA");
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
			guia.setEstatus("ASIGNADA");
			guiaDao.update(guia);
			//System.out.println(" yisus manda:"+json);
		}
		
	}
	
	@RequestMapping(value = { "/cancelar/{idGuia}" },  method = RequestMethod.GET)
	public void asignaM(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable Long idGuia )throws IOException {
	
	//				if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		AsignadorDeCharset.asignar(request, response);
		
			Guia guia = guiaDao.consult(idGuia);
			guia.setEstatus("CANCELADA");
			guiaDao.update(guia);
			//System.out.println(" yisus manda:"+json);
		
		
	}
	 @RequestMapping(value = { "/getGuia/{userName}" },  method = RequestMethod.GET, produces = "application/json")
		public void getGuia(HttpServletResponse response, HttpServletRequest request, @PathVariable String userName) throws IOException {
	   System.out.println("dame guia");
	   
	   Guia g=guiaDao.getByEstSuc("ASIGNADA", usuarioDao.consultarUsuario(userName).getIdSucursal());
	   
	   System.out.println("dame guia:"+g);
	  //response.getWriter().println(g.getNumero());
	  response.getWriter().println(JsonConvertidor.toJson(g));
	

//	   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 20, sessionDao,userName)){
		  
}
	
	 @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Guia> lista = guiaDao.findAll();
			if (lista == null) {
				lista = new ArrayList<Guia>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
}
