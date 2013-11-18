package dao;

import java.util.List;

import javax.persistence.TypedQuery;
import classesBasicas.ModeloCarro;

public class DAOModeloCarro extends DAOGenerico<ModeloCarro> implements IDAOModeloCarro {

	@Override
	public List<ModeloCarro> pesquisarModeloPorMarca(Integer codigo) {
		TypedQuery<ModeloCarro> query = entityManager.createQuery("from ModeloCarro m where m.marcaCarro.codigo = :codigo", ModeloCarro.class);
		query.setParameter("codigo", codigo);
		return query.getResultList();
	}


}
