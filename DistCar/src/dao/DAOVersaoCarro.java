package dao;

import java.util.List;
import javax.persistence.TypedQuery;

import classesBasicas.VersaoCarro;

public class DAOVersaoCarro extends DAOGenerico<VersaoCarro> implements IDAOVersaoCarro{

	@Override
	public List<VersaoCarro> pesquisarVersaoPorModelo(Integer codigo) {
		TypedQuery<VersaoCarro> query = entityManager.createQuery("from VersaoCarro v where v.modeloCarro.codigo = :codigo", VersaoCarro.class);
		query.setParameter("codigo", codigo);
		return query.getResultList();
	}

}
