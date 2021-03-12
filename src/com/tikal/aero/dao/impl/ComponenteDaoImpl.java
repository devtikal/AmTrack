package com.tikal.aero.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


import com.tikal.aero.dao.ComponenteDao;

import com.tikal.aero.modelo.entity.Componente;

@Service("componenteDao")
public class ComponenteDaoImpl implements ComponenteDao{
	
	public void save(Componente c) {   
		System.out.println("si lega aqui");
        ofy().save().entity(c).now();
    }
	public List<Componente> getAll() {                   

		return ofy().load().type(Componente.class).list();
	}

	public List<Componente> getAllF() {                   
		return ofy().load().type(Componente.class).filter("d_cantidad >", 0).list();
		
	}
	
	public List<Componente> getAll0() {                   
		return ofy().load().type(Componente.class).filter("d_cantidad", 0).list();
		
	}

	public void findAll() {
	// TODO Auto-generated method stub
	
	}

	@Override
	public void delete(Componente c) {
		// TODO Auto-generated method stub@Override
		ofy().delete().entity(c).now();
			//object.setActivo(false);
			//update(c);
		
		
	}


	@Override
	public void update(Componente c) {
		// TODO Auto-generated method stub
		
			//ofy().save().entity(c).now();
			System.out.println("guarda los nuevos datos del componente" );
			
			Componente old = this.consult(c.getId());
			if (old != null) {
				old.setAnaquel(c.getAnaquel());
				old.setCertificado8130(c.getCertificado8130());
				old.setCantidad(c.getCantidad());
				old.setclaveInterna(c.getclaveInterna());
				old.setnombre(c.getnombre());
				old.setMarcaAeronave(c.getMarcaAeronave());
				old.setModeloAeronave(c.getModeloAeronave());
				old.setObservaciones(c.getObservaciones());
				old.setclaveManual(c.getclaveManual());
				old.setCategoria(c.getCategoria());
				old.setSubCategoria(c.getSubCategoria());
				old.setEstado(c.getEstado());
				
				old.setUnidad(c.getUnidad());
				old.setMaximo(c.getMaximo());
				old.setMinimo(c.getMinimo());
				old.setNoSerie(c.getNoSerie());
				old.setRepisa(c.getRepisa());
				
			}	
				ofy().save().entity(old);
		}

		
	
	@Override
	public Componente consult(Long id) {
		System.out.println("consultando el componente " );
       return ofy().load().type(Componente.class).id(id).now();
		
	}
	


}
 