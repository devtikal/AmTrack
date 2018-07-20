/**
 * 
 */
package com.tikal.mensajeria.dao;

import java.util.List;

import com.tikal.mensajeria.modelo.entity.Receptor;

/**
 * @author Tikal
 *
 */
public interface ReceptorDAO {

	void guardar(Receptor receptor);
	
	Receptor consultar(String rfc);
	
	List<Receptor> consultarPorEmisor(String rfcEmisor);
}
