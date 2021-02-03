package com.tikal.aero.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aero.modelo.entity.Tarea;


	@Service("tareaDao")
	
	public interface TareaDao {
		
		public void save(Tarea t);
		
		public void delete(Tarea t);
		
		public void update(Tarea t); 
		
		public Tarea consult(Long id);
		
		public List<Tarea> getAll();
			
		public List<Tarea> byComponente(Long idComponente);
	
}