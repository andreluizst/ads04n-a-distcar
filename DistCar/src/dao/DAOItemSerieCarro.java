package dao;



import java.util.List;

import javax.persistence.TypedQuery;

import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;

public class DAOItemSerieCarro extends DAOGenerico<ItemSerieCarro> implements IDAOItemSerieCarro{

	@Override
	public List<ItemSerieCarro> pesquisarPorModelo(Integer codigo) {
		TypedQuery<ItemSerieCarro> query = entityManager.createQuery("from ItemSerieCarro i where i.modeloCarro.codigo = :codigo", ItemSerieCarro.class);
		query.setParameter("codigo", codigo);
		return query.getResultList();
		
	}

	@Override
	public List<ItemSerieCarro> listarItensPorModelo(ModeloCarro modelo) {
		TypedQuery<ItemSerieCarro> query = entityManager.createQuery("from ItemSerieCarro i where i.modelo.codigo = :modeloCarro",ItemSerieCarro.class);
		query.setParameter("modeloCarro", modelo.getCodigo());
		return query.getResultList();
	}
	
}
