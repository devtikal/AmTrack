package com.tikal.mensajeria.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.mensajeria.dao.EmpresaDao;
import com.tikal.mensajeria.modelo.entity.Empresa;


@Service("empresaDao")
public class EmpresaDaoImpl implements EmpresaDao{

	
	public  void save(Empresa e) {    	
        ofy().save().entity(e).now();
    }

    
    public void delete(Empresa e) {
    	 System.out.println("si esta en daoimpl eliminando"+e);
        ofy().delete().entity(e).now();
        System.out.println("eliminando...empresa");
    }


	public void update(Empresa e) {
	   System.out.print("empresa:"+e.getId());
	   Empresa old = this.consult(e.getId());
	System.out.print("old:"+old);
		if (old != null) {
			old.setDescripcion(e.getDescripcion());
				
		}

			ofy().save().entity(old);
   }

    

	public Empresa consult(Long id) {
	   System.out.println("si esta en daoimpl consultando la empresa.."+id);
      return ofy().load().type(Empresa.class).id(id).now();
		
	}


   
	public List<Empresa> findAll() {
		return ofy().load().type(Empresa.class).list();
	}


	@Override
	public void findAll(Empresa e) {
		// TODO Auto-generated method stub
		
	}


	//@Override
	public Empresa getByNombre(String nombre) {
		// TODO Auto-generated method stub
		
		return ofy().load().type(Empresa.class).filter("descripcion",nombre).list().get(0);
	}


	


}
