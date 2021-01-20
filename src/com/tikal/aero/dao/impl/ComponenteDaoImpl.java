package com.tikal.aero.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


import com.tikal.aero.dao.ComponenteDao;

import com.tikal.aero.modelo.entity.Componente;

@Service("componenteDao")
public class ComponenteDaoImpl implements ComponenteDao{
	
	public void save(Componente c) {   
		System.out.println("si lega aqui");
        ofy().save().entity(c).now();
    }
	public List<Componente> getAll() {                   

		return ofy().load().type(Componente.class).list();
	}

	public List<Componente> getAllF() {                   
		return ofy().load().type(Componente.class).filter("d_cantidad >", 0).list();
		
	}
	
	public List<Componente> getAll0() {                   
		return ofy().load().type(Componente.class).filter("d_cantidad", 0).list();
		
	}

	public void findAll() {
	// TODO Auto-generated method stub
	
	}

	@Override
	public void delete(Componente c) {
		// TODO Auto-generated method stub@Override
		ofy().delete().entity(c).now();
			//object.setActivo(false);
			//update(c);
		
		
	}


	@Override
	public void update(Componente c) {
		// TODO Auto-generated method stub
		
			//ofy().save().entity(c).now();
			System.out.println("guarda los nuevos datos del componente" );
			
			Componente old = this.consult(c.getId());
			if (old != null) {
				old.setAnaquel(c.getAnaquel());
				old.setCertificado8130(c.getCertificado8130());
				old.setD_cantidad(c.getD_cantidad());
				old.setclaveInterna(c.getclaveInterna());
				old.setnombre(c.getnombre());
				old.setD_marca(c.getD_marca());
				old.setD_modelo(c.getD_modelo());
				old.setD_observaciones(c.getD_observaciones());
				old.setclaveManual(c.getclaveManual());
				old.setD_pendientes(c.getD_pendientes());
				old.setFechaApertura(c.getFechaApertura());
				old.setIdCategoria(c.getIdCategoria());
				old.setIdCondicion(c.getIdCondicion());
				old.setIdUnidad(c.getIdUnidad());
				old.setMaximo(c.getMaximo());
				old.setMinimo(c.getMinimo());
				old.setNoSerie(c.getNoSerie());
				old.setRepisa(c.getRepisa());
				
			}	
				ofy().save().entity(old);
		}

		
	
	@Override
	public Componente consult(Long id) {
		System.out.println("consultando el componente " );
       return ofy().load().type(Componente.class).id(id).now();
		
	}
	@Override
	public void updateExistencias(Long id, Integer d_cantidad, Integer d_pendientes) {
		// TODO Auto-generated method stub
		Componente old = this.consult(id);
		if (old != null) {
//			//old.setDireccion(e.getDireccion());
			old.setD_cantidad(d_cantidad);
			//old.setclaveInterna(c.getclaveInterna());
		//	old.setclaveManual(c.getclaveManual());;
			old.setD_pendientes(d_pendientes);
		//	old.setD_vale(c.getD_vale());
		//	old.setD_requisicion(c.getD_requisicion());
			
			ofy().save().entity(old);
		
		}
		//return null;
	}

	@Override
	public List<Componente> getByDiscrepancia(Long idDiscrepancia) {
		// TODO Auto-generated method stub
		//List<Componente> comps = ofy().load().type(Componente.class).filter("folioOrden", folioOrden).list();
		return null;
	}
	@Override
	public List<Componente> getByCategoria(Long idCategoria) {
		// TODO Auto-generated method stub
		System.out.println("consultando componentes por categoria " );
		return ofy().load().type(Componente.class).filter("idCategoria", idCategoria).list();
		
	}
	@Override
	public List<Componente> getMaxMin() {
		// TODO Auto-generated method stub
		System.out.println("obtenendo minimos......" );
		List<Componente> min = new ArrayList<Componente>();
		List<Componente> todos = ofy().load().type(Componente.class).list();
		for (Componente c :todos){
			if (c.getD_cantidad()<=c.getMinimo()){
				System.out.println("El componente ya esta en alerta de mínimo" );
				min.add(c);				
			}
			System.out.println("No hay componentes en alerta"+min.size() );
			
		}
		
		return min;
	}
}
 