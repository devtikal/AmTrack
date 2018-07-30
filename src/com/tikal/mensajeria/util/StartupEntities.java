package com.tikal.mensajeria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;

import com.googlecode.objectify.ObjectifyService;
import com.tikal.mensajeria.modelo.entity.Contador;
import com.tikal.mensajeria.modelo.entity.ContadorServicio;
import com.tikal.mensajeria.modelo.entity.Emisor;
import com.tikal.mensajeria.modelo.entity.Envio;
import com.tikal.mensajeria.modelo.entity.Factura;
import com.tikal.mensajeria.modelo.entity.FacturaVTT;
import com.tikal.mensajeria.modelo.entity.Guia;
import com.tikal.mensajeria.modelo.entity.Paquete;
import com.tikal.mensajeria.modelo.entity.Persona;
import com.tikal.mensajeria.modelo.entity.Receptor;
import com.tikal.mensajeria.modelo.entity.Serial;
import com.tikal.mensajeria.modelo.entity.Venta;
import com.tikal.mensajeria.modelo.login.Perfil;
import com.tikal.mensajeria.modelo.login.SessionEntity;
import com.tikal.mensajeria.modelo.login.Sucursal;
import com.tikal.mensajeria.modelo.login.Usuario;
import com.tikal.mensajeria.modelo.vo.ClienteFac;
import com.tikal.mensajeria.modelo.vo.VentaFac;




@Component
public class StartupEntities implements ServletContextListener {
	
	
	
		public void contextInitialized(ServletContextEvent event) {
		
			
			ObjectifyService.register(Perfil.class);
			ObjectifyService.register(SessionEntity.class);
			ObjectifyService.register(Sucursal.class);
			ObjectifyService.register(Usuario.class);
			ObjectifyService.register(Paquete.class);
			ObjectifyService.register(Envio.class);
			ObjectifyService.register(Persona.class);
			ObjectifyService.register(Guia.class);
			ObjectifyService.register(Contador.class);
			ObjectifyService.register(ContadorServicio.class);
			ObjectifyService.register(Venta.class);
			ObjectifyService.register(Factura.class);
			ObjectifyService.register(FacturaVTT.class);
			ObjectifyService.register(Serial.class);
			ObjectifyService.register(VentaFac.class);
			ObjectifyService.register(ClienteFac.class);
			ObjectifyService.register(Emisor.class);
			ObjectifyService.register(Receptor.class);
		}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
    
    
}
