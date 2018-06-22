package com.tikal.mensajeria.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tikal.mensajeria.dao.GuiaDao;
import com.tikal.mensajeria.dao.UsuarioDao;
import com.tikal.mensajeria.dao.VentaDao;
import com.tikal.mensajeria.modelo.entity.Contador;
import com.tikal.mensajeria.modelo.entity.Guia;
import com.tikal.mensajeria.modelo.entity.Venta;
import com.tikal.mensajeria.modelo.vo.EnvioVo;
import com.tikal.mensajeria.util.JsonConvertidor;
import com.tikal.util.AsignadorDeCharset;

@Controller
@RequestMapping("/venta")
public class VentaController {
	
	@Autowired
	@Qualifier("usuarioDao")
	UsuarioDao usuarioDao;
	

	@Autowired
	@Qualifier("ventaDao")
	VentaDao ventaDao;

	
	@RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo Venta prueba");

	    }
	
	@RequestMapping(value = { "/add/{username}" },  method = RequestMethod.GET ,consumes = "Application/Json")
	public void altas(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable String username,@RequestBody String json)throws IOException {
	
	//				if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		AsignadorDeCharset.asignar(request, response);
		Venta v = (Venta) JsonConvertidor.fromJson(json, Venta.class);
		Contador folio= new Contador();
		v.setFolio(folio.getFolio());
		v.setEstatus("ABIERTA");
		//System.out.println(" yisus manda:"+json);
//		int ini=Integer.parseInt(inicio);
//		int f=Integer.parseInt(fin);
			
		
			ventaDao.save(v); 
	
	
//			} else {
//				response.sendError(403);
//			}
		}
	
	
	
	
	
	@RequestMapping(value = { "/cancelar/{idVenta}" },  method = RequestMethod.GET)
	public void asignaM(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable Long idVenta )throws IOException {
	
	//				if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		AsignadorDeCharset.asignar(request, response);
		
			Venta v = ventaDao.consult(idVenta);
			v.setEstatus("CANCELADA");
			ventaDao.update(v);
			//System.out.println(" yisus manda:"+json);
		
		
	}
	
	 @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Venta> lista = ventaDao.findAll();
			if (lista == null) {
				lista = new ArrayList<Venta>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}


}
