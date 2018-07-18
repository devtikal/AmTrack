package com.tikal.mensajeria.modelo.vo;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

public class ResumenGuia {
	
	@Id private Long id;
	 private String inicia;
	 private String termina;
	 private String estatus;
	 private String sucursal;
	 private String tipoGuia;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInicia() {
		return inicia;
	}
	public void setInicia(String inicia) {
		this.inicia = inicia;
	}
	public String getTermina() {
		return termina;
	}
	public void setTermina(String termina) {
		this.termina = termina;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getTipoGuia() {
		return tipoGuia;
	}
	public void setTipoGuia(String tipoGuia) {
		this.tipoGuia = tipoGuia;
	}
	
	
	
	

}
