package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import classesBasicas.Centro;

public class DAOCentro extends DAOGenerico<Centro> implements IDAOCentro{

	@Override
	public List<Centro> pesquisarCentro(Centro centro) {
		TypedQuery<Centro> tqry = entityManager.createQuery("from Centro c where c.alias like :alias", Centro.class);
		tqry.setParameter("alias", "%" + centro.getAlias() + "%");
		return tqry.getResultList();
	}

}
