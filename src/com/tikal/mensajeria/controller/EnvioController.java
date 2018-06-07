package com.tikal.mensajeria.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tikal.mensajeria.dao.DestinatarioDao;
import com.tikal.mensajeria.dao.EmpresaDao;
import com.tikal.mensajeria.dao.EnvioDao;
import com.tikal.mensajeria.dao.PaqueteDao;
import com.tikal.mensajeria.dao.UsuarioDao;
import com.tikal.mensajeria.modelo.entity.Destinatario;
import com.tikal.mensajeria.modelo.entity.Empresa;
import com.tikal.mensajeria.modelo.entity.Envio;
import com.tikal.mensajeria.modelo.entity.Paquete;
import com.tikal.mensajeria.modelo.login.Usuario;
import com.tikal.mensajeria.modelo.vo.EnvioVo;
import com.tikal.mensajeria.util.AsignadorDeCharset;
import com.tikal.util.JsonConvertidor;

@Controller
@RequestMapping("/envio")
public class EnvioController {
	
	//@Autowired
	@Qualifier("usuariDao")
	UsuarioDao usuarioDao;
	
	@Autowired
	@Qualifier("empresaDao")
	EmpresaDao empresaDao;
	
	@Autowired
	@Qualifier("paqueteDao")
	PaqueteDao paqueteDao;
	
	@Autowired
	@Qualifier("destinatarioDao")
	DestinatarioDao destinatarioDao;
	
	@Autowired
	@Qualifier("envioDao")
	EnvioDao envioDao;
	
	
	@RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo Envio prueba");

	    }
	
	@RequestMapping(value = { "/add_" }, method = RequestMethod.GET)
	public void crearPerfil(HttpServletRequest request, HttpServletResponse response)throws IOException {
//		if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
		//	AsignadorDeCharset.asignar(request, response);
		//	System.out.println(" edgar manda:"+json);
			Envio envio = new Envio();
			Destinatario d= new Destinatario();
			Paquete p = new Paquete();
			Empresa e = new Empresa();
			Usuario u = new Usuario();
			envio.setCantidad(1);
			envio.setCliente("Juan Perez Acuña");
			
			d.setNombre("Angelica");
			d.setaMaterno("Pacheco");
			d.setaMaterno("Pantoja");
			d.setCalle("Ejercito del trabajo");
			d.setNoExterior("130");
			d.setNoInterior("0");
			d.setColonia("guadalupe san buena");
			d.setLocalidad("Toluca");
			d.setMunicipio("Toluca");
			d.setEstado("Mexico");
			d.setCodigoPostal(50110);
			d.setTelefono("7222222222");
			
			destinatarioDao.save(d);
			
			
			envio.setDestinatario(d);
			
			p.setAlto(10.00);p.setAncho(10.00); p.setLargo(10.00);
			p.setDescripcion("paquete de joyeria");
			p.setPeso(312.00);
			p.setTipoPaquete("paquete");
			paqueteDao.save(p);
			
			envio.setPaquete(p);
			
			e.setClave("fed");
			e.setDescripcion("FEDEX_");
			empresaDao.save(e);
			
		//	envio.setEmpresa("otra");
			envio.setFecha("05/06/2018 18:03:45");
			envio.setFolio("0001");
			envio.setGuia(123456789);
			envio.setPrecio(312.50);
			envio.setRastreo(8888888);
			envio.setTipoEnvio("Express");
			envio.setTotal(312.50);
			

			//u= usuarioDao.consult("")
			
			//envio.setUsuario(usuario);
			envioDao.save(envio);
			
	//}
//			response.sendError(403);
//		}
	}
	

	@RequestMapping(value = { "/add" },  method = RequestMethod.POST, consumes = "Application/Json")
	public void crearEnvio(HttpServletRequest request, HttpServletResponse response,@RequestBody String json)throws IOException {
//		if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 45, sessionDao,userName)){
			AsignadorDeCharset.asignar(request, response);
			System.out.println(" Yisus manda:"+json);
			EnvioVo evo = (EnvioVo) JsonConvertidor.fromJson(json, EnvioVo.class);
			//System.out.println("lista de permisos:"+perfil.getPermisos());
			Paquete p = new Paquete();
			Envio envio = new Envio();
			Destinatario des= new Destinatario();
			Empresa e=new Empresa();
		//	e=empresaDao.getByNombre(evo.getEmpresa());
			
			p.setAlto(evo.getAlto());
			p.setAncho(evo.getAncho());
			p.setDescripcion(evo.getDescripcion());
			p.setLargo(evo.getLargo());
			p.setPeso(evo.getPeso());
			p.setTipoPaquete(evo.getTipoPaquete());
			 paqueteDao.save(p);
			
			des.setaMaterno(evo.getaMaterno());
			des.setaPaterno(evo.getaPaterno());
			des.setCalle(evo.getCalle());
			des.setCodigoPostal(evo.getCodigoPostal());
			des.setColonia(evo.getColonia());
			des.setEstado(evo.getEstado());
			des.setLocalidad(evo.getLocalidad());
			des.setMunicipio(evo.getMunicipio());
			des.setNoExterior(evo.getNoExterior());
			des.setNoInterior(evo.getNoInterior());
			des.setNombre(evo.getNombre());
			des.setTelefono(evo.getTelefono());
			destinatarioDao.save(des);
			
			//envio.setEmpresa(e);
			
			envio.setCantidad(evo.getCantidad());
			envio.setCliente(evo.getCliente());
			envio.setDestinatario(des);
			
			envio.setFecha(evo.getFecha());
			envio.setFolio(evo.getFolio());
			envio.setGuia(evo.getGuia());
			envio.setPaquete(p);
			envio.setPrecio(evo.getPrecio());
			envio.setRastreo(evo.getRastreo());
			envio.setTipoEnvio(evo.getTipoEnvio());
			envio.setTotal(evo.getTotal());
			//envio.setUsuario(usuario); falta
			
			envioDao.save(envio);
			
			
			
	}
}
