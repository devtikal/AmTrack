package com.tikal.aero.modelo.entity;


import  com.googlecode.objectify.annotation.Entity ;
import  com.googlecode.objectify.annotation.Id ;
import com.googlecode.objectify.annotation.Index;




@Entity 
public  class  Aeronave {
	
    @Id Long id;
    @Index private String numeroAeronave;
	@Index private String matricula;
	@Index private String marca;
    @Index  private String modelo;
    @Index private String numeroSerie;
    private String aterrizaje;
    private int tiempovuelo;
    private String planeador;
    private String motor1;
    private String motor2;
    private String marcas;
    @Index private String nacionalidad;
    
    
	//@Container OrdenEntity orden;
    
    
    
	
	
	public String getMatricula() {
		return matricula;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroAeronave() {
		return numeroAeronave;
	}
	public void setNumeroAeronave(String numeroAeronave) {
		this.numeroAeronave = numeroAeronave;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getAterrizaje() {
		return aterrizaje;
	}
	public void setAterrizaje(String aterrizaje) {
		this.aterrizaje = aterrizaje;
	}
	public int getTiempovuelo() {
		return tiempovuelo;
	}
	public void setTiempovuelo(int tiempovuelo) {
		this.tiempovuelo = tiempovuelo;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	
	public String getPlaneador() {
		return planeador;
	}
	public void setPlaneador(String planeador) {
		this.planeador = planeador;
	}
	public String getMotor1() {
		return motor1;
	}
	public void setMotor1(String motor1) {
		this.motor1 = motor1;
	}
	public String getMotor2() {
		return motor2;
	}
	public void setMotor2(String motor2) {
		this.motor2 = motor2;
	}
	public String getMarcas() {
		return marcas;
	}
	public void setMarcas(String marcas) {
		this.marcas = marcas;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
}


