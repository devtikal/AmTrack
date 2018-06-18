package com.tikal.mensajeria.modelo.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.tikal.mensajeria.modelo.login.Sucursal;
import com.tikal.mensajeria.modelo.login.Usuario;

@Entity
public class Envio {
	

	@Id Long id;
	
	private Usuario usuario;
	//private Persona cliente;
	private String cliente;
	private String domicilioCliente;
	//private Persona destinatario;
	private Destinatario destinatario;
	@Index  Empresa empresa;
	private Paquete paquete;
	
	
	@Index private String fecha;
	private Integer cantidad;
	@Index private Integer guia;
	@Index private Integer rastreo;
	private String tipoEnvio;
	@Index private String folio; //numero de envio
	private Double precio;
	private Double total;
	//private String totalLetra;
	
	
	
	
	
	public Destinatario getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	} 
	
	public String getDomicilioCliente() {
		return domicilioCliente;
	}
	public void setDomicilioCliente(String domicilioCliente) {
		this.domicilioCliente = domicilioCliente;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public Persona getCliente() {
//		return cliente;
//	}
//	public void setCliente(Persona cliente) {
//		this.cliente = cliente;
//	}
//	public Persona getDestinatario() {
//		return destinatario;
//	}
//	public void setDestinatario(Persona destinatario) {
//		this.destinatario = destinatario;
//	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Paquete getPaquete() {
		return paquete;
	}
	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getGuia() {
		return guia;
	}
	public void setGuia(Integer guia) {
		this.guia = guia;
	}
	public Integer getRastreo() {
		return rastreo;
	}
	public void setRastreo(Integer rastreo) {
		this.rastreo = rastreo;
	}
	public String getTipoEnvio() {
		return tipoEnvio;
	}
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
//	public String getTotalLetra() {
//		return totalLetra;
//	}
//	public void setTotalLetra(String totalLetra) {
//		this.totalLetra = totalLetra;
//	}
	
	
	
}
