package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import classesBasicas.Gestor;

public class DAOGestor extends DAOGenerico<Gestor> implements IDAOGestor {
	
	public DAOGestor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOGestor(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Gestor> pesquisarGestor(Gestor g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tornarFuncionarioEmGestor(Gestor gestor) throws Exception {
		/*Query qry = entityManager.createNamedQuery("Gestor.tornarFuncionarioEmGestor");
		qry.setParameter("codigo", gestor.getCodigo());
		qry.setParameter("codigoTipoGerencia", gestor.getTipoGerencia());
		qry.executeUpdate();*/
	}

	

}
