package com.tikal.mensajeria.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;


import com.tikal.mensajeria.dao.PersonaDao;
import com.tikal.mensajeria.modelo.entity.Guia;
import com.tikal.mensajeria.modelo.entity.Persona;

@Service("personaDao")
public class PersonaDaoImpl implements PersonaDao {
	
	
	public  void save(Persona p) {    	
        ofy().save().entity(p).now();
    }

    
    public void delete(Persona p) {
    	 System.out.println("si esta en daoimpl eliminando"+p);
        ofy().delete().entity(p).now();
        System.out.println("eliminando...persona");
    }


	public void update(Persona p) {
	   System.out.print("pesona:"+p.getId());
	   Persona old = this.consult(p.getId());
	System.out.print("old:"+old);
		if (old != null) {
			old.setNombre(p.getNombre());
			old.setEnAtencion(p.getEnAtencion()); 
			old.setNoExterior(p.getNoExterior());
			old.setNoInterior(p.getNoInterior());
			old.setColonia(p.getColonia());
			old.setLocalidad(p.getLocalidad());
			old.setMunicipio(p.getMunicipio());
			old.setCodigoPostal(p.getCodigoPostal());
			old.setEstado(p.getEstado());
			old.setTelefono(p.getTelefono());
			old.setReferencias(p.getReferencias());
				
		}

			ofy().save().entity(old);
   }

    

	public Persona consult(Long id) {
	   System.out.println("si esta en daoimpl consultando destinatario.."+id);
      return ofy().load().type(Persona.class).id(id).now();
		
	}


   
	public List<Persona> findAll() {
		return ofy().load().type(Persona.class).list();
	}


	


	@Override
	public Persona getByNombre(String nombre) {
		// TODO Auto-generated method stub
		List<Persona> lista= ofy().load().type(Persona.class).filter("nombre", nombre).list();
		System.out.println(" lista de personas :"+lista.size());
		if (lista.size() == 0) {
			return null;
		}
		Persona nuevo = lista.get(0);
		return nuevo;
	}





	


}
