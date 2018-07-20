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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Index;
import com.tikal.mensajeria.dao.EnvioDao;
import com.tikal.mensajeria.dao.GuiaDao;
import com.tikal.mensajeria.dao.SucursalDao;
import com.tikal.mensajeria.dao.UsuarioDao;
import com.tikal.mensajeria.modelo.entity.ContadorServicio;
import com.tikal.mensajeria.modelo.entity.Guia;
import com.tikal.mensajeria.modelo.login.Sucursal;
import com.tikal.mensajeria.modelo.vo.ResumenGuia;
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
		String gui = inicio.substring(0,16);
		
		Integer ini= Integer.parseInt(inicio.substring(16));
		Integer fini= Integer.parseInt(fin.substring(16));
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
	
	
	@RequestMapping(value = { "/addM/{inicio}/{fin}/{tipoGuia}" },  method = RequestMethod.GET)
	public void altaGuias(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable String inicio , @PathVariable String fin, @PathVariable String tipoGuia)throws IOException {
	
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
					guia.setTipoGuia(tipoGuia);
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
			@PathVariable String inicio , @PathVariable String fin,@PathVariable Long idSucursal )throws IOException {
	
	//				if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		AsignadorDeCharset.asignar(request, response);
		String gui = inicio.substring(0,15);		
		Integer ini= Integer.parseInt(inicio.substring(15));
		Integer fini= Integer.parseInt(fin.substring(15));
		
		for (int i=ini; i<=fini; i++) {
			Guia guia = guiaDao.getByNumero(gui+i);
			guia.setEstatus("ASIGNADA");
			//guia.setNumero(gui+i);
			System.out.println("*******ini:"+i);
			System.out.println("------------guia:"+gui+i);
		//	guia.setNumero(i);
			//guia.setIdSucursal(Long.valueOf("9999"));
			guia.setIdSucursal(sucursalDao.consult(idSucursal).getId());
			guia.setSucursal(sucursalDao.consult(idSucursal).getNombre());
			//guia.setSucursal(sucursalDao.consult(suc).getNombre());
			guiaDao.update(guia); 
		}
		
		
//		Guia guia = guiaDao.getByNumero(inicio);
//		for (int i=inicio; i<= fin; i++) {
//			Guia guia = guiaDao.getByNumero(i);
//			guia.setIdSucursal(idSucursal);
//			guia.setEstatus("ASIGNADA");
//			guiaDao.update(guia);
//			//System.out.println(" yisus manda:"+json);
//		}
		
	}
	
	@RequestMapping(value = { "/cancelar/{idGuia}" },  method = RequestMethod.GET)
	public void asignaM(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable Long idGuia )throws IOException  {
	
	//				if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		AsignadorDeCharset.asignar(request, response);
		
			Guia guia = guiaDao.consult(idGuia);
			if (guia.getEstatus().equals("EN ENVIO")){
				System.out.println("la guia no se puede cancelar");
			}else{
				guia.setEstatus("CANCELADA");
				guiaDao.update(guia);
			}
			//System.out.println(" yisus manda:"+json);
		
		
	}
	 @RequestMapping(value = { "/getGuia/{tipoGuia}/{empresa}/{userName}" },  method = RequestMethod.GET, produces = "application/json")
		public void getGuia(HttpServletResponse response, HttpServletRequest request,@PathVariable String empresa, @PathVariable String tipoGuia, @PathVariable String userName) throws IOException {
	   System.out.println("dame guia para:"+empresa);
	////   Guia g=guiaDao.getByEstSucK("ASIGNADA", usuarioDao.consultarUsuario(userName).getIdSucursal(), kilataje);
	   String numeroGuia="0000000000-00000000000";
	//   System.out.println("dame guia:"+g);
	   if (empresa.equals("MERVEL")){
		   ContadorServicio f=ObjectifyService.ofy().load().type(ContadorServicio.class).list().get(0);
			//int numero = (int) (Math.random() * 9999) + 1;
		   System.out.println("++++++++ guia:"+f.getGuia());
			numeroGuia=("0101343450-7717189"+f.getGuia().toString());
			//dato.setIdServicio(numero);
			f.incrementarGuia();
			ObjectifyService.ofy().save().entity(f).now(); 
		   
		   
	   }else{
		   numeroGuia=guiaDao.getByEstSuc(tipoGuia, usuarioDao.consultarUsuario(userName).getIdSucursal()).getNumero();
	   }
	   
	   System.out.println("dame guia:"+numeroGuia);
	  //response.getWriter().println(g.getNumero());
	  response.getWriter().println(numeroGuia );
	
	  
	  
//		if (envio.getEmpresa().equals("MERVEL")){				
//		ContadorServicio f=ObjectifyService.ofy().load().type(ContadorServicio.class).list().get(0);
//		//int numero = (int) (Math.random() * 9999) + 1;
//		envio.setGuia(f.getGuia().toString());
//		//dato.setIdServicio(numero);
//		f.incrementarGuia();
//		ObjectifyService.ofy().save().entity(f).now(); 
//	}

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
	 
	 @RequestMapping(value = { "/deleteAll" }, method = RequestMethod.GET)
		public void deleteAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			//List<Guia> lista = guiaDao.findAll();
			guiaDao.deleteAll();
			//response.getWriter().println(JsonConvertidor.toJson(lista));

		}

	 
	 @RequestMapping(value = { "/getResumenGuias" }, method = RequestMethod.GET, produces = "application/json")
		public void getResumen(HttpServletResponse response, HttpServletRequest request) throws IOException {
		 System.out.println("GET RESUMEN GUIAS:");
			AsignadorDeCharset.asignar(request, response);
			List<Sucursal> sucursales= new ArrayList<Sucursal>();
			sucursales= sucursalDao.findAll();
			List<ResumenGuia> res= new ArrayList<ResumenGuia>();
			
		//	List<Guia> guias = guiaDao.getResumen();
		//	System.out.println("lista de resumen"+guias);
			List<String> tipos= new ArrayList<String>();
			tipos.add("1Kg Sobre Sig Dia");	tipos.add("1Kg Paquete Sig Dia");tipos.add("11:30 Sig Dia");tipos.add("11:30 Sobre Sig Dia");tipos.add("3Kg Sig Dia");tipos.add("5Kg Terrestre");
			tipos.add("10Kg Terrestre");tipos.add("15Kg Terrestre");tipos.add("20Kg Terrestre");tipos.add("25Kg Terrestre");tipos.add("30Kg Terrestre");tipos.add("35Kg Terrestre");
			tipos.add("40Kg Terrestre");tipos.add("45Kg Terrestre");
			String estatusAsignada= "ASIGNADA";
			String estatusNo= "NO ASIGNADA";
			System.out.println("TIPOS:"+tipos.toString());
			for (Sucursal s:sucursales){
				System.out.println("SUCURSAL:"+s.getNombre());
				for (String tipo: tipos){
					System.out.println("TIPO:"+tipo);
					List<Guia> guias = guiaDao.getEstSucTipo(s.getNombre(),tipo);
					System.out.println("SIZE:"+guias.size());
					ResumenGuia rg= new ResumenGuia();
					if (guias.size()==0){
						break;
					}else{
						rg.setInicia(guias.get(0).getNumero());
						rg.setTermina(guias.get(guias.size()-1).getNumero());
						rg.setEstatus("ASIGNADA");
						rg.setSucursal(s.getNombre());
						rg.setTipoGuia(tipo);	
						res.add(rg);
						System.out.println("res:"+res);
					}
				}
				
			}
			////////////////////////////////////////las no asignadas
			List<Guia> noA= new ArrayList<Guia>();
			noA=guiaDao.getByEstatus("NO ASIGNADA");
			if (noA.size()==0){
				System.out.println("no hay guias NO ASIGNADAS...");
			}else{
				ResumenGuia rg= new ResumenGuia();
				rg.setInicia(noA.get(0).getNumero());
				rg.setTermina(noA.get(noA.size()-1).getNumero());
				rg.setEstatus("NO ASIGNADA");
				rg.setSucursal("MASTER");
				rg.setTipoGuia("-");	
				res.add(rg);
			}
			
			System.out.println("res completo:"+res);
//			
//			List<Guia> lista = guiaDao.getBySucursal(idSucursal);
//			if (lista == null) {
//				lista = new ArrayList<Guia>();
//			}
			response.getWriter().println(JsonConvertidor.toJson(res));

		}
	 
	
}
