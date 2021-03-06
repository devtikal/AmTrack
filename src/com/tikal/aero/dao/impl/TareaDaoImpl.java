package com.tikal.aero.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;


import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aero.dao.TareaDao;
import com.tikal.aero.modelo.entity.Tarea;
@Service("tareaDao")
public class TareaDaoImpl implements TareaDao {

	public void save(Tarea c) {   
		System.out.println("si lega aqui");
        ofy().save().entity(c).now();
    }
	public List<Tarea> getAll() {                   

		return ofy().load().type(Tarea.class).list();
	}

	
	public List<Tarea> byComponente(Long idComponente) {                   

		return ofy().load().type(Tarea.class).filter("idComponente", idComponente).list();
	}

	
	public void delete(Tarea c) {
		// TODO Auto-generated method stub@Override
		ofy().delete().entity(c).now();
			//object.setActivo(false);
			//update(c);
		
		
	}



	public void update(Tarea c) {
		// TODO Auto-generated method stub
		
			//ofy().save().entity(c).now();
			System.out.println("guarda los nuevos datos delTarea" );
			
			Tarea old = this.consult(c.getId());
			if (old != null) {
				
				old.setIdComponente(c.getIdComponente());
				old.setMarcaAeronave(c.getMarcaAeronave());
				old.setModeloAeronave(c.getModeloAeronave());
				old.setNombre(c.getNombre());
				old.setSecuencia(c.getSecuencia());
				old.setDescripcion(c.getDescripcion());
				
				old.setUnidadSecuencia(c.getUnidadSecuencia());
				
			}	
				ofy().save().entity(old);
		}

		
	
	
	public Tarea consult(Long id) {
		System.out.println("consultandoTarea " );
       return ofy().load().type(Tarea.class).id(id).now();
		
	}
	


}
