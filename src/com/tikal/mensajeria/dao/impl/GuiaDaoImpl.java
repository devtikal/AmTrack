package com.tikal.mensajeria.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.mensajeria.dao.GuiaDao;

import com.tikal.mensajeria.modelo.entity.Guia;
import com.tikal.mensajeria.modelo.login.Usuario;

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
			old.setSucursal(g.getSucursal());
				
		}

			ofy().save().entity(old);
   }

    

	public Guia consult(Long id) {
	   System.out.println("si esta en daoimpl consultando la guia.."+id);
      return ofy().load().type(Guia.class).id(id).now();
		
	}


   
	public List<Guia> findAll() {
		return ofy().load().type(Guia.class).order("numero").list();
	}


	


	@Override
	public List<Guia> getByEstatus(String estatus) {
		// TODO Auto-generated method stub
		
		return ofy().load().type(Guia.class).filter("estatus",estatus).list();
	}


	@Override
	public List<Guia> getBySucursal(Long idSucursal) {
		// TODO Auto-generated method stub
		return ofy().load().type(Guia.class).filter("idSucursal",idSucursal).list();
	}


	@Override
	public Guia getByNumero(String numero) {
		// TODO Auto-generated method stub
		return ofy().load().type(Guia.class).filter("numero",numero).list().get(0);
	}


	@Override
	public Guia getByEstSuc(String estatus, Long idSucursal) {
		// TODO Auto-generated method stub
		System.out.println("esta en daoimpl envio  get by status:"+estatus+" Suc:"+idSucursal);
		List<Guia> g= ofy().load().type(Guia.class).filter("estatus",estatus).filter("idSucursal", idSucursal).order("numero").list();
		System.out.println(" cuantas guias :"+g.size());
		if (g.size() == 0) {
			return null;
		}
		Guia nuevo = g.get(0);
		return nuevo;
	}


	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		 System.out.println("si esta en daoimpl eliminando todAS LAS GUIAS");
		 List<Guia> lista =findAll();
		 for (Guia g:lista){
			 ofy().delete().entity(g).now();
		 }
	     //   ofy().delete().type(Guia.class).;
	        System.out.println("eliminando...guiaSSSS");
		
	}


	@Override
	public Guia getByEstSucK(String estatus, Long idSucursal, String kilataje) {
		// TODO Auto-generated method stub
		return null;
	}


	


}
