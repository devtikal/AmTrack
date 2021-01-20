package com.tikal.aero.dao;



import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aero.modelo.entity.Aeronave;

@Service("aeronaveDao")
public interface AeronaveDao {


public void save(Aeronave a);

	public void delete(Aeronave a);

	public void update(Aeronave nave);
	
	public Aeronave consult(Long id);
	
	//public static Aeronave consult(String numeroSerie);
	
    
	public void findAll(Aeronave a);	
	
	public List<Aeronave> getAll();
	
	public List<Aeronave> getAllN();


	
}

