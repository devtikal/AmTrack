package com.tikal.aero.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;

import com.googlecode.objectify.ObjectifyService;
import com.tikal.aero.modelo.entity.Serial;
import com.tikal.aero.modelo.login.Perfil;
import com.tikal.aero.modelo.login.SessionEntity;
import com.tikal.aero.modelo.login.Usuario;





@Component
public class StartupEntities implements ServletContextListener {
	
	
	
		public void contextInitialized(ServletContextEvent event) {
		
			
			ObjectifyService.register(Perfil.class);
			ObjectifyService.register(SessionEntity.class);
			
			ObjectifyService.register(Usuario.class);
		
			ObjectifyService.register(Serial.class);
	
		}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
    
    
}
