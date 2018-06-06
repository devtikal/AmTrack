package com.tikal.mensajeria.dao;


import com.tikal.mensajeria.modelo.entity.Paquete;

public interface PaqueteDao {

	public void save(Paquete p);

	public void delete(Paquete p);

	public void update(Paquete p);
	
	public Paquete consult(Long id);
	
	//public static AeronaveEntity consult(String numeroSerie);
	
    
	public void findAll(Paquete p);	
}
