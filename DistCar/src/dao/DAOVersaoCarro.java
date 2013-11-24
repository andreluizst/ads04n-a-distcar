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

	@Override
	public List<VersaoCarro> consultar(VersaoCarro versao) throws Exception {
		String jpql = "from VersaoCarro v where v.descricao like :descricao";
		String descricao = "%";
		boolean temValor = false;
		boolean temModelo = false;
		boolean temSituacao = false;
		boolean temFabricante = false;
		boolean temMarca = false;
		if (versao.getDescricao() != null && versao.getDescricao().length() > 0)
			descricao = "%" + versao.getDescricao() + "%";
		if (versao.getModeloCarro()!=null && versao.getModeloCarro().getCodigo()!= null && versao.getModeloCarro().getCodigo()>0){
			jpql+= " and v.modeloCarro.codigo = :modelo";
			temModelo = true;
		}
		if (versao.getModeloCarro()!=null && versao.getModeloCarro().getMarcaCarro()!=null && versao.getModeloCarro().getMarcaCarro().getCodigo()!= null && 
				versao.getModeloCarro().getMarcaCarro().getCodigo()>0){
			jpql+= " and v.modeloCarro.marcaCarro.codigo = :marca";
			temMarca = true;
		}
		if (versao.getModeloCarro()!=null && versao.getModeloCarro().getMarcaCarro()!=null&&
				versao.getModeloCarro().getMarcaCarro().getFabricante()!=null && versao.getModeloCarro().getMarcaCarro().getFabricante().getCodigo()!= null && 
				versao.getModeloCarro().getMarcaCarro().getFabricante().getCodigo()>0){
			jpql+= " and v.modeloCarro.marcaCarro.fabricante.codigo = :fabricante";
			temFabricante = true;
		}
		if (versao.getSituacao() != null){
			jpql+= " and v.situacao = :situacao";
			temSituacao = true;
		}
		if (versao.getValor()>0){
			jpql+= " and v.valor = :valor";
			temValor = true;
		}
		TypedQuery<VersaoCarro> tqry = entityManager.createQuery(jpql, VersaoCarro.class);
		tqry.setParameter("descricao", descricao);
		if(temValor)
			tqry.setParameter("valor", versao.getValor());
		if (temModelo)
			tqry.setParameter("modelo", versao.getModeloCarro().getCodigo());
		if (temMarca )
			tqry.setParameter("marca", versao.getModeloCarro().getMarcaCarro().getCodigo());
		if (temFabricante)
			tqry.setParameter("fabricante", versao.getModeloCarro().getMarcaCarro().getFabricante().getCodigo());
		if (temSituacao)
			tqry.setParameter("situacao", versao.getSituacao());
		return tqry.getResultList();
	}

}
