package com.tikal.mensajeria.modelo.vo;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.tikal.cacao.sat.cfd.TUbicacion;
@Entity
public class ClienteFac {
	@Id private Long id;
	private String rfc;
	private String nombre;
	private TUbicacion domicilio;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	
	
	

}
