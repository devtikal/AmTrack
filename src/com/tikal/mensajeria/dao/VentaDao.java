package com.tikal.mensajeria.dao;

import java.util.Date;
import java.util.List;


import com.tikal.mensajeria.modelo.entity.Venta;

public interface VentaDao {
	
	public void save(Venta v);

	public void delete(Venta v);

	public void update(Venta v);
	
	public Venta consult(Long id);
	
	//public static AeronaveEntity consult(String numeroSerie);
	public List<Venta> findAll();
	
	public List<Venta> getVentas(Date inicio, Date fin);
	
	

}
