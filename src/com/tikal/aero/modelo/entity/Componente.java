package com.tikal.aero.modelo.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


@Entity

public class Componente{
	
	@Id private Long id;
	private String nombre;
	private String claveInterna;
	private String claveManual;
	private String noSerie;
	@Index private Integer d_cantidad;
	private Integer d_pendientes;
	@Index private String fechaApertura;
	@Index private Long idCategoria;
	private Long idUnidad;
	private Long idCondicion;
	private Integer maximo;
	private Integer minimo;
	private String anaquel;
	private String repisa;
	private String imagen;
	private Boolean certificado8130;
	private String d_modelo;
	private String d_marca;
	private String d_observaciones;
	
	
	
	
	
	
	
	
	
	
	public String getNoSerie() {
		return noSerie;
	}
	public void setNoSerie(String noSerie) {
		this.noSerie = noSerie;
	}
	public String getD_modelo() {
		return d_modelo;
	}
	public void setD_modelo(String d_modelo) {
		this.d_modelo = d_modelo;
	}
	public String getD_marca() {
		return d_marca;
	}
	public void setD_marca(String d_marca) {
		this.d_marca = d_marca;
	}
	public String getD_observaciones() {
		return d_observaciones;
	}
	public void setD_observaciones(String d_observaciones) {
		this.d_observaciones = d_observaciones;
	}
	public Boolean getCertificado8130() {
		return certificado8130;
	}
	public void setCertificado8130(Boolean certificado8130) {
		this.certificado8130 = certificado8130;
	}
	public Integer getMaximo() {
		return maximo;
	}
	public void setMaximo(Integer maximo) {
		this.maximo = maximo;
	}
	public Integer getMinimo() {
		return minimo;
	}
	public void setMinimo(Integer minimo) {
		this.minimo = minimo;
	}
	public Long getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(Long idUnidad) {
		this.idUnidad = idUnidad;
	}
	public Long getIdCondicion() {
		return idCondicion;
	}
	public void setIdCondicion(Long idCondicion) {
		this.idCondicion = idCondicion;
	}
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getnombre() {
		return nombre;
	}
	public void setnombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getclaveInterna() {
		return claveInterna;
	}
	public void setclaveInterna(String claveInterna) {
		this.claveInterna = claveInterna;
	}
	public String getclaveManual() {
		return claveManual;
	}
	public void setclaveManual(String claveManual) {
		this.claveManual = claveManual;
	}
	public Integer getD_cantidad() {
		// if (this.getD_cantidad() < 0) d_cantidad=0;
		return d_cantidad;
	}
	public void setD_cantidad(Integer d_cantidad) {
		this.d_cantidad = d_cantidad;
	}
	public Integer getD_pendientes() {
		return d_pendientes;
	}
	public void setD_pendientes(Integer d_pendientes) {
		this.d_pendientes = d_pendientes;
	}
	public String getAnaquel() {
		return anaquel;
	}
	public void setAnaquel(String anaquel) {
		this.anaquel = anaquel;
	}
	public String getRepisa() {
		return repisa;
	}
	public void setRepisa(String repisa) {
		this.repisa = repisa;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	
	
	
	
}
