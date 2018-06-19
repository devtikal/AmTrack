package com.tikal.mensajeria.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.mensajeria.dao.DestinatarioDao;
import com.tikal.mensajeria.modelo.entity.Destinatario;
import com.tikal.mensajeria.modelo.entity.Empresa;

@Service("destinatarioDao")
public class DestinatarioDaoImpl implements DestinatarioDao {
	
	
	public  void save(Destinatario d) {    	
        ofy().save().entity(d).now();
    }

    
    public void delete(Destinatario d) {
    	 System.out.println("si esta en daoimpl eliminando"+d);
        ofy().delete().entity(d).now();
        System.out.println("eliminando...destinatario");
    }


	public void update(Destinatario d) {
	   System.out.print("destinatario:"+d.getId());
	   Destinatario old = this.consult(d.getId());
	System.out.print("old:"+old);
		if (old != null) {
			old.setNombre(d.getNombre());
			old.setaPaterno(d.getaPaterno());
			old.setaMaterno(d.getaMaterno());
			old.setCalle(d.getCalle());
			old.setNoExterior(d.getNoExterior());
			old.setNoInterior(d.getNoInterior());
			old.setColonia(d.getColonia());
			old.setLocalidad(d.getLocalidad());
			old.setMunicipio(d.getMunicipio());
			old.setCodigoPostal(d.getCodigoPostal());
			old.setEstado(d.getEstado());
			old.setTelefono(d.getTelefono());
			old.setNombreCompleto(d.getNombreCompleto());
			old.setReferencias(d.getReferencias());
				
		}

			ofy().save().entity(old);
   }

    

	public Destinatario consult(Long id) {
	   System.out.println("si esta en daoimpl consultando destinatario.."+id);
      return ofy().load().type(Destinatario.class).id(id).now();
		
	}


   
	public List<Empresa> findAll() {
		return ofy().load().type(Empresa.class).list();
	}





	@Override
	public void findAll(Destinatario d) {
		// TODO Auto-generated method stub
		
	}


}
