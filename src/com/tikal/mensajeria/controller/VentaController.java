package com.tikal.mensajeria.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.objectify.ObjectifyService;
import com.itextpdf.text.DocumentException;
import com.tikal.cacao.dao.FacturaVttDAO;
import com.tikal.cacao.sat.cfd.Comprobante;
import com.tikal.cacao.service.FacturaVTTService;
import com.tikal.mensajeria.dao.EmisorDAO;
import com.tikal.mensajeria.dao.EnvioDao;
import com.tikal.mensajeria.dao.GuiaDao;
import com.tikal.mensajeria.dao.PaqueteDao;
import com.tikal.mensajeria.dao.PersonaDao;
import com.tikal.mensajeria.dao.SerialDAO;
import com.tikal.mensajeria.dao.SucursalDao;
import com.tikal.mensajeria.dao.UsuarioDao;
import com.tikal.mensajeria.dao.VentaDao;
import com.tikal.mensajeria.facturacion.ComprobanteVentaFactory33;
import com.tikal.mensajeria.formatos.pdf.GeneraTicket;
import com.tikal.mensajeria.modelo.entity.Contador;
import com.tikal.mensajeria.modelo.entity.ContadorServicio;
import com.tikal.mensajeria.modelo.entity.Emisor;
import com.tikal.mensajeria.modelo.entity.Envio;
import com.tikal.mensajeria.modelo.entity.Guia;
import com.tikal.mensajeria.modelo.entity.Paquete;
import com.tikal.mensajeria.modelo.entity.Persona;
import com.tikal.mensajeria.modelo.entity.Venta;
import com.tikal.mensajeria.modelo.login.Sucursal;
import com.tikal.mensajeria.modelo.login.Usuario;
import com.tikal.mensajeria.modelo.vo.DatosEmisor;
import com.tikal.mensajeria.modelo.vo.EnvioVo;
import com.tikal.mensajeria.modelo.vo.FechasVo;
import com.tikal.mensajeria.modelo.vo.ReporteVo;
import com.tikal.mensajeria.modelo.vo.VentaFac;
import com.tikal.mensajeria.reportes.ReportePdf;
import com.tikal.mensajeria.reportes.ReporteSucursal;
import com.tikal.mensajeria.reportes.ReporteXls;
import com.tikal.mensajeria.util.JsonConvertidor;
import com.tikal.util.AsignadorDeCharset;

@Controller
@RequestMapping(value = { "/venta"})
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
	
		
	@RequestMapping(value = { "/add/{username}" },  method = RequestMethod.POST,consumes = "Application/Json")
	public void altas(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable String username,@RequestBody String json)throws IOException {
	
	//				if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		AsignadorDeCharset.asignar(request, response);
		Venta v = (Venta) JsonConvertidor.fromJson(json, Venta.class);
		
		if (ObjectifyService.ofy().load().type(ContadorServicio.class).list().isEmpty()){
			ContadorServicio f= new ContadorServicio();
			f.getFolio();
			v.setFolio(f.getFolio());
			f.incrementar();
			System.out.println("folio guardado:"+f.getFolio());
			ObjectifyService.ofy().save().entity(f).now(); 
		}else{
			ContadorServicio f=ObjectifyService.ofy().load().type(ContadorServicio.class).list().get(0);
			//int numero = (int) (Math.random() * 9999) + 1;
			v.setFolio(f.getFolio());
			//dato.setIdServicio(numero);
			f.incrementar();
			ObjectifyService.ofy().save().entity(f).now(); 
		}
		
		//SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		//Calendar c = Calendar.getInstance();
		System.out.println("fecha calendar:"+v.getFecha());
		Date nueva = v.getFecha();
		nueva.setHours(nueva.getHours()-5);
		
		System.out.println("fecha menos 5 horas:"+nueva);
		v.setFecha(nueva);
		//Contador folio= new Contador();
	//	v.setFolio(folio.getFolio());
		v.setEstatus("ABIERTA");
		v.setTotal(Double.parseDouble("0.00"));
		v.setUsuario(usuarioDao.consultarUsuario(username));
		v.setIdSucursal(usuarioDao.consultarUsuario(username).getIdSucursal());
		
	//	SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		////Date datei = formatter.parse(v.getFecha());
		//System.out.println(" yisus manda:"+json);
//		int ini=Integer.parseInt(inicio);
//		int f=Integer.parseInt(fin);
			
		
			ventaDao.save(v);
			response.getWriter().println(JsonConvertidor.toJson(v.getId()));
			//folio.incremeta();
		//	ObjectifyService.ofy().save().entity(folio).now();
	
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
			ventaDao.delete(v);
			//v.setEstatus("CANCELADA");
		//	ventaDao.update(v);
			//System.out.println(" yisus manda:"+json);
		
		
	}
	
	 @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Venta> lista = ventaDao.findAllAbierta();
			if (lista == null) {
				lista = new ArrayList<Venta>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));
		}
	 
	 @RequestMapping(value = { "/findAllHoy" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllHoy(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Venta> lista = ventaDao.findAllAbiertaHoy();
			if (lista == null) {
				lista = new ArrayList<Venta>();
			}
			System.out.println("size"+lista.size());
			response.getWriter().println(JsonConvertidor.toJson(lista));
		}
	 
	 
	 @RequestMapping(value = { "/findAll/{userName} " }, method = RequestMethod.GET, produces = "application/json")
		public void findAll(HttpServletResponse response, HttpServletRequest request, @PathVariable String userName) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Venta> lista = ventaDao.findAllAbiertaBySuc(usuarioDao.consultarUsuario(userName).getIdSucursal());
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

	  @RequestMapping(value = { "/generaReportePdf/{inicio}/{fin}/{userName}" },  method = RequestMethod.GET, produces = "application/pdf")
		public void generaRep(HttpServletResponse response, HttpServletRequest request,@PathVariable String inicio,
				@PathVariable String fin, @PathVariable String userName) throws IOException, ParseException {
	   System.out.println("genera ticket");
//	   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 20, sessionDao,userName)){
		   response.setContentType("Application/Pdf");
		  
		   String sucursal=sucursalDao.consult(usuarioDao.consultarUsuario(userName).getIdSucursal()).getNombre();
		   System.out.println("si entra: con inicio"+inicio);
			  System.out.println("si entra: con fin"+fin);

				SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy"); //HH:mm:ss");
			//	try {
					Date datei = formatter.parse(inicio);
					System.out.println("formatter inicio"+datei);
					Date datef = formatter.parse(fin);
					System.out.println("formatter fin"+datef);
					Calendar c = Calendar.getInstance();
					c.setTime(datef);
					c.add(Calendar.DATE, 1);
					datef = c.getTime();
					
					
					
					 List<ReporteVo> regs= new ArrayList<ReporteVo>();
					 System.out.println("-fecha inicio"+datei);
					 System.out.println("-fecha fin"+datef);
					regs= getEventos(regs, datei,datef, userName);			
					Double total= Double.valueOf("00.00");
					for( ReporteVo ro:regs){
						total=total+ro.getTotal();
					}
					
					 File newPdfFile = new File("ReporteVentasPdf_"+datei+"-"+datef+".pdf");		 
				        if (!newPdfFile.exists()){
				            try {
				            	newPdfFile.createNewFile();
				            } catch (IOException ioe) {
				                System.out.println("(Error al crear el fichero nuevo ......)" + ioe);
				            }
				        }
					
				
	       
   
	     //   rep.GetReporteXls(datei.toGMTString(),datef.toGMTString(), total);
	        ReportePdf rpdf= new ReportePdf(regs, sucursal, datei.toGMTString(),datef.toGMTString(), total,  response.getOutputStream());
	      //  Sucursal s= sucursalDao.consult(usuarioDao.consultarUsuario(userName).getIdSucursal());
	  
//	        System.out.println("Empiezo a generar pdf..envios.."+objE );
//	        System.out.println("Empiezo a generar pdf...suc."+s );
//	        System.out.println("Empiezo a generar pdf...venta."+v );
	    //	GeneraReporte gt = new GeneraRepPdf(datei.toGMTString(),datef.toGMTString(), total,  response.getOutputStream());
	 
	    	  response.getOutputStream().flush();
		        response.getOutputStream().close();
	    	
//	   }else{
//			response.sendError(403);
//		}
	}
	
	  

	    
	  
	  @RequestMapping(value = { "/generaReporteXls_/{inicio}/{fin}/{userName}" }, method = RequestMethod.GET, produces = "application/vnd.ms-excel" )
		 
		public void generaxls_(HttpServletResponse response, HttpServletRequest request,
				@PathVariable String inicio,@PathVariable String fin, @PathVariable String userName) throws IOException, ParseException {
		 
	//	  if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 4, sessionDao,userName)){
				  System.out.println("si entra: con inicio"+inicio);
				  System.out.println("si entra: con fin"+fin);

					SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy"); //HH:mm:ss");
				//	try {
						Date datei = formatter.parse(inicio);
						System.out.println("formatter inicio"+datei);
						Date datef = formatter.parse(fin);
						System.out.println("formatter fin"+datef);
						Calendar c = Calendar.getInstance();
						c.setTime(datef);
						c.add(Calendar.DATE, 1);
						datef = c.getTime();
						
						
						
						 List<ReporteVo> regs= new ArrayList<ReporteVo>();
						 System.out.println("-fecha inicio"+datei);
						 System.out.println("-fecha fin"+datef);
						regs= getEventos(regs, datei,datef, userName);						
						
						Double total= Double.valueOf("00.00");
						if (regs.size()!=0){
							
							for( ReporteVo ro:regs){
								total=total+ro.getTotal();
							}
						}
						System.out.println("total"+total);
						
						 System.out.println("------------existen :"+regs.size()+"envios en total" );
			        System.out.println("empiezo a generar pdf..." );
			        /////igual puedo 
			    //	ReporteXls rep = new ReporteXls(regs,nombreArchivo);//,response.getOutputStream()
			        String perfil=usuarioDao.consultarUsuario(userName).getPerfil();
			    	if (perfil.equals("Administrador") || perfil.equals("SuperAdministrador")){
			    		
			    		ReporteXls rep = new ReporteXls();
				    	rep.setRenglones(regs);
				    	HSSFWorkbook reporteXls = rep.GetReporteXls(datei.toGMTString(),datef.toGMTString(), total);
						reporteXls.write(response.getOutputStream());
			    	}else{
			    		ReporteSucursal repS = new ReporteSucursal();
			    		repS.setRenglones(regs);
			    		HSSFWorkbook reporteSucursal = repS.GetReporteSucursalXls(datei.toGMTString(),datef.toGMTString(), total);
			    		reporteSucursal.write(response.getOutputStream());
			    	}
			    
//		  }else{
//				response.sendError(403);
//		   }	
		}
	  
	  
	  
	  
	  
	  public List<ReporteVo> getEventos(List<ReporteVo> regs,Date datei, Date datef, String userName){
		  
		  System.out.println("get eventos");
		  List<Venta> lista= new ArrayList<Venta>();
		  System.out.println("ififififififif");
	      String perfil=usuarioDao.consultarUsuario(userName).getPerfil();
	      System.out.println("perfil"+perfil);
		  if (perfil.equals("Administrador") || perfil.equals("SuperAdministrador")){
			  System.out.println("ififififififif");
			  lista= ventaDao.findAllAbiertaIF(datei, datef);
			  System.out.println("ventas array:"+lista.size());
			  
		  }else{
			  System.out.println("eeeeeeee");
			  lista= ventaDao.getVentas(datei, datef, usuarioDao.consultarUsuario(userName).getIdSucursal());
			  System.out.println("ventas array:"+lista.size());
		  }
		  
		 
		 
		  List<Envio> envios= new ArrayList<Envio>();
		  List<Long> longs= new ArrayList<Long>();
		//	ReporteVo r= new ReporteVo(); 	
		  
				for (Venta v : lista ){					
					System.out.println("VVVVVVVVVVVV venta:"+v.getId()+"---cant ENVIOOOOOOOs"+v.getCantidad());
					
					//System.out.println("eventos array:"+longs);
					if  (v.getCantidad()==0){
						System.out.println("no hay envios en la venta:"+v.getId() );
					}else{
						
						longs = v.getEnvios();
						System.out.println("---envios longs:"+longs);
						for (int i = 0; i <= longs.size()-1;i++ ){
							ReporteVo r= new ReporteVo(); 	
							//r.setFecha(v.getFecha().toGMTString().substring(0, 17));
							r.setFecha(v.getFecha().toString().substring(0, 17));
							r.setFolio(v.getFolio());
							r.setSucursal(sucursalDao.consult(v.getIdSucursal()).getNombre());
							r.setTotal(v.getTotal());
							Envio e=envioDao.consult(longs.get(i));					
						//	System.out.println("envio numero:.."+e.getId());
							r.setRemitente(e.getCliente().getNombre());
							r.setGuia(e.getGuia());
							r.setRastreo(e.getRastreo());
							r.setTipoPaquete(e.getPaquete().getTipoPaquete());
							r.setTipoEnvio(e.getTipoEnvio());
							r.setEmpresa(e.getEmpresa());
							r.setPrecio(e.getPrecio());
							r.setCostoSeguro(e.getCostoSeguro());	
							r.setTotal(e.getTotalEnvio());
							regs.add(r);
							System.out.println("guia numero:.."+r.getGuia() );
							System.out.println("--------------------------------------------------");
						}
						System.out.println("registros para reporte:"+regs );
					}
					System.out.println("siguiente venta.." );
				}
				//System.out.println("" );
				return regs;
	  }

	  
	
	  
	  
	  @RequestMapping(value = "/setup/{folio}", method = RequestMethod.GET)
		public void setUp(HttpServletResponse res,@PathVariable Long folio) throws IOException{
			ventaDao.crearContador(folio);			
			res.getWriter().println("Setup finalizado...");
		}
	   
	  @RequestMapping(value = "/numPaginas", method = RequestMethod.GET)
		public void numpags(HttpServletRequest req, HttpServletResponse res) throws IOException {
			int paginas = ventaDao.findAllpags();
			res.getWriter().print(paginas);
		}
	  
	  @RequestMapping(value = { "/findAllP/{page}" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllByPage(HttpServletResponse response, HttpServletRequest request, @PathVariable int page) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Venta> lista = ventaDao.findAllPage(page);
			if (lista == null) {
				lista = new ArrayList<Venta>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));
		}
	  
	  @RequestMapping(value = "/numPaginasSuc/{idSucursal}", method = RequestMethod.GET)
		public void numpagsSuc(HttpServletRequest req, HttpServletResponse res,@PathVariable Long idSucursal) throws IOException {
			int paginas = ventaDao.findAllpagsSuc(idSucursal);
			res.getWriter().print(paginas);
		}
	  
	  @RequestMapping(value = { "/findAllPage/{userName}/{page} " }, method = RequestMethod.GET, produces = "application/json")
		public void findAllP(HttpServletResponse response, HttpServletRequest request, @PathVariable String userName,@PathVariable int page) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Venta> lista = ventaDao.findAllAbiertaBySucP(usuarioDao.consultarUsuario(userName).getIdSucursal(), page);
			if (lista == null) {
				lista = new ArrayList<Venta>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));
		}

	  
	  
}
