package com.tikal.aero.modelo.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Tarea {

	@Id private Long id;
	@Index private Long idComponente;
	@Index private String marcaAeronave;
	@Index private String modeloAeronave;
	@Index private String nombre;
	@Index private Integer secuencia;
	@Index private String unidadSecuencia;
	private  String descripcion;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdComponente() {
		return idComponente;
	}
	public void setIdComponente(Long idComponente) {
		this.idComponente = idComponente;
	}
	public String getMarcaAeronave() {
		return marcaAeronave;
	}
	public void setMarcaAeronave(String marcaAeronave) {
		this.marcaAeronave = marcaAeronave;
	}
	public String getModeloAeronave() {
		return modeloAeronave;
	}
	public void setModeloAeronave(String modeloAeronave) {
		this.modeloAeronave = modeloAeronave;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
	public String getUnidadSecuencia() {
		return unidadSecuencia;
	}
	public void setUnidadSecuencia(String unidadSecuencia) {
		this.unidadSecuencia = unidadSecuencia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
