package com.tikal.mensajeria.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import org.springframework.stereotype.Service;

import com.tikal.mensajeria.dao.ClienteFacDao;
import com.tikal.mensajeria.modelo.entity.Emisor;
import com.tikal.mensajeria.modelo.entity.Receptor;
import com.tikal.mensajeria.modelo.vo.ClienteFac;


@Service("clientefacDao")
public class ClienteFacDaoImpl implements ClienteFacDao{

	@Override
	public void crear(ClienteFac cf) {
		// TODO Auto-generated method stub
		ofy().save().entity(cf).now();
	}

	@Override
	public ClienteFac consultar(String rfc) {
		// TODO Auto-generated method stub
		return ofy().load().type(ClienteFac.class).id(rfc).now();
		
	}

}




