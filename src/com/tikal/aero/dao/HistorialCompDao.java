package com.tikal.aero.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aero.modelo.entity.HistorialComponente;


@Service("historialCompDao")
public interface HistorialCompDao {
	
	public void save(HistorialComponente h);
	
	public void delete(HistorialComponente h);
	
	public void update(HistorialComponente h); 
	
	public HistorialComponente consult(Long id);
	
	public List<HistorialComponente> getAll();
		
	public List<HistorialComponente> byComponente(Long idComponente);

}
