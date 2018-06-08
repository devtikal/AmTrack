package com.tikal.mensajeria.dao;

import java.util.List;

import com.tikal.mensajeria.modelo.login.Sucursal;

public interface SucursalDao {
	
	public void save(Sucursal s);

	public void delete(Sucursal s);

	public void update(Sucursal s);
	
	public Sucursal consult(Long id);
	
	//public static AeronaveEntity consult(String numeroSerie);
	
    
	public List<Sucursal> findAll();
	
	

}
