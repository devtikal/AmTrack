package com.tikal.mensajeria.modelo.vo;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.tikal.mensajeria.modelo.entity.Material;
import com.tikal.mensajeria.modelo.login.Sucursal;
import com.tikal.mensajeria.modelo.login.Usuario;

@Entity
public class EnvioVo {
	

	@Id Long id;
	
	private Usuario usuario;
	//private Persona cliente;
	private String cliente;
	//private Persona destinatario;
	private String nombre;
	private String aPaterno;
	private String aMaterno;
	private String nombreCompleto;	
	private String telefono;	
	private String calle;
	private String noExterior;
	private String noInterior;
	private String colonia;
	private String localidad;
	private String municipio;
	private String estado;
	private Integer codigoPostal;
	private Integer referencias;
	
	
	
	
	@Index  String empresa;
	
	private String descripcion;
	private String tipoPaquete;
//	private String tipoEnvio;
	private Double largo;
	private Double alto;
	private Double ancho;
	private Double peso;
	
	private List<Material> materiales;
	
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getaPaterno() {
		return aPaterno;
	}
	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}
	public String getaMaterno() {
		return aMaterno;
	}
	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNoExterior() {
		return noExterior;
	}
	public void setNoExterior(String noExterior) {
		this.noExterior = noExterior;
	}
	public String getNoInterior() {
		return noInterior;
	}
	public void setNoInterior(String noInterior) {
		this.noInterior = noInterior;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipoPaquete() {
		return tipoPaquete;
	}
	public void setTipoPaquete(String tipoPaquete) {
		this.tipoPaquete = tipoPaquete;
	}
	public Double getLargo() {
		return largo;
	}
	public void setLargo(Double largo) {
		this.largo = largo;
	}
	public Double getAlto() {
		return alto;
	}
	public void setAlto(Double alto) {
		this.alto = alto;
	}
	public Double getAncho() {
		return ancho;
	}
	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	@Index private String fecha;
	private Integer cantidad;
	@Index private Integer guia;
	@Index private Integer rastreo;
	private String tipoEnvio;
	@Index private String folio;
	private Double precio;
	private Double total;
	//private String totalLetra;
	
	
	
	
	
	
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
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
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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
	public List<Material> getMateriales() {
		return materiales;
	}
	public void setMateriales(List<Material> materiales) {
		this.materiales = materiales;
	}
	public Integer getReferencias() {
		return referencias;
	}
	public void setReferencias(Integer referencias) {
		this.referencias = referencias;
	}
	
	
	
}
