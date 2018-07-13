package com.tikal.mensajeria.modelo.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class ContadorServicio {

	@Id private Long id;
	private Long folio;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFolio() {
		
		if (folio==0){
			folio=Long.parseLong("1000");
		return folio;
		}else
			return folio;
		
		
	}
	public void incrementar() {
		this.folio++;
	}
	
	public void setFolio(Long folio){
		this.folio= folio;
	}
	
	
}
