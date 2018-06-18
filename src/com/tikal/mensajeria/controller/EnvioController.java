package com.tikal.mensajeria.controller;

import java.io.File;
import java.io.IOException;
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

import com.tikal.mensajeria.dao.DestinatarioDao;
import com.tikal.mensajeria.dao.EmpresaDao;
import com.tikal.mensajeria.dao.EnvioDao;
import com.tikal.mensajeria.dao.GuiaDao;
import com.tikal.mensajeria.dao.PaqueteDao;
import com.tikal.mensajeria.dao.SucursalDao;
import com.tikal.mensajeria.dao.UsuarioDao;
import com.tikal.mensajeria.formatos.pdf.GeneraTicket;
import com.tikal.mensajeria.modelo.entity.Contador;
import com.tikal.mensajeria.modelo.entity.Destinatario;
import com.tikal.mensajeria.modelo.entity.Empresa;
import com.tikal.mensajeria.modelo.entity.Envio;
import com.tikal.mensajeria.modelo.entity.Guia;
import com.tikal.mensajeria.modelo.entity.Paquete;
import com.tikal.mensajeria.modelo.login.Sucursal;
import com.tikal.mensajeria.modelo.login.Usuario;
import com.tikal.mensajeria.modelo.vo.EnvioVo;
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
	@Qualifier("empresaDao")
	EmpresaDao empresaDao;
	
	@Autowired
	@Qualifier("paqueteDao")
	PaqueteDao paqueteDao;
	
	@Autowired
	
	@Qualifier("destinatarioDao")
	DestinatarioDao destinatarioDao;
	
	@Autowired
	@Qualifier("envioDao")
	EnvioDao envioDao;
	
	@Autowired
	@Qualifier("guiaDao")
	GuiaDao guiaDao;
	
	
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
			Destinatario d= new Destinatario();
			Paquete p = new Paquete();
			Empresa e = new Empresa();
			Usuario u = new Usuario();
			envio.setCantidad(1);
			envio.setCliente("Juan Perez Acuña");
			
			d.setNombre("Angelica");
			d.setaMaterno("Pacheco");
			d.setaMaterno("Pantoja");
			d.setCalle("Ejercito del trabajo");
			d.setNoExterior("130");
			d.setNoInterior("0");
			d.setColonia("guadalupe san buena");
			d.setLocalidad("Toluca");
			d.setMunicipio("Toluca");
			d.setEstado("Mexico");
			d.setCodigoPostal(50110);
			d.setTelefono("7222222222");
			
			destinatarioDao.save(d);
			
			
			envio.setDestinatario(d);
			
			p.setAlto(10.00);p.setAncho(10.00); p.setLargo(10.00);
			p.setDescripcion("paquete de joyeria");
			p.setPeso(312.00);
			p.setTipoPaquete("paquete");
			paqueteDao.save(p);
			
			envio.setPaquete(p);
			
			e.setClave("fed");
			e.setDescripcion("FEDEX_");
			empresaDao.save(e);
			
		//	envio.setEmpresa("otra");
			envio.setFecha("05/06/2018 18:03:45");
			Contador c= new Contador();
			envio.setFolio(c.getFolio().toString());
			//Integer guia = buscaGuia(username);
			//guiaDao.
			//Usuario user= usuarioDao.consultarUsuario(usuario);
			//System.out.println("esta en daoimpl envio  get by status Suc:"+usuarioDao.consultarUsuario(usuario).getIdSucursal());
			Guia g=guiaDao.getByEstSuc("NO ASIGNADA", usuarioDao.consultarUsuario(usuario).getIdSucursal());
			envio.setGuia(g.getNumero());
			envio.setPrecio(312.50);
			envio.setRastreo(8888888);
			envio.setTipoEnvio("Express");
			envio.setTotal(312.50);
			   

			//u= usuarioDao.consult("")
			
			//envio.setUsuario(usuario);
			envioDao.save(envio);
			g.setEstatus("ASIGNADA");
			guiaDao.update(g);
			c.incremeta();
	//}
//			response.sendError(403);
//		}
	}
	

	@RequestMapping(value = { "/add/{usuario}" },  method = RequestMethod.POST, consumes = "Application/Json")
	public void crearEnvio(HttpServletRequest request, HttpServletResponse response,@RequestBody String json, @PathVariable String usuario)throws IOException {
//		if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
			AsignadorDeCharset.asignar(request, response);
			System.out.println(" Yisus manda:"+json);
			EnvioVo evo = (EnvioVo) JsonConvertidor.fromJson(json, EnvioVo.class);
			//System.out.println("lista de permisos:"+perfil.getPermisos());
			Paquete p = new Paquete();
			Envio envio = new Envio();
			Destinatario des= new Destinatario();
			Empresa e=new Empresa();
		//	e=empresaDao.getByNombre(evo.getEmpresa());
			
			p.setAlto(evo.getAlto());
			p.setAncho(evo.getAncho());
			p.setDescripcion(evo.getDescripcion());
			p.setLargo(evo.getLargo());
			p.setPeso(evo.getPeso());
			p.setTipoPaquete(evo.getTipoPaquete());
			 paqueteDao.save(p);
			
			des.setaMaterno(evo.getaMaterno());
			des.setaPaterno(evo.getaPaterno());
			des.setCalle(evo.getCalle());
			des.setCodigoPostal(evo.getCodigoPostal());
			des.setColonia(evo.getColonia());
			des.setEstado(evo.getEstado());
			des.setLocalidad(evo.getLocalidad());
			des.setMunicipio(evo.getMunicipio());
			des.setNoExterior(evo.getNoExterior());
			des.setNoInterior(evo.getNoInterior());
			des.setNombre(evo.getNombre());
			des.setTelefono(evo.getTelefono());
			destinatarioDao.save(des);
			
			//envio.setEmpresa(e);
			
			envio.setCantidad(evo.getCantidad());
			envio.setCliente(evo.getCliente());
			envio.setDestinatario(des);
			
			 Calendar c = Calendar.getInstance();		  
			//  String fecha =  Integer.toString(c.get(Calendar.DATE));
			envio.setFecha(c.toString());
			//envio.setFolio(evo.getFolio()); asignar folio consecutivo
			Contador folio= new Contador();
			envio.setFolio(folio.getFolio().toString());// asignar folio consecutivo
			Guia g=guiaDao.getByEstSuc("NO ASIGNADA", usuarioDao.consultarUsuario(usuario).getIdSucursal());
			envio.setGuia(g.getNumero());
			//envio.setGuia(evo.getGuia());
			envio.setPaquete(p);
			envio.setPrecio(evo.getPrecio());
			envio.setRastreo(evo.getRastreo());
			envio.setTipoEnvio(evo.getTipoEnvio());
			envio.setTotal(evo.getTotal());
			envio.setUsuario(usuarioDao.consultarUsuario(usuario));// falta
			
			envioDao.save(envio);
			g.setEstatus("ASIGNADA");
			guiaDao.update(g);
			folio.incremeta();
			
			
			
	}
	
	
	
	   @RequestMapping(value = { "/getGuia/{userName}" },  method = RequestMethod.GET, produces = "application/pdf")
			public void getGuia(HttpServletResponse response, HttpServletRequest request, @PathVariable String userName) throws IOException {
		   System.out.println("dame guia");
		   Guia g=guiaDao.getByEstSuc("NO ASIGNADA", usuarioDao.consultarUsuario(userName).getIdSucursal());
		   System.out.println("dame guia:"+g.getNumero());
		   response.getWriter().println(g.getNumero());
//		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 20, sessionDao,userName)){
			  
	   }
	
	   @RequestMapping(value = { "/generaTicket/{idEnvio}/{userName}" },  method = RequestMethod.GET, produces = "application/pdf")
		public void generaVale(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idEnvio, @PathVariable String userName) throws IOException {
	   System.out.println("genera ticket");
//	   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 20, sessionDao,userName)){
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
       
	        Sucursal s= sucursalDao.consult(usuarioDao.consultarUsuario(userName).getIdSucursal());
	        Paquete p = e.getPaquete();
	     //   String des = e.paquete.paqueteDao.consult(e.getPaquete().getDescripcion());
	        System.out.println("Empiezo a generar pdf...." );
	    	GeneraTicket gt = new GeneraTicket(e, s, p,  response.getOutputStream());
	    //ystem.out.println("nombre de archivo para edgar:"+tik.getNombreArchivo().substring(10) );
	    	//response.getWriter().println((vpdf.getNombreArchivo().substring(10)));
	    	  response.getOutputStream().flush();
		        response.getOutputStream().close();
	    	//generaOrdenPdf.GeneraOrdenPdf(new File(ox.getNombreArchivo()));
	    	//generaOrdenPdf.GeneraOrdenPdf(ox));
//	   }else{
//			response.sendError(403);
//		}
	}
	   
	    
		 
	
	
}


