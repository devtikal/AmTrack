/**
 * 
 */
package com.tikal.mensajeria.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.mensajeria.dao.ReceptorDAO;
import com.tikal.mensajeria.modelo.entity.Receptor;

/**
 * @author Tikal
 *
 */
@Service("receptordao")
public class ReceptorDAOImpl implements ReceptorDAO {

	@Override
	public void guardar(Receptor receptor) {
		ofy().save().entity(receptor).now();

	}

	@Override
	public Receptor consultar(String rfc) {
		return ofy().load().type(Receptor.class).id(rfc).now();
	}

	@Override
	public List<Receptor> consultarPorEmisor(String rfcEmisor) {
		return ofy().load().type(Receptor.class).filter("listaRFCEmisores", rfcEmisor).list();
	}

}
