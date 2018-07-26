package com.tikal.mensajeria.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import org.springframework.stereotype.Service;

import com.tikal.mensajeria.dao.VentaFacDao;
import com.tikal.mensajeria.modelo.vo.VentaFac;

@Service("ventaFacDao")

public class VentaFacDaoImpl implements VentaFacDao {
	
	@Override
	public void crear(VentaFac vf) {
		// TODO Auto-generated method stub
		ofy().save().entity(vf).now();
	}

	@Override
	public VentaFac consultar(Long idVentaFac) {
		// TODO Auto-generated method stub
		return ofy().load().type(VentaFac.class).id(idVentaFac).now();
		
	}

}
