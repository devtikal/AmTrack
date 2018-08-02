package com.tikal.mensajeria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.objectify.ObjectifyService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.tikal.cacao.dao.FacturaVttDAO;
import com.tikal.cacao.factura.RespuestaWebServicePersonalizada;
import com.tikal.cacao.factura.ws.WSClient;
import com.tikal.cacao.factura.ws.WSClientCfdi33;
import com.tikal.cacao.sat.cfd.catalogos.dyn.C_UsoCFDI;
//import com.tikal.cacao.sat.cfd.Comprobante.Receptor;
import com.tikal.cacao.sat.cfd33.Comprobante;
import com.tikal.cacao.service.FacturaVTTService;
import com.tikal.cacao.springController.viewObjects.v33.ComprobanteVO;
import com.tikal.mensajeria.dao.ClienteFacDao;
import com.tikal.mensajeria.dao.EmisorDAO;
import com.tikal.mensajeria.dao.EnvioDao;
import com.tikal.mensajeria.dao.GuiaDao;
import com.tikal.mensajeria.dao.PaqueteDao;
import com.tikal.mensajeria.dao.PersonaDao;
import com.tikal.mensajeria.dao.ReceptorDAO;
import com.tikal.mensajeria.dao.SerialDAO;
import com.tikal.mensajeria.dao.SucursalDao;
import com.tikal.mensajeria.dao.UsuarioDao;
import com.tikal.mensajeria.dao.VentaDao;
import com.tikal.mensajeria.dao.VentaFacDao;
import com.tikal.mensajeria.facturacion.ComprobanteVentaFactory33;
import com.tikal.mensajeria.modelo.entity.ContadorServicio;
import com.tikal.mensajeria.modelo.entity.Emisor;
import com.tikal.mensajeria.modelo.entity.Envio;
import com.tikal.mensajeria.modelo.entity.FacturaVTT;
import com.tikal.mensajeria.modelo.entity.Serial;
import com.tikal.mensajeria.modelo.entity.Venta;
import com.tikal.mensajeria.modelo.vo.ClienteFac;
import com.tikal.mensajeria.modelo.vo.DatosEmisor;
import com.tikal.mensajeria.modelo.vo.VentaFac;
import com.tikal.mensajeria.util.JsonConvertidor;
import com.tikal.util.AsignadorDeCharset;


	

@Controller
@RequestMapping(value = { "/factura"})
public class FacturaController {
	
	@Autowired
	private WSClient client;

	@Autowired
	private WSClientCfdi33 client33;
	
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
	


	@Autowired
	@Qualifier("serialdao")
	SerialDAO serialdao;
	
	@Autowired
	@Qualifier("clientefacDao")
	ClienteFacDao clientefacDao;
	
	@Autowired
	@Qualifier("emisordao")
	EmisorDAO emisordao;
	
	@Autowired
	@Qualifier("receptordao")
	ReceptorDAO receptordao;
	
	@Autowired
	@Qualifier("facturaVTTDAO")
	FacturaVttDAO facturaVTTDAO;
//	
	@Autowired	
	@Qualifier("facturaVTTService")
	FacturaVTTService facturaVTTService;
	
	@Autowired	
	@Qualifier("ventaFacDao")
	VentaFacDao ventaFacDao;
	
	
	@PostConstruct
	public void init() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package with the WSDL java classes
		marshaller.setContextPath("localhost");

		client.setDefaultUri("http://www.timbracfdipruebas.mx/serviciointegracionpruebas/Timbrado.asmx");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);

		// init Web Service Timbrado Cfdi v3.3
		Jaxb2Marshaller marshallerV33 = new Jaxb2Marshaller();
		marshallerV33.setContextPath("org.tempuri");

		client33.setDefaultUri("https://cfdi33-pruebas.buzoncfdi.mx:1443/Timbrado.asmx");
		client33.setMarshaller(marshallerV33);
		client33.setUnmarshaller(marshallerV33);
		
		
		
//		client.setMarshaller(marshaller);
//		client.setUnmarshaller(marshaller);
	}
	
	
	@RequestMapping(value={"/prueba"},method = RequestMethod.GET)	
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo Venta prueba");

	    }
	
	
	  @RequestMapping(value = {	"/facturar/{idVenta}" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
			public void facturar(HttpServletRequest re, HttpServletResponse rs, @PathVariable Long idVenta, @RequestBody String json) 
					throws IOException, MessagingException, DocumentException{
				//if(Util.verificarPermiso(re, usuariodao, perfildao, 3)){
				AsignadorDeCharset.asignar(re, rs);
					VentaFac ventavo= (VentaFac) JsonConvertidor.fromJson(json, VentaFac.class);
					ventaFacDao.crear(ventavo);
					
					Venta venta= ventaDao.consult(idVenta);
					List<Envio> envios =new ArrayList<Envio>();
					for (Long envio: venta.getEnvios()){
						Envio e= envioDao.consult(envio);
						envios.add(e);
					}
					
					ventavo.setEnvios(envios);
					//Serial s=new Serial();		
					ContadorServicio f=ObjectifyService.ofy().load().type(ContadorServicio.class).list().get(0);
					Long folio = f.getFactura();		
					Comprobante c= ComprobanteVentaFactory33.generarFactura(ventavo);
					System.out.println("comprobante c: "+c);
					c.setFolio(folio.toString());
					c.setSerie("FS");
					c.getReceptor().setUsoCFDI(new C_UsoCFDI(ventavo.getUsoCfdi()));
					ComprobanteVO comprobanteVO= new ComprobanteVO();
					comprobanteVO.setComprobante(c);
					comprobanteVO.setEmail(ventavo.getMail());
					System.out.println("comprobante vo: "+comprobanteVO);
					System.out.println("session: "+re.getSession());
					System.out.println("factura: "+facturaVTTService);
					RespuestaWebServicePersonalizada respuestaws = facturaVTTService.timbrarPOS(comprobanteVO, re.getSession());
					String uuid= respuestaws.getUuidFactura();
					String[] respuesta= new String[2];
					if (uuid!=null) {
						System.out.println("uuid no nulo");
						venta.setEstatus("FACTURADA");			
						venta.setUuid(uuid);
						venta.setNumeroFactura("FS"+folio.toString());
						venta.setIdVentaFac(ventavo.getId());
						ventavo.setUuid(uuid);
						f.incrementarFactura();
						ObjectifyService.ofy().save().entity(f).now(); 
						ventaDao.update(venta);
						ventaFacDao.crear(ventavo);
						respuesta[0]="0";
					}else{
						System.out.println("uuid nulo");
						respuesta[0]="1";
						String mensaje=respuestaws.getMensajeRespuesta();
						respuesta[1]="Mensaje de respuesta: " +mensaje ;
					}
					
					rs.getWriter().println(JsonConvertidor.toJson(respuesta));
			//	}else{
				//	rs.sendError(403);
			//	}
			}
	  
	  @RequestMapping(value = {"/pdfFactura/{uuid}" }, method = RequestMethod.GET)
		public void pdf(HttpServletRequest re, HttpServletResponse res, @PathVariable String uuid) throws IOException{
		//	if(Util.verificarPermiso(re, usuariodao, perfildao, 1,3)){
			res.setContentType("Application/PDF");
			AsignadorDeCharset.asignar(re, res);
			FacturaVTT factura=facturaVTTService.consultar(uuid);
			try {
				PdfWriter writer = facturaVTTService.obtenerPDF(factura, res.getOutputStream());
				if(writer!=null){
					res.setContentType("Application/Pdf");
					res.getOutputStream().flush();
					res.getOutputStream().close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
				catch (DocumentException e) {
				e.printStackTrace();
			}
//			}else{
//				res.sendError(403);
//			}
		}
		
		@RequestMapping(value = {"/sendmail" }, method = RequestMethod.POST,consumes= "application/json")
		public void sendMail(HttpServletRequest re, HttpServletResponse res, @RequestBody String json) throws IOException, MessagingException, DocumentException{
			//if(Util.verificarPermiso(re, usuariodao, perfildao, 1,3)){
			VentaFac vf= (VentaFac)JsonConvertidor.fromJson(json, VentaFac.class);
			String mail= vf.getMail();
			FacturaVTT f= facturaVTTDAO.consultar(vf.getUuid());
			if(f!=null){
				if(mail!=null){
					facturaVTTService.enviarEmail(mail, f.getUuid(), re.getSession());
					res.getWriter().print("Se envió");
				}
			}
//			}else{
//				res.sendError(403);
//			}
		}
	  
	  
		@RequestMapping(value = "/xmlDescarga/{uuid}", method = RequestMethod.GET, produces = "text/xml")
		public void obtenerXML(HttpServletRequest req, HttpServletResponse res, @PathVariable String uuid) throws IOException {
		//	if(Util.verificarPermiso(req, usuariodao, perfildao, 1,3)){
				AsignadorDeCharset.asignar(req, res);
				FacturaVTT factura = facturaVTTDAO.consultar(uuid);
				PrintWriter writer = res.getWriter();
				if (factura != null) {
					res.setContentType("text/xml");
					writer.println(factura.getCfdiXML());
				} else {
					writer.println("La factura con el folio fiscal (uuid) ".concat(uuid).concat(" no existe"));
				}
//			}else{
//				res.sendError(403);
//			}
		}
	  
		@RequestMapping(value = "/cancelarAck/{idVenta}", method = RequestMethod.POST)
		public void cancelarAck(HttpServletRequest req, HttpServletResponse res, @PathVariable Long idVenta) throws IOException {
		//if(Util.verificarPermiso(req, usuariodao, perfildao, 3)){
		//	VentaFac vf= (VentaFac)JsonConvertidor.fromJson(json, VentaFac.class);
			AsignadorDeCharset.asignar(req, res);
			VentaFac vf = ventaFacDao.consultar(ventaDao.consult(idVenta).getIdVentaFac());
			String mensaje=facturaVTTService.cancelarAck(vf.getUuid(), "MERA680707KA3", req.getSession());
			//String mensaje=facturaVTTService.cancelarAck(vf.getUuid(), "AAA010101AAA", req.getSession());
			if(mensaje.compareTo("Comprobante cancelado")==0){
				Venta v= ventaDao.consult(idVenta);
				v.setEstatus("FACTURA-CANCELADA");
				ventaDao.update(v);
			}
			res.getWriter().print(mensaje);
			
	//		}else{
		//		res.sendError(403);
			//}
		}	 
		  
		  public ClienteFac cargaCliente(VentaFac vf){
				ClienteFac cf= new ClienteFac();
				cf.setRfc(vf.getRfc());
				cf.setNombre(vf.getNombre());
				cf.setDomicilio(vf.getDomicilio());	
				clientefacDao.crear(cf);			  
			  return cf;
			}

		  public Emisor creaEmisor(){
				Emisor e= new Emisor();				
				e.setRfc("MERA680707KA3");	
				emisordao.crear(e);			  
			  return e;
			}

}
