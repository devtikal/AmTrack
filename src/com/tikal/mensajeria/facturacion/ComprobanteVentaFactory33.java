package com.tikal.mensajeria.facturacion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tikal.cacao.factura.FormatoFecha;
import com.tikal.cacao.sat.cfd.catalogos.dyn.C_ClaveProdServ;
import com.tikal.cacao.sat.cfd.catalogos.dyn.C_ClaveUnidad;
import com.tikal.cacao.sat.cfd.catalogos.dyn.C_CodigoPostal;
import com.tikal.cacao.sat.cfd.catalogos.dyn.C_FormaDePago;
import com.tikal.cacao.sat.cfd.catalogos.dyn.C_Impuesto;
import com.tikal.cacao.sat.cfd.catalogos.dyn.C_MetodoDePago;
import com.tikal.cacao.sat.cfd.catalogos.dyn.C_Moneda;
import com.tikal.cacao.sat.cfd.catalogos.dyn.C_RegimenFiscal;
import com.tikal.cacao.sat.cfd.catalogos.dyn.C_TipoDeComprobante;
import com.tikal.cacao.sat.cfd.catalogos.dyn.C_TipoFactor;
import com.tikal.cacao.sat.cfd.catalogos.dyn.C_UsoCFDI;
import com.tikal.cacao.sat.cfd33.Comprobante;
import com.tikal.cacao.sat.cfd33.Comprobante.Conceptos;
import com.tikal.cacao.sat.cfd33.Comprobante.Conceptos.Concepto;
import com.tikal.cacao.sat.cfd33.Comprobante.Emisor;
import com.tikal.cacao.sat.cfd33.Comprobante.Impuestos;
import com.tikal.cacao.sat.cfd33.Comprobante.Impuestos.Traslados;
import com.tikal.cacao.sat.cfd33.Comprobante.Impuestos.Traslados.Traslado;
import com.tikal.cacao.sat.cfd33.Comprobante.Receptor;
import com.tikal.mensajeria.modelo.vo.ClienteFac;
import com.tikal.mensajeria.modelo.vo.DatosEmisor;
import com.tikal.mensajeria.dao.EmisorDAO;
import com.tikal.mensajeria.dao.EnvioDao;
import com.tikal.mensajeria.modelo.entity.Envio;
import com.tikal.mensajeria.modelo.entity.Venta;

//import com.tikal.toledo.model.DatosEmisor;
//import com.tikal.toledo.model.Detalle;
//import com.tikal.toledo.model.Venta;
import com.tikal.cacao.util.Util;
import com.tikal.mensajeria.modelo.vo.VentaFac;

/**
 * @author Tikal
 *
 */
@Service
public class ComprobanteVentaFactory33 {
	
	@Autowired
	@Qualifier("envioDao")
	EnvioDao envioDao;
	
	@Autowired
	@Qualifier("emisorDao")
	static EmisorDAO emisorDao;
	
	
	public static  Comprobante generarFactura(VentaFac venta) {
		Comprobante comprobante = new Comprobante();
		comprobante.setVersion("3.3");
		comprobante.setFecha(Util.getXMLDate(new Date(), FormatoFecha.COMPROBANTE));
		comprobante.setMoneda(new C_Moneda("MXN"));
		comprobante.setLugarExpedicion(new C_CodigoPostal("50450"));
		comprobante.setTipoDeComprobante(new C_TipoDeComprobante("I"));
		
		comprobante.setFormaPago(new C_FormaDePago(venta.getFormaPago()));
		if(venta.getMetodoPago()!=null){
			comprobante.setMetodoPago(new C_MetodoDePago(venta.getMetodoPago()));
		}
		else{
			comprobante.setMetodoPago(new C_MetodoDePago("PUE"));
		}
		
		comprobante.setEmisor(construirEmisor());
		comprobante.setReceptor(construirReceptor(venta));
		construirConceptos(venta.getEnvios(), comprobante);
		
		//comprobante.setImpuestos(construirImuestos(comprobante.getSubTotal()));
		BigDecimal total = comprobante.getSubTotal().add(comprobante.getImpuestos().getTotalImpuestosTrasladados());
		comprobante.setTotal(total.setScale(2, RoundingMode.HALF_UP));

		return comprobante;
//		venta.setXml();
	}
	

	private static Emisor construirEmisor() {
		Emisor emisor = new Comprobante.Emisor();
		
		/////Produccion
		emisor.setRfc("MERA680707KA3");
		emisor.setNombre("ADOLFO FERMÍN MERCADO RUBIO");
		emisor.setRegimenFiscal(new C_RegimenFiscal("612"));
		///////Local
//		emisor.setRfc("AAA010101AAA");
//		emisor.setRegimenFiscal(new C_RegimenFiscal("601"));			
		return emisor;
	}
	
	private static Receptor construirReceptor(VentaFac vf) {
		Receptor receptor = new Comprobante.Receptor();
		if(vf!=null){
			receptor.setRfc(vf.getRfc());
			receptor.setNombre(vf.getNombre());
			receptor.setUsoCFDI(new C_UsoCFDI(vf.getUsoCfdi()));
		
		}
		return receptor;
	}
	
	private static void construirConceptos(List<Envio> envios, Comprobante c) {
		
		
		Conceptos conceptos = new Conceptos();
		BigDecimal subtotal = new BigDecimal(0);
		BigDecimal importeImpuesto= new BigDecimal(0);
		
		double importeTotalIVA = 0;
		for (Envio envio : envios) {
			Concepto concepto = new Concepto();
			double cantidad = Double.parseDouble("1.00");
			concepto.setNoIdentificacion("002");//////////////id de producto
			concepto.setCantidad( BigDecimal.valueOf( (double)cantidad ) );
			concepto.setUnidad("ENVIO");          /////////////unidad
			concepto.setClaveUnidad(new C_ClaveUnidad("SX")); ///cve de unidad
			concepto.setClaveProdServ("78102203");     ///clave sat
			concepto.setDescripcion("Envío "+envio.getRastreo()+" "+envio.getGuia()+" "+envio.getPaquete().getTipoPaquete()); //descripcion
			
			double valorUnitarioSinIVA = envio.getPrecio() / 1.16;
			double importeIVA = valorUnitarioSinIVA * 0.16 * cantidad;
			double importe = valorUnitarioSinIVA * cantidad;
			
			concepto.setValorUnitario( BigDecimal.valueOf( (double)valorUnitarioSinIVA ).setScale(2, RoundingMode.HALF_UP) );
			concepto.setImporte( BigDecimal.valueOf( (double)importe ).setScale(2, RoundingMode.HALF_UP) );
			
			Comprobante.Conceptos.Concepto.Impuestos impuestos= new Comprobante.Conceptos.Concepto.Impuestos();
			Comprobante.Conceptos.Concepto.Impuestos.Traslados traslados= new Comprobante.Conceptos.Concepto.Impuestos.Traslados();
			Comprobante.Conceptos.Concepto.Impuestos.Traslados.Traslado traslado= new Comprobante.Conceptos.Concepto.Impuestos.Traslados.Traslado();
			BigDecimal impimp=new BigDecimal(importeIVA).setScale(2, RoundingMode.HALF_UP);
			traslado.setBase(new BigDecimal(importe).setScale(2, RoundingMode.HALF_UP));
			traslado.setImporte(impimp);
			traslado.setTasaOCuota(new BigDecimal(0.16).setScale(2, RoundingMode.HALF_UP));
			traslado.setTipoFactor(new C_TipoFactor("Tasa"));
			traslado.setImpuesto(new C_Impuesto("002"));
			traslados.getTraslado().add(traslado);
			impuestos.setTraslados(traslados);
			concepto.setImpuestos(impuestos);
			
			conceptos.getConcepto().add(concepto);
			subtotal = subtotal.add(concepto.getImporte());
			importeImpuesto=importeImpuesto.add(impimp);
			importeTotalIVA += importeIVA;
		}
		
		c.setSubTotal(subtotal);
		c.setConceptos(conceptos);
		c.setImpuestos(construirImuestos(importeImpuesto));
	}
	
	private static Impuestos construirImuestos(BigDecimal importe) {
		Impuestos impuestos = new Impuestos();
		Traslados traslados = new Traslados();
		
		Traslado traslado = new Traslado();
		traslado.setImpuesto(new C_Impuesto("IVA"));
		traslado.setTasaOCuota(new BigDecimal(0.16).setScale(2, RoundingMode.HALF_UP));
		traslado.setTipoFactor(new C_TipoFactor("Tasa"));
		traslado.setImpuesto(new C_Impuesto("002"));
		traslado.setImporte(importe.setScale(2, RoundingMode.HALF_UP));
		
		traslados.getTraslado().add(traslado);
		impuestos.setTraslados(traslados);
		impuestos.setTotalImpuestosTrasladados(traslado.getImporte());
		return impuestos;
	}
	
	private String getFormaPago(String fp){
		switch(fp){
		case "Tarjeta de Cr�dito":{
			return "04";
		}
		case "Tarjeta de D�bito":{
			return "28";
		}
		case "Efectivo":{
			return "01";
		}
		case "Transferencia":{
			return "03";
		}
		case "Cheque":{
			return "02";
		}
		}
		return "";
	}
	
	
}
