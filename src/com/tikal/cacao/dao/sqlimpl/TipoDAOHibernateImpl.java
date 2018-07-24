package com.tikal.cacao.dao.sqlimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tikal.cacao.dao.sql.TipoDAO;
import com.tikal.cacao.model.orm.Tipo;

public class TipoDAOHibernateImpl implements TipoDAO {
	
	private SessionFactory sessionFac;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFac = sessionFactory;
    }

	@Override
	public void guardar(Tipo t) {
		Session sesion = this.sessionFac.openSession();
		Transaction tx = sesion.beginTransaction();
		sesion.persist(t);
		tx.commit();
		sesion.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tipo> consultarTodos() {
		Session session = this.sessionFac.openSession();
		List<Tipo> tipos = session.createQuery("from Tipo").list();
		session.close();
		return tipos;
	}

	@Override
	public Tipo consultarPorId(String id) {
		Session session = this.sessionFac.openSession();
		Tipo t = (Tipo)session.get(Tipo.class, id);
		session.close();
		return t;
	}

}
