package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
import classesBasicas.Fabricante;
import classesBasicas.MarcaCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoCarro;


public class DAOCarro extends DAOGenerico<Carro> implements IDAOCarro {

	public DAOCarro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOCarro(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

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
	
	public List<Carro> consultar(Carro carro,Fabricante f, MarcaCarro m, ModeloCarro modelo) throws Exception {
		String jpql = "from Carro c where c.chassi like :chassi";
		String chassi = "%";
		boolean temValor = false;
		boolean temModelo = false;
		boolean temSituacao = false;
		boolean temFabricante = false;
		boolean temMarca = false;
		boolean temVersao= false;
		boolean temF=false;
		boolean temM=false;
		boolean temMod=false;
		if (carro.getChassi() != null && carro.getChassi().length() > 0)
			chassi = "%" + carro.getChassi() + "%";
		
		if(f!=null){
			jpql+= " and c.versao.modeloCarro.marcaCarro.fabricante.codigo = :fabricante";
			temF = true;
		}
		if(m!=null){
			jpql+= " and c.versao.modeloCarro.marcaCarro.codigo = :marca";
			temM = true;
		}
		if(modelo!=null){
			jpql+= " and c.versao.modeloCarro.codigo = :modelo";
			temMod = true;
		}
		if (carro.getVersao().getModeloCarro().getMarcaCarro().getFabricante()!=null && carro.getVersao().getCodigo()!= null && 
				carro.getVersao().getCodigo()>0){
			jpql+= " and c.versao.codigo = :versao";
			temVersao = true;
		}
		if (carro.getVersao().getModeloCarro().getMarcaCarro()!=null && carro.getVersao().getModeloCarro().getMarcaCarro().getCodigo()!= null && 
				carro.getVersao().getModeloCarro().getMarcaCarro().getCodigo()>0){
			jpql+= " and c.versao.modeloCarro.marcaCarro.codigo = :marca";
			temMarca = true;
			temM=false;
		}
		
		if (carro.getVersao().getModeloCarro()!=null && carro.getVersao().getModeloCarro().getCodigo()!= null && carro.getVersao().getModeloCarro().getCodigo()>0){
			jpql+= " and c.versao.modeloCarro.codigo = :modelo";
			temModelo = true;
			temMod=false;
		}
		
		if (carro.getSituacao() != null){
			jpql+= " and c.situacao = :situacao";
			temSituacao = true;
		}
		if (carro.getValorCarro()>0){
			jpql+= " and c.valor = :valor";
			temValor = true;
		}
		TypedQuery<Carro> tqry = getEntityManager().createQuery(jpql, Carro.class);
		tqry.setParameter("chassi", chassi);
		if(temValor)
			tqry.setParameter("valor", carro.getValorCarro());
		if (temModelo)
			tqry.setParameter("modelo", carro.getVersao().getModeloCarro().getCodigo());
		if (temMarca )
			tqry.setParameter("marca", carro.getVersao().getModeloCarro().getMarcaCarro().getCodigo());
		if (temFabricante)
			tqry.setParameter("fabricante", carro.getVersao().getModeloCarro().getMarcaCarro().getFabricante().getCodigo());
		if (temSituacao)
			tqry.setParameter("situacao", carro.getVersao().getSituacao());
		if(temF){
			tqry.setParameter("fabricante", f.getCodigo());
		}
		if(temMod){
			tqry.setParameter("modelo", modelo.getCodigo());
		}
		if(temM){
			tqry.setParameter("marca",m.getCodigo());
		}
		if(temVersao){
			tqry.setParameter("versao",carro.getVersao());
		}
		return tqry.getResultList();
	}


}
