package com.tikal.mensajeria.modelo.entity;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.tikal.mensajeria.modelo.login.Usuario;

@Entity
public class Venta {

	@Id private Long id;
	@Index private Usuario usuario;
	@Index private Long folio;
	@Index private String fecha;
	private Integer cantidad;
	private String estatus;
	List<Long> envios;
	
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
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
	public Long getFolio() {
		return folio;
	}
	public void setFolio(Long folio) {
		this.folio = folio;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public List<Long> getEnvios() {
		return envios;
	}
	public void setEnvios(List<Long> envios) {
		this.envios = envios;
	}
	
	
	
	
}
