package com.tikal.mensajeria.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.mensajeria.dao.VentaDao;
import com.tikal.mensajeria.modelo.entity.Envio;
import com.tikal.mensajeria.modelo.entity.Venta;

@Service("ventaDao")
public class VentaDaoImpl implements VentaDao{

	
	public  void save(Venta v) {    	
        ofy().save().entity(v).now();
    }

    
    public void delete(Venta v) {
    	 System.out.println("si esta en daoimpl eliminando"+v);
        ofy().delete().entity(v).now();
        System.out.println("eliminando...envio");
    }


	public void update(Venta v) {
	   System.out.print("Venta:"+v.getId());
	   Venta old = this.consult(v.getId());
	   System.out.print("old:"+old);
		if (old != null) {
			
			old.setFecha(v.getFecha());
			old.setFolio(v.getFolio());
			old.setEnvios(v.getEnvios());
			old.setCantidad(v.getCantidad());
			
			List<Envio> es= new ArrayList<Envio>();
			if (es.size()<=0){
				old.setTotal(Double.valueOf("0.00"));
			}else{
				for (Envio e:es){
					v.setTotal(v.getTotal()+e.getPrecio());
				
				}
			}
			
			old.setTotal(v.getTotal());
		}

			ofy().save().entity(old);
   }

    

	public Venta consult(Long id) {
	   System.out.println("si esta en daoimpl consultando la vanta.."+id);
      return ofy().load().type(Venta.class).id(id).now();
		
	}


   
	public List<Venta> findAll() {
		return ofy().load().type(Venta.class).list();
	}
	
	
}
