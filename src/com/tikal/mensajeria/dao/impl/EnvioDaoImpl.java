package com.tikal.mensajeria.dao.impl;



import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.mensajeria.dao.EnvioDao;
import com.tikal.mensajeria.modelo.entity.Envio;


@Service("envioDao")
public class EnvioDaoImpl implements EnvioDao{
	
	public  void save(Envio e) {    	
        ofy().save().entity(e).now();
    }

    
    public void delete(Envio e) {
    	 System.out.println("si esta en daoimpl eliminando"+e);
        ofy().delete().entity(e).now();
        System.out.println("eliminando...envio");
    }


	public void update(Envio e) {
	   System.out.print("Envio:"+e.getId());
	   Envio old = this.consult(e.getId());
	System.out.print("old:"+old);
		if (old != null) {
			
			old.setCliente(e.getCliente());
			old.setDestinatario(e.getDestinatario());
			old.setEmpresa(e.getEmpresa());
			old.setGuia(e.getGuia());
			old.setPaquete(e.getPaquete());
			old.setPrecio(e.getPrecio());
			old.setCostoSeguro(e.getCostoSeguro());
			old.setRastreo(e.getRastreo());
			old.setTipoEnvio(e.getTipoEnvio());
			old.setTipoServicio(e.getTipoServicio());
			old.setMateriales(e.getMateriales());
				
		}

			ofy().save().entity(old);
   }

    

	public Envio consult(Long id) {
	   System.out.println("si esta en daoimpl consultando la Envio.."+id);
      return ofy().load().type(Envio.class).id(id).now();
		
	}


   
	public List<Envio> findAll() {
		return ofy().load().type(Envio.class).list();
	}


	

	
	
	

}
