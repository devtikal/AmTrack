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
	   System.out.println("si esta en daoimpl consultando la venta.."+id);
      return ofy().load().type(Venta.class).id(id).now();
		
	}


	@Override
	public int findAllpags() {
		return ((ofy().load().type(Venta.class).count()-1)/10)+1;
	}
	
	public List<Venta> findAll() {
		return ofy().load().type(Venta.class).list();
		//return ofy().load().type(Venta.class).filter("estatus !=","CANCELADA").list();
	}

	@Override
	public List<Venta> findAllPage(int page) {
		return ofy().load().type(Venta.class).order("- fecha").offset((page-1)*10).limit(10).list();
	}


	@Override

		public List<Venta> getVentas(Date inicio, Date fin,Long idSucursal) {  //abiertAS
			
		//List<Venta> lista =ofy().load().type(Venta.class).filter("estatus ","ABIERTA").filter("idSucursal", idSucursal).filter("fecha >=",inicio).filter("fecha <=", fin).list();
		//lista.addAll(ofy().load().type(Venta.class).filter("estatus ","FACTURADA").filter("idSucursal", idSucursal).filter("fecha >=",inicio).filter("fecha <=", fin).list());
		//lista.addAll(ofy().load().type(Venta.class).filter("estatus ","FACTURA-CANCELADA").filter("idSucursal", idSucursal).filter("fecha >=",inicio).filter("fecha <=", fin).list());
			return ofy().load().type(Venta.class).filter("idSucursal", idSucursal).filter("fecha >=",inicio).filter("fecha <=", fin).order("- fecha").list();
			//return ofy().load().type(Venta.class).filter("estatus","ABIERTA").filter("idSucursal", idSucursal).list();
	//	return lista;
		}


	@Override
	public List<Venta> findAllAbierta() {
		// TODO Auto-generated method stub
		//return ofy().load().type(Venta.class).filter("estatus", "ABIERTA").list();
		return ofy().load().type(Venta.class).order("- fecha").list();
	}
	
	@Override
	public List<Venta> findAllAbiertaHoy() {
		// TODO Auto-generated method stub
		//return ofy().load().type(Venta.class).filter("estatus", "ABIERTA").list();
		Date hoy = new Date();
		hoy.setHours(0);hoy.setMinutes(0);hoy.setSeconds(0);
		Date fin = new Date();
		fin.setHours(23);fin.setMinutes(59);fin.setSeconds(59);
		System.out.println("hoy"+hoy);
		return ofy().load().type(Venta.class).filter("fecha >=",hoy).filter("fecha <=", fin).order("- fecha").list();
	}
	
	@Override
	public List<Venta> findAllAbiertaIF(Date inicio, Date fin) {
		// TODO Auto-generated method stub
//		List<Venta> lista =ofy().load().type(Venta.class).filter("estatus", "ABIERTA").filter("fecha >=",inicio).filter("fecha <=", fin).list();
//		lista.addAll(ofy().load().type(Venta.class).filter("estatus", "FACTURADA").filter("fecha >=",inicio).filter("fecha <=", fin).list());
//		lista.addAll(ofy().load().type(Venta.class).filter("estatus", "FACTURA-CANCELADA").filter("fecha >=",inicio).filter("fecha <=", fin).list());
		return ofy().load().type(Venta.class).filter("fecha >=",inicio).filter("fecha <=", fin).order("- fecha").list();
		//return lista;
	}

	@Override
	public List<Venta> findAllAbiertaBySuc(Long idSucursal) {
		// TODO Auto-generated method stub
//		List<Venta> lista =ofy().load().type(Venta.class).filter("estatus", "ABIERTA").filter("idSucursal", idSucursal).list();
//		lista.addAll(ofy().load().type(Venta.class).filter("estatus", "FACTURADA").filter("idSucursal", idSucursal).list());
//		lista.addAll(ofy().load().type(Venta.class).filter("estatus", "FACTURA-CANCELADA").filter("idSucursal", idSucursal).list());
		return ofy().load().type(Venta.class).filter("idSucursal", idSucursal).order("- fecha").list();
		//return lista;
	}
	
	@Override
	public int findAllpagsSuc(Long idSucursal) {
		return ((ofy().load().type(Venta.class).filter("idSucursal", idSucursal).order("- fecha").count()-1)/10)+1;
	}
	
	@Override
	public List<Venta> findAllAbiertaBySucP(Long idSucursal, int page) {
	
		return ofy().load().type(Venta.class).filter("idSucursal", idSucursal).order("- fecha").offset((page-1)*10).limit(10).list();
		
	}
	
	@Override
	public void crearContador(Long folio){
		ContadorServicio f= new ContadorServicio();
		f.setFolio(folio);
		f.setGuia(Long.parseLong("500000"));
		ObjectifyService.ofy().save().entity(f);
	}

	
	
	
}
