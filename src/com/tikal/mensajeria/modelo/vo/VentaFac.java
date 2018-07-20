package com.tikal.mensajeria.modelo.vo;

import com.tikal.cacao.sat.cfd.TUbicacion;

public class VentaFac {
	
	private String usoCfdi;
	private String formaPago;
	private String metodoPago;
	private String rfc;
	private String nombre;
	private TUbicacion domicilio;
	private String mail;
	
	public String getUsoCfdi() {
		return usoCfdi;
	}
	public void setUsoCfdi(String usoCfdi) {
		this.usoCfdi = usoCfdi;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getMetodoPago() {
		return metodoPago;
	}
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TUbicacion getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(TUbicacion domicilio) {
		this.domicilio = domicilio;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	
	
	

}
