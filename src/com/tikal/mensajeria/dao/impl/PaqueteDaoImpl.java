package com.tikal.mensajeria.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.mensajeria.dao.PaqueteDao;
import com.tikal.mensajeria.modelo.entity.Paquete;


@Service("paqueteDao")
public class PaqueteDaoImpl implements PaqueteDao{
	
	public  void save(Paquete p) {    	
		p.setPesoVol((p.getAlto()*p.getAncho()*p.getLargo())/5000);
        ofy().save().entity(p).now();
    }

    
    public void delete(Paquete p) {
    	 System.out.println("si esta en daoimpl eliminando aquete"+p);
        ofy().delete().entity(p).now();
        System.out.println("eliminando...paquete");
    }


	public void update(Paquete p) {
	   System.out.print("paquete:"+p.getId());
	   Paquete old = this.consult(p.getId());
	System.out.print("old:"+old);
		if (old != null) {
			
			old.setDescripcion(p.getDescripcion());
			old.setTipoPaquete(p.getTipoPaquete());
			old.setLargo(p.getLargo());
			old.setAncho(p.getAncho());
			old.setAlto(p.getAlto());	
			old.setPeso(p.getPeso());
			old.setPesoVol((old.getAlto()*old.getAncho()*old.getLargo())/5000);
		
			
		}

			ofy().save().entity(old);
   }

    

	public Paquete  consult(Long id) {
	   System.out.println("si esta en daoimpl consultando el paquete.."+id);
      return ofy().load().type(Paquete.class).id(id).now();
		
	}


   
	public List<Paquete> findAll() {
		return ofy().load().type(Paquete.class).list();
	}


	@Override
	public void findAll(Paquete p) {
		// TODO Auto-generated method stub
		
	}


}
