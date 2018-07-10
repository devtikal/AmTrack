package com.tikal.mensajeria.modelo.entity;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
//import com.tikal.mensajeria.modelo.login.Sucursal;

@Entity
public class Envio {
	

	@Id Long id;	
	private Persona cliente;
	private Persona destinatario;
	@Index  String empresa;
	private String kilataje;
	private Paquete paquete;	
	@Index private String guia;
	@Index private String rastreo;
	private String tipoEnvio;
	private String tipoServicio;
	private Double precio;	
	private Double costoSeguro;
	private List<Material> materiales;
	
	
	
//	@Index private String fecha;
	//@Index private Usuario usuario;
//	@Index private String folio; //numero de envio
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getKilataje() {
		return kilataje;
	}
	public void setKilataje(String kilataje) {
		this.kilataje = kilataje;
	}
	public Persona getCliente() {
		return cliente;
	}
	public void setCliente(Persona cliente) {
		this.cliente = cliente;
	}
	public Persona getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Persona destinatario) {
		this.destinatario = destinatario;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public Paquete getPaquete() {
		return paquete;
	}
	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}
	
	public Double getCostoSeguro() {
		return costoSeguro;
	}
	public void setCostoSeguro(Double costoSeguro) {
		this.costoSeguro = costoSeguro;
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
	public String getTipoEnvio() {
		return tipoEnvio;
	}
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
//	public String getFolio() {
//		return folio;
//	}
//	public void setFolio(String folio) {
//		this.folio = folio;
//	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public List<Material> getMateriales() {
		return materiales;
	}
	public void setMateriales(List<Material> materiales) {
		this.materiales = materiales;
	}
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	
	
	
}
