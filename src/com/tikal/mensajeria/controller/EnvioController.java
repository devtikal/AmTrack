package com.tikal.mensajeria.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.googlecode.objectify.ObjectifyService;
import com.tikal.mensajeria.dao.EnvioDao;
import com.tikal.mensajeria.dao.GuiaDao;
import com.tikal.mensajeria.dao.PaqueteDao;
import com.tikal.mensajeria.dao.PersonaDao;
import com.tikal.mensajeria.dao.SucursalDao;
import com.tikal.mensajeria.dao.UsuarioDao;
import com.tikal.mensajeria.dao.VentaDao;
import com.tikal.mensajeria.formatos.pdf.GeneraGuiaEstafeta;
import com.tikal.mensajeria.formatos.pdf.GeneraGuiaMervel;
import com.tikal.mensajeria.formatos.pdf.GeneraTicket;
import com.tikal.mensajeria.modelo.entity.Contador;
import com.tikal.mensajeria.modelo.entity.ContadorServicio;
import com.tikal.mensajeria.modelo.entity.Envio;
import com.tikal.mensajeria.modelo.entity.Guia;
import com.tikal.mensajeria.modelo.entity.Paquete;
import com.tikal.mensajeria.modelo.entity.Persona;
import com.tikal.mensajeria.modelo.entity.Venta;
import com.tikal.mensajeria.modelo.login.Sucursal;
import com.tikal.mensajeria.modelo.login.Usuario;
//import com.tikal.mensajeria.modelo.vo.EnvioVo;
import com.tikal.mensajeria.util.AsignadorDeCharset;
import com.tikal.util.JsonConvertidor;

@Controller
@RequestMapping(value = { "/envio"})
public class EnvioController {
	
	@Autowired
	@Qualifier("usuarioDao")
	UsuarioDao usuarioDao;
	
	@Autowired
	@Qualifier("sucursalDao")
	SucursalDao sucursalDao;
	
	@Autowired
	@Qualifier("paqueteDao")
	PaqueteDao paqueteDao;
	
	@Autowired
	
	@Qualifier("personaDao")
	PersonaDao personaDao;
	
	@Autowired
	@Qualifier("envioDao")
	EnvioDao envioDao;
	
	@Autowired
	@Qualifier("guiaDao")
	GuiaDao guiaDao;
	
	@Autowired
	@Qualifier("ventaDao")
	VentaDao ventaDao;
	
	
	@RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo Envio prueba");

	    }
	
	@RequestMapping(value = { "/add_/{usuario}" }, method = RequestMethod.GET)
	public void addEnvio(HttpServletRequest request, HttpServletResponse response, @PathVariable String usuario)throws IOException {
//		if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		//	AsignadorDeCharset.asignar(request, response);
		//	System.out.println(" edgar manda:"+json);
			Envio envio = new Envio();
			Paquete p = new Paquete();
			Usuario u = new Usuario();
//			envio.("Juan Perez Acuña");
//			
			Persona d= new Persona();Persona c= new Persona();
			d=personaDao.consult(Long.valueOf("4877433580814336"));
			c=personaDao.consult(Long.valueOf("4595958604103680"));
//			d.setNombre("Oscar vazquez");
//			d.setId(4877433580814336);
//			//d.setaMaterno("Vazqu");
//		//	d.setaMaterno("Pantoja");
//			d.setCalle("Ejercito del trabajo");
//			d.setNoExterior("130");
//			d.setNoInterior("0");
//			d.setColonia("guadalupe san buena");
//			d.setLocalidad("Toluca");
//			d.setMunicipio("Toluca");
//			d.setEstado("Mexico");
//			d.setCodigoPostal(50110);
//			d.setTelefono("7222222222");
			
//			destinatarioDao.save(d);
//			
			
			envio.setDestinatario(d);
			envio.setCliente(c);
			
			p.setAlto(30.00);p.setAncho(30.00); p.setLargo(30.00);
			p.setDescripcion("Libros");
			p.setPeso(312.00);
			p.setTipoPaquete("paquete");
			
			paqueteDao.save(p);
			
			envio.setPaquete(p);
			
//			e.setClave("fed");
//			e.setDescripcion("FEDEX_");
//			empresaDao.save(e);
			envio.setTipoServicio("NACIONAL");
			envio.setEmpresa("MERVEL");
		//	envio.setFecha("05/06/2018 18:03:45");
			Contador ct= new Contador();
		//	envio.setFolio(c.getFolio().toString());
			//Integer guia = buscaGuia(username);
			//guiaDao.
			//Usuario user= usuarioDao.consultarUsuario(usuario);
			//System.out.println("esta en daoimpl envio  get by status Suc:"+usuarioDao.consultarUsuario(usuario).getIdSucursal());
			//Guia g=guiaDao.getByEstSuc("ASIGNADA", usuarioDao.consultarUsuario(usuario).getIdSucursal());
			//envio.setGuia(g.getNumero());
			envio.setPrecio(65.50);
			envio.setRastreo("878987657");
			envio.setTipoEnvio("Dia siguiente");
		//	envio.setTotal(312.50);
			   

			//u= usuarioDao.consult("")
			
			//envio.setUsuario(usuario);
			envioDao.save(envio);
		////	g.setEstatus("EN ENVIO");
		//	guiaDao.update(g);
			ct.incremeta();
	//}
//			response.sendError(403);
//		}
	}
	

	@RequestMapping(value = { "/add/{idVenta}/{usuario}" }, method = RequestMethod.POST,consumes = "Application/Json")
	public void crearEnvio(HttpServletRequest request, HttpServletResponse response,@RequestBody String json, 
			@PathVariable String usuario, @PathVariable Long idVenta)throws IOException {
//		if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
			AsignadorDeCharset.asignar(request, response);
			System.out.println(" Yisus manda:"+json);
		//	EnvioVo evo = (EnvioVo) JsonConvertidor.fromJson(json, EnvioVo.class);
			Envio ef = (Envio) JsonConvertidor.fromJson(json, Envio.class);
			//System.out.println("lista de permisos:"+perfil.getPermisos());
			Paquete p = new Paquete();
			Envio envio = new Envio();
			Persona c= new Persona();
			
			//Destinatario des= new Destinatario();
			//Empresa e=new Empresa();
		//	e=empresaDao.getByNombre(evo.getEmpresa());
			c=ef.getCliente();
			System.out.println("cliente:"+c.getNombre());
			if (c.getCodigoPostal()==null || c.getCodigoPostal()==0){
				c.setCodigoPostal(0);
			}
			personaDao.save(c);
					
			p=ef.getPaquete();
			if (p.getAlto()==null){ p.setAlto(Double.valueOf("0.00"));}
			if (p.getAncho()==null){ p.setAncho(Double.valueOf("0.00"));}
			if (p.getLargo()==null){ p.setLargo(Double.valueOf("0.00"));}
			if (p.getPeso()==null){ p.setPeso(Double.valueOf("0.00"));}
			paqueteDao.save(p);
						
			envio.setCliente(c);
			
			envio.setPaquete(p);
			
			envio.setEmpresa(ef.getEmpresa());
			if(envio.getEmpresa().equals("MERVEL") || envio.getEmpresa().equals("ESTAFETA")){
				Persona d= new Persona();
				d=ef.getDestinatario();
				System.out.println(" destinatario"+d.getNombre());
				personaDao.save(d);
				envio.setDestinatario(d);
			}
			if (ef.getCostoSeguro()==null){
				envio.setCostoSeguro(Double.valueOf("0.00"));
			}else{
				envio.setCostoSeguro(ef.getCostoSeguro());
			}
			
		
			
//			Guia g=guiaDao.getByEstSuc("ASIGNADA", usuarioDao.consultarUsuario(usuario).getIdSucursal(), kilataje);
//			if (g==null){
//			  System.out.println("no hay guias asignadas a esta sucursal");
//			}
			envio.setGuia(ef.getGuia());
			
			
			
			//envio.setGuia(evo.getGuia());
			
			envio.setPrecio(ef.getPrecio());
			envio.setRastreo(ef.getRastreo());
			envio.setTipoEnvio(ef.getTipoEnvio());
			envio.setTipoServicio(ef.getTipoServicio());
			envio.setMateriales(ef.getMateriales());
			envio.setTotalEnvio(ef.getCostoSeguro()+ef.getPrecio());
			envioDao.save(envio);
			if (envio.getEmpresa().equals("ESTAFETA")){		
				System.out.println("guia numero2"+ef.getGuia());
				Guia g=guiaDao.getByNumero(ef.getGuia());
				g.setEstatus("EN ENVIO");
				guiaDao.update(g);
			}			
			if (envio.getEmpresa().equals("MERVEL")){		
				System.out.println("guia MERVEL:"+ef.getGuia());
				 ContadorServicio f=ObjectifyService.ofy().load().type(ContadorServicio.class).list().get(0);
				 System.out.println("guia MERVEL otra vez:"+f.getGuia());
				 f.incrementarGuia();
				 ObjectifyService.ofy().save().entity(f).now(); 
				
			}
			
			
			//folio.incremeta();
			
			Venta v=ventaDao.consult(idVenta);
			if (v.getEnvios()==null){
			//List<Long> ids=v.getEnvios();
			System.out.println("lista de eids de envios"+v.getEnvios());
			//if (ids.size()==0){
				List<Long> ids= new ArrayList<Long>();
				ids.add(envio.getId());
				v.setEnvios(ids);
				v.setCantidad(ids.size());
				//System.out.println("precio envio"+envio.getPrecio());
				v.setTotal(v.getTotal()+envio.getTotalEnvio());
				ventaDao.update(v); 
				System.out.println("total venta"+v.getTotal());
				System.out.println("lista de eids de envios"+ids);
			}else{
				List<Long> ids=v.getEnvios();
				ids.add(envio.getId());
				v.setEnvios(ids);
				v.setCantidad(ids.size());
				v.setTotal(v.getTotal()+envio.getTotalEnvio());
				ventaDao.update(v); 
				System.out.println("lista ids de envios"+ids);
			}
			
			     
			
	}
	   
	 
	  	   @RequestMapping(value = { "/generaGuiaMervel/{idEnvio}/{idVenta}/{userName}" },  method = RequestMethod.GET, produces = "application/pdf")
	 		public void generaGuia(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idEnvio
	 				, @PathVariable Long idVenta, @PathVariable String userName) throws IOException {
	 	   System.out.println("genera guiiiiaaaaaaaaa MERVEL");
//	 	   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 20, sessionDao,userName)){
	 	   Venta v = ventaDao.consult(idVenta);
			  Envio envio = envioDao.consult(idEnvio) ; 
			  envio=quitaNull(envio);
			  
	 		   response.setContentType("Application/Pdf");
	 		  Envio e = envioDao.consult(idEnvio) ;  
	 	        File newPdfFile = new File(idEnvio+".pdf");		 
	 	        if (!newPdfFile.exists()){
	 	            try {
	 	            	newPdfFile.createNewFile();
	 	            } catch (IOException ioe) {
	 	                System.out.println("(Error al crear el fichero nuevo ......)" + ioe);
	 	            }
	 	        }
	        
	 	     //   Sucursal s= sucursalDao.consult(usuarioDao.consultarUsuario(userName).getIdSucursal());
	 	     //   Paquete p = e.getPaquete();
	 	     //   String des = e.paquete.paqueteDao.consult(e.getPaquete().getDescripcion());
	 	        System.out.println("Empiezo a generar pdf...." );
	 	    	GeneraGuiaMervel ggm = new GeneraGuiaMervel(v,envio, response.getOutputStream());
	 	    //ystem.out.println("nombre de archivo para edgar:"+tik.getNombreArchivo().substring(10) );
	 	    	//response.getWriter().println((vpdf.getNombreArchivo().substring(10)));
	 	    	  response.getOutputStream().flush();
	 		        response.getOutputStream().close();
	 	    	//generaOrdenPdf.GeneraOrdenPdf(new File(ox.getNombreArchivo()));
	 	    	//generaOrdenPdf.GeneraOrdenPdf(ox));
//	 	   }else{
//	 			response.sendError(403);
//	 		}
	 	}
	   
	  	 @RequestMapping(value = { "/generaGuiaEstafeta/{idEnvio}/{idVenta}/{userName}" },  method = RequestMethod.GET, produces = "application/pdf")
	 		public void generaGuiaE(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idEnvio
	 				, @PathVariable Long idVenta, @PathVariable String userName) throws IOException {
	 	   System.out.println("genera guiiiiaaaaaaaaa ESTAFETA");
//	 	   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 20, sessionDao,userName)){
	 	   Venta v = ventaDao.consult(idVenta);
			  Envio envio = envioDao.consult(idEnvio) ; 
			  
	 		   response.setContentType("Application/Pdf");
	 		  Envio e = envioDao.consult(idEnvio) ;  
	 		  e=quitaNull(e);
	 		 System.out.println("cliente.calle:");
	 		System.out.println("cliente.colinia:");
	 		System.out.println("cliente.municipio:");
	 		System.out.println("cliente.localidad:");
	 		System.out.println("dest.calle:");
	 		System.out.println("dest.colonia:");
	 		System.out.println("dest.municipi:");
	 		System.out.println("dest.localidad:");
	 		
	 		  
	 		  
	 	        File newPdfFile = new File(idEnvio+".pdf");		 
	 	        if (!newPdfFile.exists()){
	 	            try {
	 	            	newPdfFile.createNewFile();
	 	            } catch (IOException ioe) {
	 	                System.out.println("(Error al crear el fichero nuevo ......)" + ioe);
	 	            }
	 	        }
	        
	 	   
	 	        System.out.println("Empiezo a generar pdf...." );
	 	    	GeneraGuiaEstafeta gge = new GeneraGuiaEstafeta(v,envio, response.getOutputStream());
	 	    //ystem.out.println("nombre de archivo para edgar:"+tik.getNombreArchivo().substring(10) );
	 	    	//response.getWriter().println((vpdf.getNombreArchivo().substring(10)));
	 	    	  response.getOutputStream().flush();
	 		        response.getOutputStream().close();
	 	    	//generaOrdenPdf.GeneraOrdenPdf(new File(ox.getNombreArchivo()));
	 	    	//generaOrdenPdf.GeneraOrdenPdf(ox));
//	 	   }else{
//	 			response.sendError(403);
//	 		}
	 	}
	  	   
	  	   
	  	   
	   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Envio> lista = envioDao.findAll();
			if (lista == null) {
				lista = new ArrayList<Envio>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	   @RequestMapping(value = { "/getEnviosxVenta/{idVenta}" }, method = RequestMethod.GET, produces = "application/json")
		public void enviosporventa(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idVenta) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Long> ids = ventaDao.consult(idVenta).getEnvios();
			List<Envio> envios= new ArrayList<Envio>();
			for (Long id:ids){
				Envio e=envioDao.consult(id);
				envios.add(e);
			}
			
			response.getWriter().println(JsonConvertidor.toJson(envios));

		}
		 
//	   @RequestMapping(value = { "/reporte/{inicio}/{fin}" }, method = RequestMethod.GET, produces = "application/json")
//		public void findAll(HttpServletResponse response, HttpServletRequest request, @PathVariable Integer inicio , @PathVariable Integer fin) throws IOException {
//			AsignadorDeCharset.asignar(request, response);
//			for (int i=inicio; i<= fin; i++) {
//				Guia guia = guiaDao.getByNumero(i);
//				guia.setIdSucursal(idSucursal);
//				guiaDao.update(guia);
//				//System.out.println(" yisus manda:"+json);
//			}
//
//		}
	   
	   @RequestMapping(value = { "/delete/{idEnvio}/{idVenta}" }, method = RequestMethod.GET)
		public void deleteVenta(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idEnvio, @PathVariable Long idVenta) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			double precio= envioDao.consult(idEnvio).getPrecio();
			
			
			Venta v = ventaDao.consult(idVenta);
			Envio e = envioDao.consult(idEnvio);
			System.out.println("empresa :"+e.getEmpresa());
			if (e.getEmpresa().equals("ESTAFETA")){
				System.out.println("empresa estafeta, si entra guia:"+e.getGuia());
				
				//Long guia=Long.parseLong(e.getGuia().substring(0,23));
			//	System.out.println(" guia:"+guia);
				Guia g=guiaDao.getByNumero(e.getGuia());
				g.setEstatus("ASIGNADA");
				guiaDao.update(g);
			}
			
			envioDao.delete(e);
			List<Long> ids = ventaDao.consult(idVenta).getEnvios();
			ids.remove(idEnvio);
			if (ids.size()==0){
				ids = new ArrayList<Long>();
			}
			
			System.out.println("lista de envios nueva:"+ids);
			v.setEnvios(ids);
			System.out.println("total venta:"+v.getTotal());
			System.out.println("precio envio:"+precio);
		//	System.out.println("cantidad de venta:"+e.getPrecio());
			v.setTotal(v.getTotal() - precio);
		
			v.setCantidad(ids.size());
			ventaDao.update(v);
			//Envio e=envioDao.consult(id);
			envioDao.delete(e);
			
			System.out.println("nuevo total de venta:"+v.getTotal());
//			List<Envio> envios= new ArrayList<Envio>();
//			for (Long id:ids){
//				Envio e=envioDao.consult(id);
//				envios.add(e);
//			}
			
			
			envioDao.delete(e);
			response.getWriter().println(JsonConvertidor.toJson(ids));

		}
	
	   
		public Envio quitaNull(Envio e) {
			 if (e.getCliente().getCalle()==null){
	 			  e.getCliente().setCalle("");	 			  
	 		  }
			 if (e.getCliente().getNoExterior()==null){
	 			  e.getCliente().setNoExterior("");	 			  
	 		  }
			 if (e.getCliente().getNoInterior()==null){
	 			  e.getCliente().setNoInterior("");	 			  
	 		  }
			 
			 if (e.getCliente().getColonia()==null){
	 			  e.getCliente().setColonia("");	 			  
	 		  }
			 
			 if (e.getCliente().getLocalidad()==null){
	 			  e.getCliente().setLocalidad("");	 			  
	 		  }
			 if (e.getCliente().getMunicipio()==null){
	 			  e.getCliente().setMunicipio("");	 			  
	 		  }
			 if (e.getCliente().getEstado()==null){
	 			  e.getCliente().setEstado("");	 			  
	 		  }
			 if (e.getCliente().getTelefono()==null){
	 			  e.getCliente().setTelefono("");	 			  
	 		  }
			 if (e.getDestinatario().getEnAtencion()==null){
	 			  e.getDestinatario().setEnAtencion("");	 			  
	 		  }
			 if (e.getDestinatario().getCalle()==null){
	 			  e.getDestinatario().setCalle("");	 			  
	 		  }
			 if (e.getDestinatario().getNoExterior()==null){
	 			  e.getDestinatario().setNoExterior("");	 			  
	 		  }
			 if (e.getDestinatario().getNoInterior()==null){
	 			  e.getDestinatario().setNoInterior("");	 			  
	 		  }
			 if (e.getDestinatario().getColonia()==null){
	 			  e.getDestinatario().setColonia("");	 			  
	 		  }
			 
			 if (e.getDestinatario().getLocalidad()==null){
	 			  e.getDestinatario().setLocalidad("");	 			  
	 		  }
			 if (e.getDestinatario().getMunicipio()==null){
	 			  e.getDestinatario().setMunicipio("");	 			  
	 		  }
			 if (e.getDestinatario().getEstado()==null){
	 			  e.getDestinatario().setEstado("");	 			  
	 		  }
			 if (e.getDestinatario().getTelefono()==null){
	 			  e.getDestinatario().setTelefono("");	 			  
	 		  }
			 if (e.getDestinatario().getReferencias()==null){
	 			  e.getDestinatario().setReferencias("");	 			  
	 		  }
			 envioDao.save(e);
			 return e;
		}
}


