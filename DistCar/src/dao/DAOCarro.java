package dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoCarro;


public class DAOCarro extends DAOGenerico<Carro> implements IDAOCarro {

	@Override
	public List<Carro> pesquisarCarroPorChassi(String chassi) {
		TypedQuery<Carro> query = entityManager.createQuery("from Carro c where c.chassi like :chassi", Carro.class);
		query.setParameter("chassi", "%" + chassi + "%");
		return query.getResultList();
	}

	@Override
	public List<Carro> pesquisarCarroPorModelo(ModeloCarro modeloCarro) {
		TypedQuery<Carro> query = entityManager.createQuery("from Carro c where c.versaoModeloCarro.modeloCarro.descricaoModeloCarro = :modeloCarro", Carro.class);
		query.setParameter("modeloCarro", modeloCarro.getDescricao());
		return query.getResultList();
	}

	@Override
	public List<Carro> pesquisarCarroPorVersaoCarro(VersaoCarro versaoCarro) {
		TypedQuery<Carro> query = entityManager.createQuery("from Carro c where c.versaoModeloCarro.descricaoVersaoModeloCarro = :versaoModeloCarro", Carro.class);
		query.setParameter("versaoModeloCarro", versaoCarro.getDescricao());
		return query.getResultList();
	}

	@Override
	public List<Carro> pesquisarCarroPorItemSerie(String descricao) {
		TypedQuery<Carro> query = entityManager.createQuery("from carro c, ItemSerieCarro i where c.versaoModeloCarro.itemSeriecarros.descricaoItemSerie  = i.descricaoItemSerie", Carro.class);
		query.setParameter("i.descricaoItemSerie", descricao);
		return query.getResultList();
	}

	@Override
	public List<Carro> pesquisarCarroPorAcessorioCarro(
			AcessorioCarro acessorioCarro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Carro> emitirRelatorio() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carro pegarCarroPeloChassi(String chassi) throws Exception {
		TypedQuery<Carro> tqry = entityManager.createQuery("from Carro c where c.chassi = :chassi", Carro.class);
		tqry.setParameter("chassi", chassi);
		try{
			return tqry.getSingleResult();
		}catch(NoResultException re){
			return null;
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
	}

}
