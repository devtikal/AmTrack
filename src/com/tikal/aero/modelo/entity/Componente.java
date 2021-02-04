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
	@Index private Integer cantidad;
	

//	@Index private Long idCategoria;
//	private Long idUnidad;
//	private Long idCondicion;
	private String unidad;
	private Integer maximo;
	private Integer minimo;
	private String anaquel;
	private String repisa;
	private String imagen;
	private Boolean certificado8130;
	private String modeloAeronave;
	private String marcaAeronave;
	private String observaciones;
	
	
	
	
	
	
	
	
	
	
	public String getNoSerie() {
		return noSerie;
	}
	public void setNoSerie(String noSerie) {
		this.noSerie = noSerie;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClaveInterna() {
		return claveInterna;
	}
	public void setClaveInterna(String claveInterna) {
		this.claveInterna = claveInterna;
	}
	public String getClaveManual() {
		return claveManual;
	}
	public void setClaveManual(String claveManual) {
		this.claveManual = claveManual;
	}
	public String getModeloAeronave() {
		return modeloAeronave;
	}
	public void setModeloAeronave(String modeloAeronave) {
		this.modeloAeronave = modeloAeronave;
	}
	public String getMarcaAeronave() {
		return marcaAeronave;
	}
	public void setMarcaAeronave(String marcaAeronave) {
		this.marcaAeronave = marcaAeronave;
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
	
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
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
	
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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
