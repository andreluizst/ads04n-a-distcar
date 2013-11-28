package negocio;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import util.Parametros;
import classesBasicas.Funcao;
import classesBasicas.Movimentacao;
import classesBasicas.NotaFiscal;
import classesBasicas.Pedido;
import dao.DAOMovimentacao;
import dao.DAONotaFiscal;
import dao.IDAOMovimentacao;
import dao.INotaFiscalDAO;
import dao.IPedidoDAO;
import dao.DAOPedido;
import erro.NegocioExceptionNotaFiscal;
import erro.NegocioExceptionPedido;


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
	
	public void inserirMovimentacao(Movimentacao movimentacao) throws Exception{
		daoMovimentacao.inserirSemTratamento(movimentacao);
	}
	
	public void alterarMovimentacao(Movimentacao movimentacao) throws Exception{
		daoMovimentacao.alterarSemTratamento(movimentacao);
	}
	
	public void excluirMovimentacao(Movimentacao movimentacao) throws Exception{
		daoMovimentacao.removerSemTratamento(movimentacao);
	}
	
	public List<Movimentacao> consultarMovimentacao(Movimentacao movimentacao) throws Exception{
		return daoMovimentacao.consultar(movimentacao);
	}

	public List<Movimentacao> consultarMovimentacao(Movimentacao movimentacao,
			Date dataFinal) throws Exception{
		return daoMovimentacao.consultar(movimentacao, dataFinal);
	}

}