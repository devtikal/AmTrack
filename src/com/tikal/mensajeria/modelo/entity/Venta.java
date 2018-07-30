package com.tikal.mensajeria.modelo.entity;

import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.tikal.mensajeria.modelo.login.Usuario;
import com.tikal.mensajeria.modelo.vo.VentaFac;

@Entity
public class Venta {

	@Id private Long id;
	@Index private Usuario usuario;
	@Index private Long folio;
	@Index private Date fecha;
	private Integer cantidad;
	@Index private String estatus;
	List<Long> envios;
	private Double total;
	@Index String numeroFactura;
	@Index private Long idSucursal;
	private String uuid;
	private Long idVentaFac;
	
	
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Long getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public List<Long> getEnvios() {
		return envios;
	}
	public void setEnvios(List<Long> envios) {
		this.envios = envios;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public Long getIdVentaFac() {
		return idVentaFac;
	}
	public void setIdVentaFac(Long idVentaFac) {
		this.idVentaFac = idVentaFac;
	}

	
	
	
	
}
