package com.tikal.mensajeria.dao;

import java.util.Date;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.tikal.mensajeria.modelo.entity.ContadorServicio;
import com.tikal.mensajeria.modelo.entity.Venta;

public interface VentaDao {
	
	public void save(Venta v);

	public void delete(Venta v);

	public void update(Venta v);
	
	public Venta consult(Long id);
	
	//public static AeronaveEntity consult(String numeroSerie);
	public List<Venta> findAll();
	
	public List<Venta> findAllAbierta();
	
	public List<Venta> findAllAbiertaBySuc(Long idSucursal) ;
	
	public List<Venta> getVentas(Date inicio, Date fin, Long idSucursal);
	
	public List<Venta> findAllAbiertaIF(Date inicio, Date fin); 
	
	public void crearContador(Long folio);
	

}
