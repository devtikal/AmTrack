package com.tikal.mensajeria.dao;

import com.tikal.mensajeria.modelo.entity.Emisor;

import com.tikal.mensajeria.modelo.vo.ClienteFac;

public interface ClienteFacDao {

	void crear(ClienteFac cf);
	
	ClienteFac consultar(String rfc);
	
	

}
