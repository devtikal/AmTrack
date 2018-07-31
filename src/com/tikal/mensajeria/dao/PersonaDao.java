package com.tikal.mensajeria.dao;


import java.util.List;

import com.tikal.mensajeria.modelo.entity.Persona;


public interface PersonaDao {

	public void save(Persona p);

	public void delete(Persona p);

	public void update(Persona p);
	
	public Persona consult(Long id);
	
	//public static AeronaveEntity consult(String numeroSerie);
	
    
	public List<Persona> findAll();	
	
	public List<Persona> getByNombre(String nombre);

}
