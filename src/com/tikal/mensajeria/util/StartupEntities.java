package com.tikal.mensajeria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;

import com.googlecode.objectify.ObjectifyService;
import com.tikal.mensajeria.modelo.entity.Contador;
import com.tikal.mensajeria.modelo.entity.Envio;
import com.tikal.mensajeria.modelo.entity.Guia;
import com.tikal.mensajeria.modelo.entity.Paquete;
import com.tikal.mensajeria.modelo.entity.Persona;
import com.tikal.mensajeria.modelo.entity.Venta;
import com.tikal.mensajeria.modelo.login.Perfil;
import com.tikal.mensajeria.modelo.login.SessionEntity;
import com.tikal.mensajeria.modelo.login.Sucursal;
import com.tikal.mensajeria.modelo.login.Usuario;




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
			ObjectifyService.register(Venta.class);
		
		}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
    
    
}
