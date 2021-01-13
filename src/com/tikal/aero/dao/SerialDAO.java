package com.tikal.aero.dao;

import java.util.List;

import com.tikal.aero.modelo.entity.Serial;

public interface SerialDAO {
	public void guardar(Serial s);
	public Serial consultar(String rfc, String serie);
	public List<Serial> consultar(String rfc);
	public void eliminar(String id);
}
