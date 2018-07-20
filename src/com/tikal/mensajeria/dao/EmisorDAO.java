package com.tikal.mensajeria.dao;

import com.tikal.mensajeria.modelo.entity.Emisor;
import com.tikal.mensajeria.modelo.entity.Receptor;

/**
 * @author Tikal
 *
 */
public interface EmisorDAO {

	void crear(Emisor emisor);
	
	Emisor consultar(String rfc);
	
	public void addReceptor(String rfc, Receptor r);
	
	public void updateReceptor(String rfc, Receptor r, int indice);
	
	public void deleteReceptor(String rfc,int indice);
	
}
