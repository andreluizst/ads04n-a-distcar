package dao;


import java.util.List;

import javax.persistence.TypedQuery;

import classesBasicas.AcessorioCarro;
import classesBasicas.ModeloCarro;

public class DAOAcessorio extends DAOGenerico<AcessorioCarro> implements IDAOAcessorio {

	@Override
	public List<AcessorioCarro> listarAcessoriosPorModelo(ModeloCarro modelo) {
		TypedQuery<AcessorioCarro> query = entityManager.createQuery("from AcessorioCarro a where a.modelo.codigo = :modeloCarro",AcessorioCarro.class);
		query.setParameter("modeloCarro", modelo.getCodigo());
		return query.getResultList();
	}

}
