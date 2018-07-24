package com.tikal.mensajeria.modelo.vo;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.tikal.cacao.sat.cfd.TUbicacion;
import com.tikal.mensajeria.modelo.entity.Envio;

@Entity
public class VentaFac {
	
	@Id private Long id;
	private String usoCfdi;
	private String formaPago;
	private String metodoPago;
	private String rfc;
	private String nombre;
	private TUbicacion domicilio;
	private String mail;
	private List<Envio> envios;
	private String uuid;
	
	
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public List<Envio> getEnvios() {
		return envios;
	}
	public void setEnvios(List<Envio> envios) {
		this.envios = envios;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
