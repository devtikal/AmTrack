package com.tikal.aero.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aero.dao.HistorialCompDao;
import com.tikal.aero.modelo.entity.HistorialComponente;

//import com.tikal.aero.modelo.entity.Tarea;

@Service("historialCompDao")
public class HistorialCompDaoImpl implements HistorialCompDao {

	
	public void save(HistorialComponente h) {   
		System.out.println("si lega aqui");
        ofy().save().entity(h).now();
    }
	public List<HistorialComponente> getAll() {                   

		return ofy().load().type(HistorialComponente.class).list();
	}

	
	public List<HistorialComponente> byComponente(Long idComponente) {                   

		return ofy().load().type(HistorialComponente.class).filter("idComponente", idComponente).list();
	}

	
	public void delete(HistorialComponente h) {
		// TODO Auto-generated method stub@Override
		ofy().delete().entity(h).now();
			//object.setActivo(false);
			//update(c);
		
		
	}



	public void update(HistorialComponente h) {
		// TODO Auto-generated method stub
		
			//ofy().save().entity(c).now();
			System.out.println("guarda los nuevos datos delTarea" );
			
			HistorialComponente old = this.consult(h.getId());
			if (old != null) {
				
				old.setFecha(h.getFecha());
				old.setDescripcion(h.getDescripcion());
				old.setIdComponente(h.getIdComponente());
				
			}	
				ofy().save().entity(old);
		}

		
	
	
	public HistorialComponente consult(Long id) {
		System.out.println("consultandoTarea " );
       return ofy().load().type(HistorialComponente.class).id(id).now();
		
	}
	

}
