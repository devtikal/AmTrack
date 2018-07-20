package com.tikal.mensajeria.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.tikal.cacao.dao.FacturaVttDAO;
import com.tikal.cacao.factura.RespuestaWebServicePersonalizada;
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
import com.tikal.mensajeria.facturacion.ComprobanteVentaFactory33;
import com.tikal.mensajeria.modelo.entity.Emisor;
import com.tikal.mensajeria.modelo.entity.FacturaVTT;
import com.tikal.mensajeria.modelo.entity.Serial;
import com.tikal.mensajeria.modelo.entity.Venta;
import com.tikal.mensajeria.modelo.vo.ClienteFac;
import com.tikal.mensajeria.modelo.vo.DatosEmisor;
import com.tikal.mensajeria.modelo.vo.VentaFac;
import com.tikal.mensajeria.util.JsonConvertidor;
import com.tikal.util.AsignadorDeCharset;


	

@Controller
@RequestMapping("/factura")
public class FacturaController {
	
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
	
	//@Autowired
	@Qualifier("facturaVTTDAO")
	FacturaVttDAO facturaVTTDAO;
	
	//@Autowired	
	@Qualifier("facturaVTTService")
	FacturaVTTService facturaVTTService;
	
	
	
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
					Venta venta= ventaDao.consult(idVenta);
				//	Receptor r = creaReceptor();
					//Emisor e=crearEmisor(ventavo);
					Serial s=new Serial();
					
					Long folio = s.getFolio();
					ClienteFac cliente= cargaCliente(ventavo);
					DatosEmisor emisor=new DatosEmisor();
					Comprobante c= ComprobanteVentaFactory33.generarFactura(ventavo,cliente, emisor );
					//facturar
					c.setFolio(folio.toString());
					c.setSerie("FS");
					c.getReceptor().setUsoCFDI(new C_UsoCFDI(ventavo.getUsoCfdi()));
					
					ComprobanteVO comprobanteVO= new ComprobanteVO();
					comprobanteVO.setComprobante(c);
					comprobanteVO.setEmail(ventavo.getMail());
					RespuestaWebServicePersonalizada respuestaws = facturaVTTService.timbrarPOS(comprobanteVO, re.getSession());
					String uuid= respuestaws.getUuidFactura();
					String[] respuesta= new String[2];
					if (uuid!=null) {
//						venta.setXml(cfdiXML);
						venta.setEstatus("FACTURADO");
					//	venta.setUuid(uuid);
						venta.setNumeroFactura(folio.toString());
						s.incrementa();
						ventaDao.update(venta);
						respuesta[0]="0";
					}else{
						respuesta[0]="1";
						String mensaje=respuestaws.getMensajeRespuesta();
						respuesta[1]="Mensaje de respuesta: " +mensaje ;
					}
					
					rs.getWriter().println(JsonConvertidor.toJson(respuesta));
			//	}else{
				//	rs.sendError(403);
			//	}
			}
	  
	  @RequestMapping(value = {"/pdfFactura/{id}" }, method = RequestMethod.GET)
		public void pdf(HttpServletRequest re, HttpServletResponse res, @PathVariable String id) throws IOException{
		//	if(Util.verificarPermiso(re, usuariodao, perfildao, 1,3)){
			res.setContentType("Application/PDF");
			FacturaVTT factura=facturaVTTDAO.consultar(id);
			try {
				PdfWriter writer = facturaVTTService.obtenerPDF(factura, res.getOutputStream());
				if(writer!=null){
					res.setContentType("Application/Pdf");
					res.getOutputStream().flush();
					res.getOutputStream().close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
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
			//FacturaVTT f= facturaVTTDAO.consultar(vf.getUuid());
		//	if(f!=null){
				if(mail!=null){
		//			facturaVTTService.enviarEmail(mail, f.getUuid(), re.getSession());
					res.getWriter().print("Se envió");
				}
		//	}
//			}else{
//				res.sendError(403);
//			}
		}
	  
	  
	  
	  
	 
		  
		  public ClienteFac cargaCliente(VentaFac vf){
				ClienteFac cf= new ClienteFac();
				cf.setRfc(vf.getRfc());
				cf.setNombre(vf.getNombre());
				cf.setDomicilio(vf.getDomicilio());	
				clientefacDao.crear(cf);			  
			  return cf;
			}

		  public Emisor creaEmisor(VentaFac vf){
				Emisor e= new Emisor();				
				e.setRfc("MERA680707KA3");	
				emisordao.crear(e);			  
			  return e;
			}

}
