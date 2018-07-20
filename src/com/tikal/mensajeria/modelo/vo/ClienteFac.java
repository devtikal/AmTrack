package com.tikal.mensajeria.modelo.vo;

import com.tikal.cacao.sat.cfd.TUbicacion;

public class ClienteFac {
	
	private String rfc;
	private String nombre;
	private TUbicacion domicilio;
	
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
	
	
	
	

}
