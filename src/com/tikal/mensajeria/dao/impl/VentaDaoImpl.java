package com.tikal.mensajeria.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.googlecode.objectify.ObjectifyService;
import com.tikal.mensajeria.dao.VentaDao;
import com.tikal.mensajeria.modelo.entity.ContadorServicio;
import com.tikal.mensajeria.modelo.entity.Envio;
import com.tikal.mensajeria.modelo.entity.Venta;
import com.tikal.mensajeria.modelo.vo.ReporteVo;

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
			old.setTotal(v.getTotal());
			old.setEnvios(v.getEnvios());
			old.setEstatus(v.getEstatus());
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


	@Override

		public List<Venta> getVentas(Date inicio, Date fin,Long idSucursal) {  //abiertAS
			
			return ofy().load().type(Venta.class).filter("estatus","ABIERTA").filter("idSucursal", idSucursal).filter("fecha >=",inicio).filter("fecha <=", fin).list();
			//return ofy().load().type(Venta.class).filter("estatus","ABIERTA").filter("idSucursal", idSucursal).list();
		}


	@Override
	public List<Venta> findAllAbierta() {
		// TODO Auto-generated method stub
		return ofy().load().type(Venta.class).filter("estatus", "ABIERTA").list();
	}

	@Override
	public List<Venta> findAllAbiertaBySuc(Long idSucursal) {
		// TODO Auto-generated method stub
		return ofy().load().type(Venta.class).filter("estatus", "ABIERTA").filter("idSucursal", idSucursal).list();
	}
	@Override
	public void crearContador(Long folio){
		ContadorServicio f= new ContadorServicio();
		f.setFolio(folio);
		ObjectifyService.ofy().save().entity(f);
	}

	
	
	
}
