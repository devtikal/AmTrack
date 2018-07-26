package com.tikal.mensajeria.dao;

import com.tikal.mensajeria.modelo.vo.VentaFac;

public interface VentaFacDao {
	
	void crear(VentaFac vf);
	
	VentaFac consultar( Long idVentaFac);
}
