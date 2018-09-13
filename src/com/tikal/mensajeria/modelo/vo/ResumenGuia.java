package com.tikal.mensajeria.modelo.vo;

import java.util.List;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.tikal.mensajeria.modelo.entity.Guia;

public class ResumenGuia {
	
	@Id private Long id;
	 private String inicia;
	 private String termina;
	 private String estatus;
	 private String sucursal;
	 private String tipoGuia;
	 private Integer cantidad;
	 private List<Guia> guias;
	 
	 
	 
	 
	public List<Guia> getGuias() {
		return guias;
	}
	public void setGuias(List<Guia> guias) {
		this.guias = guias;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
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
