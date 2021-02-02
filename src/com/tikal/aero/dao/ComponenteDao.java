package com.tikal.aero.dao;


import java.util.List;

import org.springframework.stereotype.Service;

//import com.tikal.aero.controllers.vo.OrdenVo;

import com.tikal.aero.modelo.entity.Componente;
@Service("componenteDao")
public interface ComponenteDao {
	
	public void save(Componente c);
	
	public void delete(Componente c);
	
	public void update(Componente c); 
	
	public Componente consult(Long id);
	
	public List<Componente> getAll();
	

	//void findAll();
	
	
}
