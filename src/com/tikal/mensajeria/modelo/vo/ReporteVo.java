package com.tikal.mensajeria.modelo.vo;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFRow;

public class ReporteVo {
	
	private Date fecha;
	private Long folio;
	private String remitente;
	private String guia;
	private String rastreo;
	private String tipoPaquete;
	private String tipoEnvio;
	private String empresa;
	private Double precio;
	private Double costoSeguro;
	private Double Total;
	private Double Acumulado;
	
	
	
	public Long getFolio() {
		return folio;
	}
	public void setFolio(Long folio) {
		this.folio = folio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getGuia() {
		return guia;
	}
	public void setGuia(String guia) {
		this.guia = guia;
	}
	public String getRastreo() {
		return rastreo;
	}
	public void setRastreo(String rastreo) {
		this.rastreo = rastreo;
	}
	public String getTipoPaquete() {
		return tipoPaquete;
	}
	public void setTipoPaquete(String tipoPaquete) {
		this.tipoPaquete = tipoPaquete;
	}
	public String getTipoEnvio() {
		return tipoEnvio;
	}
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Double getCostoSeguro() {
		return costoSeguro;
	}
	public void setCostoSeguro(Double costoSeguro) {
		this.costoSeguro = costoSeguro;
	}
	public Double getTotal() {
		return Total;
	}
	public void setTotal(Double total) {
		Total = total;
	}
	public Double getAcumulado() {
		return Acumulado;
	}
	public void setAcumulado(Double acumulado) {
		Acumulado = acumulado;
	}
	
	public void llenarRenglon(HSSFRow r){
		for(int i=0;i<12;i++){
			r.createCell(i);
		}
		
		r.getCell(0).setCellValue(this.fecha);
		r.getCell(1).setCellValue(this.folio);
		r.getCell(2).setCellValue(this.remitente);		
		r.getCell(3).setCellValue(this.guia);
		r.getCell(4).setCellValue(this.rastreo);
		r.getCell(5).setCellValue(this.tipoPaquete);
		r.getCell(6).setCellValue(this.tipoEnvio);
		r.getCell(7).setCellValue(this.empresa);
		r.getCell(8).setCellValue(this.precio);
		r.getCell(9).setCellValue(this.costoSeguro);		
		r.getCell(10).setCellValue(this.Total);
		//r.getCell(11).setCellValue(this.Acumulado);
	}

	

}
