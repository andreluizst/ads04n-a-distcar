package negocio;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import util.Parametros;
import classesBasicas.Movimentacao;
import classesBasicas.MovimentacaoItem;
import classesBasicas.SituacaoMovimentacao;
import classesBasicas.Status;
import classesBasicas.TipoMovimentacao;
import dao.DAOMovimentacao;
import dao.DAOMovimentacaoItem;
import dao.IDAOMovimentacao;
import dao.IDAOMovimentacaoItem;


public class ControladorMovimentacao {
	private EntityManagerFactory emf;
	private EntityManager entityManager;
	private IDAOMovimentacao daoMovimentacao;
	//private ControladorCarro ctrlCarro;

	
	public ControladorMovimentacao() {
		emf = Parametros.EMF_Default;
		entityManager = emf.createEntityManager();
		inicializarDAO();
		//ctrlCarro = new ControladorCarro();
	}
	
	public ControladorMovimentacao(EntityManagerFactory emf){
		this.emf = emf;
		this.entityManager = emf.createEntityManager();
		inicializarDAO();
		//ctrlCarro = new ControladorCarro();
	}
	
	private void inicializarDAO(){
		daoMovimentacao = new DAOMovimentacao();
	}
	
	
	public boolean movimentacaoExiste(Movimentacao movimentacao) throws Exception{
		Movimentacao obj = null;
		if (movimentacao.getNumero() == null)
			return false;
		obj = daoMovimentacao.consultarPorId(movimentacao.getNumero());
		if (obj != null)
			if (obj.getNumero() == movimentacao.getNumero())
				return true;
		return false;
	}
	
	private void atualizaStatusDoItemDaMovimentacao(Movimentacao movimentacao){
		for (MovimentacaoItem item : movimentacao.getItens()){
			if (movimentacao.getSituacao() == SituacaoMovimentacao.PENDENTE){
				if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.COMPRA){
					item.getMovimentoCarroPK().getCarro().setStatus(Status.ESTOQUE);
					item.getMovimentoCarroPK().getCarro().setCentro(movimentacao.getCtoOrigem());
				}else{
					if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.VENDA){
						item.getMovimentoCarroPK().getCarro().setStatus(Status.VENDIDO);
						item.getMovimentoCarroPK().getCarro().setCentro(movimentacao.getCtoOrigem());
					}else{
						if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.ENTRE_CENTROS){
							item.getMovimentoCarroPK().getCarro().setStatus(Status.TRANSFERENCIA);
							item.getMovimentoCarroPK().getCarro().setCentro(movimentacao.getCtoDestino());
						}
					}
				}
			}else{
				if (movimentacao.getSituacao() == SituacaoMovimentacao.CONCLUIDA){
					if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.ENTRE_CENTROS){
						item.getMovimentoCarroPK().getCarro().setStatus(Status.ESTOQUE);
						item.getMovimentoCarroPK().getCarro().setCentro(movimentacao.getCtoDestino());
					}
				}
			}
		}
	}
	
	public void inserirMovimentacao(Movimentacao movimentacao) throws Exception{
		movimentacao.setDataMovimentacao(Calendar.getInstance().getTime());
		movimentacao.setDataUltimaAtualizacao(Calendar.getInstance().getTime());
		if (movimentacao.getSituacao() == null)
			movimentacao.setSituacao(SituacaoMovimentacao.PENDENTE);
		atualizaStatusDoItemDaMovimentacao(movimentacao);
		daoMovimentacao.inserirSemTratamento(movimentacao);
		IDAOMovimentacaoItem daoItem = new DAOMovimentacaoItem();
		daoItem.atualizarItensDaMovimentacao(movimentacao);
	}
	
	public void alterarMovimentacao(Movimentacao movimentacao) throws Exception{
		movimentacao.setDataUltimaAtualizacao(Calendar.getInstance().getTime());
		if (movimentacao.getSituacao() == null)
			movimentacao.setSituacao(SituacaoMovimentacao.PENDENTE);
		atualizaStatusDoItemDaMovimentacao(movimentacao);
		daoMovimentacao.alterarSemTratamento(movimentacao);
		IDAOMovimentacaoItem daoItem = new DAOMovimentacaoItem();
		daoItem.atualizarItensDaMovimentacao(movimentacao);
	}
	
	public void excluirMovimentacao(Movimentacao movimentacao) throws Exception{
		IDAOMovimentacaoItem daoItem = new DAOMovimentacaoItem();
		daoItem.removerItensDaMovimentacaoNumero(movimentacao.getNumero());
		daoMovimentacao.removerSemTratamento(movimentacao);
	}
	
	public List<Movimentacao> consultarMovimentacao(Movimentacao movimentacao) throws Exception{
		return daoMovimentacao.consultar(movimentacao);
	}

	public List<Movimentacao> consultarMovimentacao(Movimentacao movimentacao,
			Date dataFinal) throws Exception{
		return daoMovimentacao.consultar(movimentacao, dataFinal);
	}
	
	public Movimentacao pegarMovimentacaoPeloNumero(Integer numero) throws Exception{
		return daoMovimentacao.consultarPorId(numero);
	}
	
	public List<MovimentacaoItem> listarItensDaMovimentacaoNumero(Integer numero) throws Exception{
		IDAOMovimentacaoItem daoItem = new DAOMovimentacaoItem();
		return daoItem.listarItensDaMovimentacaoNumero(numero);
	}

}