package dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import classesBasicas.ModeloCarro;

public class DAOModeloCarro extends DAOGenerico<ModeloCarro> implements IDAOModeloCarro {

	@Override
	public List<ModeloCarro> pesquisarModeloPorMarca(Integer codigo) {
		TypedQuery<ModeloCarro> query = entityManager.createQuery("from ModeloCarro m where m.marcaCarro.codigo = :codigo", ModeloCarro.class);
		query.setParameter("codigo", codigo);
		return query.getResultList();
	}

	@Override
	public ModeloCarro pesquisarModeloDescAno(ModeloCarro modelo) {
		try {
			TypedQuery<ModeloCarro> query = entityManager.createQuery("from ModeloCarro m where m.descricao = :descricao and m.ano =:ano",ModeloCarro.class);
			query.setParameter("descricao", modelo.getDescricao());
			query.setParameter("ano", modelo.getAno());
			return query.getSingleResult();
	} catch (NoResultException e) {
		//e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ModeloCarro> consultar(ModeloCarro modelo) throws Exception {
		String jpql = "from ModeloCarro m where m.descricao like :descricao";
		String descricao = "%";
		boolean temAno = false;
		boolean temMarca = false;
		boolean temSituacao = false;
		if (modelo.getDescricao() != null && modelo.getDescricao().length() > 0)
			descricao = "%" + modelo.getDescricao() + "%";
		if (modelo.getMarcaCarro()!=null && modelo.getMarcaCarro().getCodigo()!= null && modelo.getMarcaCarro().getCodigo()>0){
			jpql+= " and m.marcaCarro.codigo = :marca";
			temMarca = true;
		}
		if (modelo.getSituacao() != null){
			jpql+= " and m.situacao = :situacao";
			temSituacao = true;
		}
		if (modelo.getAno()>0){
			jpql+= " and m.ano = :ano";
			temAno = true;
		}
		TypedQuery<ModeloCarro> tqry = entityManager.createQuery(jpql, ModeloCarro.class);
		tqry.setParameter("descricao", descricao);
		if(temAno)
			tqry.setParameter("ano", modelo.getAno());
		if (temMarca)
			tqry.setParameter("marca", modelo.getMarcaCarro().getCodigo());
		if (temSituacao)
			tqry.setParameter("situacao", modelo.getSituacao());
		return tqry.getResultList();
	}

}
