package com.tikal.mensajeria.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
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
	//System.out.print("old estatus:"+old.getEstatus());
	//System.out.print("new estatus:"+g.getEstatus());
		if (old != null) {
			old.setNumero(g.getNumero());
			old.setEstatus(g.getEstatus());
			old.setIdSucursal(g.getIdSucursal());
			old.setSucursal(g.getSucursal());
			old.setTipoGuia(g.getTipoGuia());
				
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
		
		return ofy().load().type(Guia.class).filter("estatus",estatus).order("numero").list();
	}


	@Override
	public List<Guia> getBySucursal(Long idSucursal) {
		// TODO Auto-generated method stub
	
		if (ofy().load().type(Guia.class).filter("idSucursal",idSucursal).list().isEmpty()) {
			List<Guia> nuevo = new ArrayList<Guia>();
			return nuevo;
		}
		List<Guia> nuevo = ofy().load().type(Guia.class).filter("idSucursal",idSucursal).order("numero").list();
		return nuevo;
		
		
	}


	@Override
	public Guia getByNumero(String numero) {
		// TODO Auto-generated method stub
		System.out.println(" guias con el numero:"+numero);
		String num= numero.substring(0, 22);
		System.out.println("num:"+num);
		List<Guia> lista = ofy().load().type(Guia.class).filter("numero",num).list();
		System.out.println(" guias con el numero:"+numero);
		//String num= numero.substring(0, 22);
		System.out.println("lista:"+lista);
		if (lista.size() == 0) {
			System.out.println("no hay:");
			return null;
		}
		System.out.println("lista de guias :"+lista); 
		Guia nuevo = lista.get(0);
		return nuevo;
		
	}


	@Override
	public Guia getByEstSuc(String tipoGuia, Long idSucursal) {
		// TODO Auto-generated method stub
		System.out.println("esta en daoimpl envio  get by ASIGNADA, tipo guia::"+tipoGuia+" Suc:"+idSucursal);
		List<Guia> g= ofy().load().type(Guia.class).filter("estatus","ASIGNADA").filter("idSucursal", idSucursal).filter("tipoGuia",tipoGuia).order("numero").list();
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
	public List<Guia> getEstSucTipo(String sucursal,String tipoGuia) {
		// TODO Auto-generated method stub
		
		System.out.println("esta en daoimpl envio  get by estatus, sucursal y tipo de guia::"+tipoGuia+" Suc:"+sucursal);
		List<Guia> g= ofy().load().type(Guia.class).filter("estatus","ASIGNADA").filter("sucursal", sucursal).filter("tipoGuia",tipoGuia).order("numero").list();
		System.out.println(" cuantas guias :"+g.size());
		return g;
//		if (g.size() == 0) {
//			return null;
//		}
		//Guia nuevo = g.get(0);
		
		
		//return ofy().load().type(Guia.class).order("numero").list();

	}


	


}
