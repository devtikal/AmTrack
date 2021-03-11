package com.tikal.aero;

import com.googlecode.objectify.ObjectifyService;
import com.tikal.aero.modelo.Perfil;
import com.tikal.aero.modelo.Usuario;
import com.tikal.aero.modelo.entity.Aeronave;
import com.tikal.aero.modelo.entity.Tarea;
import com.tikal.aero.modelo.entity.Componente;
import com.tikal.aero.modelo.entity.HistorialComponente;


public class Register {
	public Register(){
		
		
		ObjectifyService.register(Usuario.class);
		ObjectifyService.register(Perfil.class);
		ObjectifyService.register(Aeronave.class);
		ObjectifyService.register(Componente.class);
		ObjectifyService.register(Tarea.class);
		ObjectifyService.register(HistorialComponente.class);
	}
}
