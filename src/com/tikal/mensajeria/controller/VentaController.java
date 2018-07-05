package com.tikal.mensajeria.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.tikal.mensajeria.dao.PaqueteDao;
import com.tikal.mensajeria.dao.PersonaDao;
import com.tikal.mensajeria.dao.SucursalDao;
import com.tikal.mensajeria.dao.UsuarioDao;
import com.tikal.mensajeria.dao.VentaDao;
import com.tikal.mensajeria.formatos.pdf.GeneraTicket;
import com.tikal.mensajeria.modelo.entity.Contador;
import com.tikal.mensajeria.modelo.entity.Envio;
import com.tikal.mensajeria.modelo.entity.Guia;
import com.tikal.mensajeria.modelo.entity.Paquete;
import com.tikal.mensajeria.modelo.entity.Persona;
import com.tikal.mensajeria.modelo.entity.Venta;
import com.tikal.mensajeria.modelo.login.Sucursal;
import com.tikal.mensajeria.modelo.login.Usuario;
import com.tikal.mensajeria.modelo.vo.EnvioVo;
import com.tikal.mensajeria.modelo.vo.FechasVo;
import com.tikal.mensajeria.modelo.vo.ReporteVo;
import com.tikal.mensajeria.reportes.ReporteXls;
import com.tikal.mensajeria.util.JsonConvertidor;
import com.tikal.util.AsignadorDeCharset;

@Controller
@RequestMapping("/venta")
public class VentaController {
	
	@Autowired
	@Qualifier("usuarioDao")
	UsuarioDao usuarioDao;
	

	@Autowired
	@Qualifier("ventaDao")
	VentaDao ventaDao;

	
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
	
	@RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo Venta prueba");

	    }
	
	@RequestMapping(value = { "/add_/{username}" },  method = RequestMethod.GET)
	public void ventasadd(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable String username)throws IOException {
		System.out.println("si entra aqui en alta venta:");
	//				if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		AsignadorDeCharset.asignar(request, response);
		//Venta v = (Venta) JsonConvertidor.fromJson(json, Venta.class);
		Venta v= new Venta();
		Usuario u= usuarioDao.consultarUsuario(username);
		List<Long> envios= new ArrayList<Long>();
		Envio e1 = new Envio();Envio e2 = new Envio();
		Persona c=new Persona();
		Persona d =new Persona();
		Paquete pq = new Paquete();
		
//		c.setNombre("Angelica Pacheco");
//		c.setCalle("ejercito del trabajo");
//		c.setNoExterior("130");
//		c.setNoInterior("0");
//		c.setColonia("gpe");
//		c.setLocalidad("san buena");
//		c.setMunicipio("toluca");
//		c.setEstado("mexico");
//		c.setCodigoPostal(50110);
//		c.setReferencias("frente a minisuper");
//		c.setTelefono("4890104");
//		personaDao.save(c);
//		e1.setCliente(c);
//		
		
		
//		d.setNombre("Ma. del Carmen PAntoja");
//		d.setCalle("melero y piña");
//		d.setNoExterior("870");
//		d.setNoInterior("0");
//		d.setColonia("san sebastian");
//		d.setLocalidad("toluca");
//		d.setMunicipio("toluca");
//		d.setEstado("mexico");
//		d.setCodigoPostal(50050);
//		d.setReferencias("junto a gasolinerar");
//		d.setTelefono("4875567");
//		personaDao.save(d);
//		e1.setDestinatario(d);
		
		
//		pq.setAlto(40.00);pq.setAncho(40.00); pq.setLargo(0.00);
//		pq.setDescripcion("paquete de joyeria");
//		pq.setPeso(3.0);
//		pq.setTipoPaquete("paquete");
//		paqueteDao.save(pq);
//		e1.setPaquete(pq);
		
		
	//	e1.setEmpresa("ESTAFETA");

		//Guia g=guiaDao.getByEstSuc("ASIGNADA", usuarioDao.consultarUsuario(username).getIdSucursal());
	//	System.out.println("guia:"+g.getNumero());
//		e1.setGuia(g.getNumero());
//		e1.setRastreo(111111);
//		e1.setTipoEnvio("PAQUETE");
//		e1.setTipoServicio("NACIONAL");
//		e1.setPrecio(312.50);
//		e1.setId(Long.parseLong("1111111"));
//		
//		envioDao.save(e1);
//		
		
		/////////// SEGUNDO ENVIO
//		e2.setCliente(d);
//		e2.setDestinatario(c);
//		e2.setPaquete(pq);
//		e2.setEmpresa("MERVEL");
//		Guia g2=guiaDao.getByEstSuc("ASIGNADA", usuarioDao.consultarUsuario(username).getIdSucursal());
//		System.out.println("guia:"+g2.getNumero());
//		e2.setGuia(g2.getNumero());
//		e2.setRastreo(22222);
//		e2.setTipoEnvio("PAQUETE");
//		e2.setTipoServicio("NACIONAL");
//		e2.setPrecio(70.00);
//		e2.setId(Long.parseLong("222222"));
//		envioDao.save(e2);
		 e1= envioDao.consult(Long.valueOf("6434342045745152"));
		 e2= envioDao.consult(Long.valueOf("5871392092323840"));
		
		////////
		envios.add(e1.getId());		
		envios.add(e2.getId());
		
		v.setEnvios(envios);
		v.setCantidad(2);
		//Envio e2 = new Envio();
		Contador folio= new Contador();
		v.setFolio(folio.getFolio());
		v.setFecha("25/06/2018");
		
		v.setEstatus("ABIERTA");
		
		v.setUsuario(u);
		v.setTotal(Double.valueOf("378.00"));
		
		
		
		//System.out.println(" yisus manda:"+json);
//		int ini=Integer.parseInt(inicio);
//		int f=Integer.parseInt(fin);
			
		
			ventaDao.save(v); 
			//g.setEstatus("EN ENVIO");
			//g2.setEstatus("EN ENVIO");
		//	guiaDao.update(g);
			//guiaDao.update(g2);
			folio.incremeta();
	
//			} else {
//				response.sendError(403);
//			}
		}
	
	@RequestMapping(value = { "/add/{username}" },  method = RequestMethod.POST,consumes = "Application/Json")
	public void altas(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable String username,@RequestBody String json)throws IOException {
	
	//				if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		AsignadorDeCharset.asignar(request, response);
		Venta v = (Venta) JsonConvertidor.fromJson(json, Venta.class);
		Contador folio= new Contador();
		v.setFolio(folio.getFolio());
		v.setEstatus("ABIERTA");
		v.setTotal(Double.parseDouble("0.00"));
		v.setUsuario(usuarioDao.consultarUsuario(username));
		//System.out.println(" yisus manda:"+json);
//		int ini=Integer.parseInt(inicio);
//		int f=Integer.parseInt(fin);
			
		
			ventaDao.save(v); 
			folio.incremeta();
	
//			} else {
//				response.sendError(403);
//			}
		}
	
	
	 @RequestMapping(value = {"/update/{idVenta}" }, method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request, @RequestBody String json, @PathVariable Long idVenta)
				throws IOException {
	//	   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 2, sessionDao,userName)){

				AsignadorDeCharset.asignar(request, response);
				Venta v = (Venta) JsonConvertidor.fromJson(json, Venta.class);
				
				
				//Empleado e= evo.getEmpleado();
				ventaDao.update(v);
				response.getWriter().println(JsonConvertidor.toJson(v));
//		   }else{
//				response.sendError(403);
//		   }
		}
	
	
	@RequestMapping(value = { "/cancelar/{idVenta}" },  method = RequestMethod.GET)
	public void asignaM(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable Long idVenta )throws IOException {
	
	//				if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		AsignadorDeCharset.asignar(request, response);
		
			Venta v = ventaDao.consult(idVenta);
			v.setEstatus("CANCELADA");
			ventaDao.update(v);
			//System.out.println(" yisus manda:"+json);
		
		
	}
	
	 @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Venta> lista = ventaDao.findAll();
			if (lista == null) {
				lista = new ArrayList<Venta>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	 
	  @RequestMapping(value = { "/getFolio" },  method = RequestMethod.GET, produces = "application/json")
			public void getFolio(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   //System.out.println("dame guia");
			   Contador folio= new Contador();
			   //folio.getFolio();
		  //response.getWriter().println(g.getNumero());
		  response.getWriter().println(JsonConvertidor.toJson(folio.getFolio()));
		

//		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 20, sessionDao,userName)){
			  
	  }

	  @RequestMapping(value = { "/generaTicket/{idVenta}/{userName}" },  method = RequestMethod.GET, produces = "application/pdf")
		public void generaVale(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idVenta, @PathVariable String userName) throws IOException {
	   System.out.println("genera ticket");
//	   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 20, sessionDao,userName)){
		   response.setContentType("Application/Pdf");
		   
		   Venta v = ventaDao.consult(idVenta);
		 // Envio e = envioDao.consult(idEnvio) ; 
		   List<Envio> objE= new ArrayList<Envio>();
		    List<Long> envios = ventaDao.consult(idVenta).getEnvios();
			for (Long envio: envios){
				Envio e=envioDao.consult(envio);
				objE.add(e);
			}
				   
	        File newPdfFile = new File(idVenta+".pdf");		 
	        if (!newPdfFile.exists()){
	            try {
	            	newPdfFile.createNewFile();
	            } catch (IOException ioe) {
	                System.out.println("(Error al crear el fichero nuevo ......)" + ioe);
	            }
	        }
     
	        Sucursal s= sucursalDao.consult(usuarioDao.consultarUsuario(userName).getIdSucursal());
	  
	        System.out.println("Empiezo a generar pdf..envios.."+objE );
	        System.out.println("Empiezo a generar pdf...suc."+s );
	        System.out.println("Empiezo a generar pdf...venta."+v );
	    	GeneraTicket gt = new GeneraTicket(objE, s, v ,  response.getOutputStream());
	 
	    	  response.getOutputStream().flush();
		        response.getOutputStream().close();
	    	
//	   }else{
//			response.sendError(403);
//		}
	}
	 

	  
	  @RequestMapping(value = { "/generaReporteXls/{userName}" }, method = RequestMethod.GET,consumes = "application/json", produces = "application/pdf" )
	  //@RequestMapping(value = { "/generaReporteXls/{userName}" }, method = RequestMethod.GET,consumes = "application/json", produces = "application/pdf" )
			public void generaxls(HttpServletResponse response, HttpServletRequest request,@RequestBody String json ,
					 @PathVariable String userName) throws IOException, ParseException {
			 
		//	  if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 4, sessionDao,userName)){
					  System.out.println("si entra:");
					  response.setContentType("Application/Pdf");
					//  String nombreArchivo = ("Reporte_Ventas_"+inicio+"_"+fin);
					  
					
					  
				        FechasVo f= (FechasVo) JsonConvertidor.fromJson(json, FechasVo.class);
						SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
					//	try {
							Date datei = formatter.parse(f.getFechai());
							Date datef = formatter.parse(f.getFechaf());
							Calendar c = Calendar.getInstance();
							c.setTime(datef);
							c.add(Calendar.DATE, 1);
							datef = c.getTime();
							
							 String nombreArchivo = ("Reporte_Ventas_"+datei+"_"+datef);
							 
							  File newExcelFile = new File(nombreArchivo);		 
						        if (!newExcelFile.exists()){
						            try {
						                newExcelFile.createNewFile();
						            } catch (IOException ioe) {
						                System.out.println("(Error al crear el fichero nuevo ......)"+ ioe);
						                System.out.println("(ruta absoluta ......)"+newExcelFile.getAbsolutePath());
						                System.out.println("(ruta canonica..)"+newExcelFile.getPath());
						            }
						        }
							  
							 
							 
							 List<ReporteVo> regs= new ArrayList<ReporteVo>();
							regs= getEventos(regs, datei,datef);
					  
				        System.out.println("empiezo a generar pdf..." );
				        /////igual puedo 
				    	ReporteXls rep = new ReporteXls(regs,nombreArchivo);//,response.getOutputStream()
				    	
				    	System.out.println("nombre de archivo para edgar:");
				    	System.out.println("El Directorio Temporal del Sistema Es: ");
				        System.out.println( System.getProperty("java.io.tmpdir") );
			//	    	response.getWriter().println((ox.getNombreArchivo().substring(7)));
				       // response.getOutputStream().flush();
				      //  response.getOutputStream().close();
				    	//generaOrdenPdf.GeneraOrdenPdf(new File(ox.getNombreArchivo()));
				    	//generaOrdenPdf.GeneraOrdenPdf(ox));
//			  }else{
//					response.sendError(403);
//			   }
			}
		  
	  
	  @RequestMapping(value = { "/generaReporteXls_/{inicio}/{fin}/{userName}" }, method = RequestMethod.GET, produces = "application/xls" )
	 
			public void generaxls_(HttpServletResponse response, HttpServletRequest request,
					@PathVariable String inicio,@PathVariable String fin, @PathVariable String userName) throws IOException, ParseException {
			 
		//	  if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 4, sessionDao,userName)){
					  System.out.println("si entra:");
					  //response.setContentType("Application/Pdf");
					  String nombreArchivo = ("C://REPORTES_MENSAJERIA//REPORTEXXXXXX.xls");//+inicio+"_"+fin);
					  
					  File newExcelFile = new File(nombreArchivo);		 
					  if (!newExcelFile.exists()){
				            try {
				                newExcelFile.createNewFile();
				                System.out.println("YA LO CREO... ");
				            } catch (IOException ioe) {
				                System.out.println("(Error al crear el fichero nuevo ......)"+ ioe);
				                System.out.println("(ruta absoluta ......)"+newExcelFile.getAbsolutePath());
				                System.out.println("(ruta canonica..)"+newExcelFile.getPath());
				            }
				        }
					  
				     //   FechasVo f= (FechasVo) JsonConvertidor.fromJson(json, FechasVo.class);
						SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy"); //HH:mm:ss");
					//	try {
							Date datei = formatter.parse(inicio);
							Date datef = formatter.parse(fin);
							Calendar c = Calendar.getInstance();
							c.setTime(datef);
							c.add(Calendar.DATE, 1);
							datef = c.getTime();
							
							 List<ReporteVo> regs= new ArrayList<ReporteVo>();
							regs= getEventos(regs, datei,datef);
							 System.out.println("------------existen :"+regs.size()+"envios en total" );
				        System.out.println("empiezo a generar pdf..." );
				        /////igual puedo 
				    	ReporteXls rep = new ReporteXls(regs,nombreArchivo);//,response.getOutputStream()
				    	
				    	
				    	System.out.println("El Directorio Temporal del Sistema Es: ");
				        System.out.println( System.getProperty("java.io.tmpdir") );
			//	    	response.getWriter().println((ox.getNombreArchivo().substring(7)));
				       // response.getOutputStream().flush();
				        //response.getOutputStream().close();
				    	//generaOrdenPdf.GeneraOrdenPdf(new File(ox.getNombreArchivo()));
				    	//generaOrdenPdf.GeneraOrdenPdf(ox));
//			  }else{
//					response.sendError(403);
//			   }
			}
		
	  public List<ReporteVo> getEventos(List<ReporteVo> regs,Date datei, Date datef){
		  List<Venta> lista= ventaDao.getVentas(datei, datef);
		  System.out.println("ventas array:"+lista.size());
		  List<Envio> envios= new ArrayList<Envio>();
		  List<Long> longs= new ArrayList<Long>();
		//	ReporteVo r= new ReporteVo(); 				
				for (Venta v : lista ){
					ReporteVo r= new ReporteVo(); 	
					System.out.println("*********************");
					r.setFecha(v.getFecha());
					r.setFolio(v.getFolio());
					r.setTotal(v.getTotal());
					longs = v.getEnvios();
					System.out.println("eventos array:"+longs);
					for (Long l: longs){
						Envio e=envioDao.consult(l);
						//envios.add(e);
					//	System.out.println("---envios longs:"+envios);
				///	}
				//	for (Envio e : envios){
						//Envio e = envioDao.consult(e.getCliente())
						System.out.println("+++++++++++++++++++++++");
						r.setRemitente(e.getCliente().getNombre());
						r.setGuia(e.getGuia());
						r.setRastreo(e.getRastreo());
						r.setTipoPaquete(e.getPaquete().getTipoPaquete());
						r.setTipoEnvio(e.getTipoEnvio());
						r.setEmpresa(e.getEmpresa());
						r.setPrecio(e.getPrecio());
						r.setCostoSeguro(e.getCostoSeguro());						
						regs.add(r);
						System.out.println("regsssssssssss:.."+r.getGuia() );
					}
				}
				System.out.println("objetos de envios:.."+regs );
				return regs;
	  }
       
	   
}
