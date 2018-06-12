package com.tikal.mensajeria.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.mensajeria.dao.GuiaDao;

import com.tikal.mensajeria.modelo.entity.Guia;

@Service("guiaDao")
public class GuiaDaoImpl implements GuiaDao {
	
	public  void save(Guia g) {    	
        ofy().save().entity(g).now();
    }

    
    public void delete(Guia g) {
    	 System.out.println("si esta en daoimpl eliminando"+g);
        ofy().delete().entity(g).now();
        System.out.println("eliminando...guia");
    }


	public void update(Guia g) {
	   System.out.print("Guia:"+g.getId());
	   Guia old = this.consult(g.getId());
	System.out.print("old:"+old);
		if (old != null) {
			old.setNumero(g.getNumero());
			old.setEstatus(g.getEstatus());
			old.setIdSucursal(g.getIdSucursal());
				
		}

			ofy().save().entity(old);
   }

    

	public Guia consult(Long id) {
	   System.out.println("si esta en daoimpl consultando la guia.."+id);
      return ofy().load().type(Guia.class).id(id).now();
		
	}


   
	public List<Guia> findAll() {
		return ofy().load().type(Guia.class).list();
	}


	@Override
	public void findAll(Guia g) {
		// TODO Auto-generated method stub
		
	}


	//@Override
	public List<Guia> getByEstatus(String estatus) {
		// TODO Auto-generated method stub
		
		return ofy().load().type(Guia.class).filter("estatus",estatus).list();
	}


	@Override
	public List<Guia> getBySucursal(Long idSucursal) {
		// TODO Auto-generated method stub
		return ofy().load().type(Guia.class).filter("idSucursal",idSucursal).list();
	}


	


}
