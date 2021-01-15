package com.tikal.aero;

import com.googlecode.objectify.ObjectifyService;
import com.tikal.aero.modelo.Perfil;
import com.tikal.aero.modelo.Usuario;


public class Register {
	public Register(){
		
		
		ObjectifyService.register(Usuario.class);
		ObjectifyService.register(Perfil.class);
		

	}
}
