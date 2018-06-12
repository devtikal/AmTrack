package com.tikal.mensajeria.dao;

import java.util.List;

import com.tikal.mensajeria.modelo.entity.Guia;

public interface GuiaDao {

	
	public void save(Guia g);

	public void delete(Guia g);

	public void update(Guia g);
	
	public Guia consult(Long id);
	
	//public static AeronaveEntity consult(String numeroSerie);
	
    
	public void findAll(Guia g);	
	
	public List<Guia> getByEstatus(String estatus);
	
	public List<Guia> getBySucursal(Long idSucursal);
}
