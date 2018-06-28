package com.tikal.mensajeria.modelo.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Persona {

	@Id Long id;
	@Index private String nombre;
	private String enAtencion;
	private String calle;
	private String noExterior;
	private String noInterior;
	private String colonia;
	private String localidad;
	private String municipio;
	private String estado;
	private Integer codigoPostal;
	private String referencias;
	private String telefono;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getEnAtencion() {
		return enAtencion;
	}
	public void setEnAtencion(String enAtencion) {
		this.enAtencion = enAtencion;
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
	public String getReferencias() {
		return referencias;
	}
	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	

	
}
