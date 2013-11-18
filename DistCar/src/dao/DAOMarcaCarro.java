package dao;

import java.util.List;

import javax.persistence.TypedQuery;
import classesBasicas.MarcaCarro;

public class DAOMarcaCarro extends DAOGenerico<MarcaCarro> implements IDAOMarcaCarro {

	@Override
	public List<MarcaCarro> pesquisarMarcaPorFab(Integer codigo) {
		TypedQuery<MarcaCarro> query = entityManager.createQuery("from MarcaCarro m where m.fabricante.codigo = :codigo", MarcaCarro.class);
		query.setParameter("codigo", codigo);
		return query.getResultList();
	}

}
