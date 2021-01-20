package com.tikal.aero.dao.impl;



import com.tikal.aero.dao.AeronaveDao;
import com.tikal.aero.modelo.entity.Aeronave;

import org.springframework.stereotype.Service;
import java.util.List;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * @author 
 */

@Service("aeronaveDao")

public class AeronaveDaoImpl implements AeronaveDao {

    public  void save(Aeronave a) {    	
        ofy().save().entity(a).now();
    }

    
    public void delete(Aeronave a) {
    	 System.out.println("si esta en daoimpl eliminando"+a);
        ofy().delete().entity(a).now();
        System.out.println("eliminando...");
    }

   @Override
	public void update(Aeronave nave) {
	   System.out.print("nave:"+nave.getId());
	Aeronave old = this.consult(nave.getId());
	System.out.print("old:"+old);
		if (old != null) {
			old.setMarca(nave.getMarca());
			old.setMatricula(nave.getMatricula());
			old.setModelo(nave.getModelo());
			old.setNacionalidad(nave.getNacionalidad());
			old.setNumeroSerie(nave.getNumeroSerie());
			old.setTiempovuelo(nave.getTiempovuelo());
			old.setAterrizaje(nave.getAterrizaje());
			old.setPlaneador(nave.getPlaneador());
			old.setMotor1(nave.getMotor1());
			old.setMotor2(nave.getMotor2());
			old.setMarcas(nave.getMarcas());
			
		}

			ofy().save().entity(old);
   }

    
   @Override
	public Aeronave consult(Long id) {
	   System.out.println("si esta en daoimpl consultando la nave.."+id);
      return ofy().load().type(Aeronave.class).id(id).now();
		
	}

	@Override
	public void findAll(Aeronave a) {
		// TODO Auto-generated method stub
		
	}
   
	public List<Aeronave> getAll() {
		return ofy().load().type(Aeronave.class).list();
	}


	@Override
	public List<Aeronave> getAllN() {
		// TODO Auto-generated method stub
		return null;
	}
}