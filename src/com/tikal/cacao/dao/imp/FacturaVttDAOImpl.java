package com.tikal.cacao.dao.imp;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.cacao.dao.FacturaVttDAO;
import com.tikal.cacao.factura.Estatus;
import com.tikal.mensajeria.modelo.entity.FacturaVTT;

@Service("facturaVTTDAO")
public class FacturaVttDAOImpl implements FacturaVttDAO {

	@Override
	public void guardar(FacturaVTT f) {
		ofy().save().entity(f).now();
	}

	@Override
	public FacturaVTT consultar(String uuid) {
		return ofy().load().type(FacturaVTT.class).id(uuid).now();
	}

	@Override
	public List<FacturaVTT> consutarTodas(String rfcEmisor) {
		List<FacturaVTT> facturas = ofy().load().type(FacturaVTT.class).filter("rfcEmisor", rfcEmisor).order("-fechaCertificacion").list();
		if (facturas == null)
			return new ArrayList<>();
		else
			return facturas;
	}

	@Override
	public boolean eliminar(FacturaVTT f) {
		if (f.getEstatus().equals(Estatus.GENERADO)) {
			ofy().delete().entity(f).now();
			return true;
		}
		return false;
	}

}
