package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoModeloCarro;


public class CarroDAO extends DAOGenerico<Carro> implements ICarroDAO {

	@Override
	public List<Carro> pesquisarCarroPorChassi(String chassi) {
		TypedQuery<Carro> query = entityManager.createQuery("from Carro c where c.chassi like :chassi", Carro.class);
		query.setParameter("chassi", "%" + chassi + "%");
		return query.getResultList();
	}

	@Override
	public List<Carro> pesquisarCarroPorModelo(ModeloCarro modeloCarro) {
		TypedQuery<Carro> query = entityManager.createQuery("from Carro c where c.versaoModeloCarro.modeloCarro.descricaoModeloCarro = :modeloCarro", Carro.class);
		query.setParameter("modeloCarro", modeloCarro.getDescricaoModeloCarro());
		return query.getResultList();
	}

	@Override
	public List<Carro> pesquisarCarroPorVersaoCarro(VersaoModeloCarro versaoModeloCarro) {
		TypedQuery<Carro> query = entityManager.createQuery("from Carro c where c.versaoModeloCarro.descricaoVersaoModeloCarro = :versaoModeloCarro", Carro.class);
		query.setParameter("versaoModeloCarro", versaoModeloCarro.getDescricaoVersaoModeloCarro());
		return query.getResultList();
	}

	@Override
	public List<Carro> pesquisarCarroPorItemSerie(ItemSerieCarro itemSerieCarro) {
		TypedQuery<Carro> query = entityManager.createQuery("from ItemSerieCarro i where i.descricao  = :itemSerieCarro", Carro.class);
		query.setParameter("itemSerieCarro", itemSerieCarro.getDescricao());
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

}
