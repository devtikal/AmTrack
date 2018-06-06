package com.tikal.mensajeria.dao;

import com.tikal.mensajeria.modelo.entity.Destinatario;

public interface DestinatarioDao {
	public void save(Destinatario d);

	public void delete(Destinatario d);

	public void update(Destinatario d);
	
	public Destinatario consult(Long id);
	
	//public static AeronaveEntity consult(String numeroSerie);
	
    
	public void findAll(Destinatario d);	

}
