package com.tikal.mensajeria.dao;

import com.tikal.mensajeria.modelo.entity.Empresa;


public interface EmpresaDao {

	public void save(Empresa e);

	public void delete(Empresa e);

	public void update(Empresa e);
	
	public Empresa consult(Long id);
	
	//public static AeronaveEntity consult(String numeroSerie);
	
    
	public void findAll(Empresa e);	

}
