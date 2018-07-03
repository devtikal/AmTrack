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


import com.tikal.mensajeria.dao.EnvioDao;
import com.tikal.mensajeria.dao.GuiaDao;
import com.tikal.mensajeria.dao.PaqueteDao;
import com.tikal.mensajeria.dao.PersonaDao;
import com.tikal.mensajeria.dao.SucursalDao;
import com.tikal.mensajeria.dao.UsuarioDao;
import com.tikal.mensajeria.dao.VentaDao;
import com.tikal.mensajeria.formatos.pdf.GeneraGuiaMervel;
import com.tikal.mensajeria.formatos.pdf.GeneraTicket;
import com.tikal.mensajeria.modelo.entity.Contador;
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
@RequestMapping("/envio")
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
			Guia g=guiaDao.getByEstSuc("ASIGNADA", usuarioDao.consultarUsuario(usuario).getIdSucursal());
			envio.setGuia(g.getNumero());
			envio.setPrecio(65.50);
			envio.setRastreo(878987657);
			envio.setTipoEnvio("Dia siguiente");
		//	envio.setTotal(312.50);
			   

			//u= usuarioDao.consult("")
			
			//envio.setUsuario(usuario);
			envioDao.save(envio);
			g.setEstatus("EN ENVIO");
			guiaDao.update(g);
			ct.incremeta();
	//}
//			response.sendError(403);
//		}
	}
	

	@RequestMapping(value = { "/add/{usuario}" },  method = RequestMethod.POST, consumes = "Application/Json")
	public void crearEnvio(HttpServletRequest request, HttpServletResponse response,@RequestBody String json, @PathVariable String usuario)throws IOException {
//		if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
			AsignadorDeCharset.asignar(request, response);
			System.out.println(" Yisus manda:"+json);
		//	EnvioVo evo = (EnvioVo) JsonConvertidor.fromJson(json, EnvioVo.class);
			Envio ef = (Envio) JsonConvertidor.fromJson(json, Envio.class);
			//System.out.println("lista de permisos:"+perfil.getPermisos());
			Paquete p = new Paquete();
			Envio envio = new Envio();
			Persona c= new Persona();
			Persona d= new Persona();
			//Destinatario des= new Destinatario();
			//Empresa e=new Empresa();
		//	e=empresaDao.getByNombre(evo.getEmpresa());
			c=ef.getCliente();
			personaDao.save(c);
			
			d=ef.getCliente();
			personaDao.save(d);
			
			p=ef.getPaquete();
			paqueteDao.save(p);
			
			
			
			
			
			envio.setCliente(c);
			envio.setDestinatario(d);
			envio.setPaquete(p);
			
			
			
//			p.setAlto(evo.getAlto());
//			p.setAncho(evo.getAncho());
//			p.setDescripcion(evo.getDescripcion());
//			p.setLargo(evo.getLargo());
//			p.setPeso(evo.getPeso());
//			p.setTipoPaquete(evo.getTipoPaquete());
//			 paqueteDao.save(p);
			
//			des.setNombre(evo.getNombre()); 
//			des.setCalle(evo.getCalle());
//			des.setCodigoPostal(evo.getCodigoPostal());
//			des.setColonia(evo.getColonia());
//			des.setEstado(evo.getEstado());
//			des.setLocalidad(evo.getLocalidad());
//			des.setMunicipio(evo.getMunicipio());
//			des.setNoExterior(evo.getNoExterior());
//			des.setNoInterior(evo.getNoInterior());			
//			des.setTelefono(evo.getTelefono());
//			des.setReferencias(evo.getReferencias);
//			destinatarioDao.save(des);
			
			envio.setEmpresa(ef.getEmpresa());
			
			
			
			// Calendar c = Calendar.getInstance();		  
			//  String fecha =  Integer.toString(c.get(Calendar.DATE));
			//envio.setFecha(evo.getFecha());
			//envio.setFolio(evo.getFolio()); asignar folio consecutivo
		//	Contador folio= new Contador();
			//envio.setFolio(folio.getFolio().toString());// asignar folio consecutivo
			Guia g=guiaDao.getByEstSuc("ASIGNADA", usuarioDao.consultarUsuario(usuario).getIdSucursal());
			if (g==null){
			  System.out.println("no hay guias asignadas a esta sucursal");
			}
			envio.setGuia(g.getNumero());
			//envio.setGuia(evo.getGuia());
			
			envio.setPrecio(ef.getPrecio());
			envio.setRastreo(ef.getRastreo());
			envio.setTipoEnvio(ef.getTipoEnvio());
			envio.setTipoServicio(ef.getTipoServicio());
			envio.setMateriales(ef.getMateriales());
			
			envioDao.save(envio);
			g.setEstatus("EN ENVIO");
			guiaDao.update(g);
			//folio.incremeta();
			
			
			
	}
	   
	 
	  	   @RequestMapping(value = { "/generaGuiaMervel/{idVenta}/{idEnvio}/{userName}" },  method = RequestMethod.GET, produces = "application/pdf")
	 		public void generaGuia(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idEnvio
	 				, @PathVariable Long idVenta, @PathVariable String userName) throws IOException {
	 	   System.out.println("genera ticket");
//	 	   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 20, sessionDao,userName)){
	 	   Venta v = ventaDao.consult(idVenta);
			  Envio envio = envioDao.consult(idEnvio) ; 
			  
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
	   
	   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Envio> lista = envioDao.findAll();
			if (lista == null) {
				lista = new ArrayList<Envio>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

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
	
}


