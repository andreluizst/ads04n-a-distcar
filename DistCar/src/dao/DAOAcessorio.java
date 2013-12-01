package dao;


import java.util.List;

import javax.persistence.NoResultException;
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

	@Override
	public AcessorioCarro pesquisarAcessorioDescModelo(
			AcessorioCarro acessorioCarro) {
	try {
			TypedQuery<AcessorioCarro> query = entityManager.createQuery("from AcessorioCarro a where a.descricao = :descricao and a.modelo.codigo =:modelo",AcessorioCarro.class);
			query.setParameter("descricao", acessorioCarro.getDescricao());
			query.setParameter("modelo", acessorioCarro.getModelo().getCodigo());
			return query.getSingleResult();
	} catch (NoResultException e) {
		//e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<AcessorioCarro> consultar(AcessorioCarro acessorio)
			throws Exception {
		String jpql = "from AcessorioCarro a where a.descricao like :descricao";
		String descricao = "%";
		boolean temValor = false;
		boolean temModelo = false;
		boolean temSituacao = false;
		if (acessorio.getDescricao() != null && acessorio.getDescricao().length() > 0)
			descricao = "%" + acessorio.getDescricao() + "%";
		if (acessorio.getModelo()!=null && acessorio.getModelo().getCodigo()!= null && acessorio.getModelo().getCodigo()>0){
			jpql+= " and a.modelo.codigo = :modelo";
			temModelo = true;
		}
		if (acessorio.getSituacao() != null){
			jpql+= " and a.situacao = :situacao";
			temSituacao = true;
		}
		if (acessorio.getValor()>0){
			jpql+= " and a.valor = :valor";
			temValor = true;
		}
		TypedQuery<AcessorioCarro> tqry = entityManager.createQuery(jpql, AcessorioCarro.class);
		tqry.setParameter("descricao", descricao);
		if(temValor)
			tqry.setParameter("valor", acessorio.getValor());
		if (temModelo)
			tqry.setParameter("modelo", acessorio.getModelo().getCodigo());
		if (temSituacao)
			tqry.setParameter("situacao", acessorio.getSituacao());
		return tqry.getResultList();
	}

	@Override
	public List<AcessorioCarro> pesquisarPorModelo(Integer codigo) {
		TypedQuery<AcessorioCarro> query = entityManager.createQuery("from AessorioCarro a where a.modelo.codigo = :codigo", AcessorioCarro.class);
		query.setParameter("codigo", codigo);
		return query.getResultList();
	}

	@Override
	public List<AcessorioCarro> listarAcessorios(ModeloCarro modelo) {
		TypedQuery<AcessorioCarro> query = entityManager.createQuery("from AessorioCarro a join fetch a.type where a.modelo.codigo = :codigo", AcessorioCarro.class);
		query.setParameter("codigo", modelo.getCodigo());
		return query.getResultList();
	}

}
