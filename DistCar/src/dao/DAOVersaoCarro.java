package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoCarro;

public class DAOVersaoCarro extends DAOGenerico<VersaoCarro> implements IDAOVersaoCarro{

	@Override
	public List<ItemSerieCarro> listarItensPorModelo(ModeloCarro modelo) {
		TypedQuery<ItemSerieCarro> query = entityManager.createQuery("from ItemSerieCarro i where i.modelo.codigo = :modeloCarro",ItemSerieCarro.class);
		query.setParameter("modeloCarro", modelo.getCodigo());
		return query.getResultList();
	}

	@Override
	public List<AcessorioCarro> listarAcessoriosPorModelo(ModeloCarro modelo) {
		TypedQuery<AcessorioCarro> query = entityManager.createQuery("from AcessorioCarro a where a.modelo.codigo = :modeloCarro",AcessorioCarro.class);
		query.setParameter("modeloCarro", modelo.getCodigo());
		return query.getResultList();
	}



}
