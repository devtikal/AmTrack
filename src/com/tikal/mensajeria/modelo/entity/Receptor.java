package com.tikal.mensajeria.modelo.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.tikal.cacao.sat.cfd.TUbicacion;

/**
 * @author Tikal
 *
 */
@Entity
public class Receptor {
	@Id
	private String rfc;
	
	private TUbicacion domicilio;
	
	private String nombre;
	
	public Receptor() {}
	
	public Receptor( String rfc,String nombre) {
		this.rfc = rfc;
		this.nombre = nombre;
	}
	
	/**
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}
	/**
	 * @param rfc the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
/**
	@return domicilio del receptor
	**/
	public TUbicacion getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(TUbicacion domicilio) {
		this.domicilio = domicilio;
	}
	
}
