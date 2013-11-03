package negocio;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import util.Parametros;
import classesBasicas.Centro;
import classesBasicas.Cidade;
import classesBasicas.Departamento;
import classesBasicas.Funcao;
import classesBasicas.Funcionario;
import classesBasicas.Gestor;
import classesBasicas.Situacao;
import classesBasicas.TipoLogradouro;
import classesBasicas.UnidadeFederativa;
import dao.DAOCentro;
import dao.DAOCidade;
import dao.DAODepartamento;
import dao.DAOFuncao;
import dao.DAOFuncionario;
import dao.DAOGestor;
import dao.DAOPessoaJuridica;
import dao.DAOTipoLogradouro;
import dao.DAOUnidadeFederativa;
import dao.IDAOCentro;
import dao.IDAOCidade;
import dao.IDAODepartamento;
import dao.IDAOFuncao;
import dao.IDAOFuncionario;
import dao.IDAOGestor;
import dao.IDAOPessoaJuridica;
import dao.IDAOTipoLogradouro;
import dao.IDAOUnidadeFederativa;
import erro.NegocioExceptionDepartamento;
import erro.NegocioExceptionFuncao;
import erro.NegocioExceptionFuncionario;
import erro.NegocioExceptionGestor;
import gui.MsgPrimeFaces;

public class ControladorOrganizacional {
	private EntityManagerFactory emf;
	private EntityManager entityManager;
	private IDAOFuncao daoFuncao;
	private IDAOFuncionario daoFuncionario;
	private IDAOCentro daoCentro;
	private IDAOGestor daoGestor;
	private IDAODepartamento daoDepto;
	private IDAOPessoaJuridica daoPJ;
	private IDAOTipoLogradouro daoTipoLogradouro;
	private IDAOCidade daoCidade;
	private IDAOUnidadeFederativa daoUF;
	
	public ControladorOrganizacional(){
		emf = Persistence.createEntityManagerFactory(Parametros.UNIT_PERSISTENCE_NAME);
		entityManager = emf.createEntityManager();
		inicializarDAO();
	}
	
	public ControladorOrganizacional(EntityManagerFactory emf){
		this.emf = emf;
		this.entityManager = emf.createEntityManager();
		inicializarDAO();
	}
	
	private void inicializarDAO(){
		daoFuncao = new DAOFuncao(entityManager);
		daoFuncionario = new DAOFuncionario();
		daoCentro = new DAOCentro(entityManager);
		daoDepto = new DAODepartamento(entityManager);
		daoGestor = new DAOGestor(entityManager);
		daoPJ = new DAOPessoaJuridica(entityManager);
		daoTipoLogradouro = new DAOTipoLogradouro(entityManager);
		daoCidade = new DAOCidade(entityManager);
		daoUF = new DAOUnidadeFederativa(entityManager);
	}
	
	
	//*****************************  F U N � � O  *****************************************
	public boolean funcaoExiste(Funcao funcao) throws Exception{
		Funcao obj = null;
		if (funcao.getCodigo() == null)
			return false;
		obj = daoFuncao.consultarPorId(funcao.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == funcao.getCodigo())
				return true;
		return false;
	}

	public void inserirFuncao (Funcao funcao) throws Exception {
		// TODO Auto-generated method stub
		funcao.setDataUltimaAtualizacao(Calendar.getInstance());
		if (funcao.getSituacao() == null)
			funcao.setSituacao(Situacao.ATIVO);
		
		if(funcao.getDescricao()==null||funcao.getDescricao().equals("")||
						funcao.getSituacao()==null ||
							funcao.getDataUltimaAtualizacao()==null)
				{
					throw new NegocioExceptionFuncao("inserirFuncao: Campos inv�lidos");
				}
		daoFuncao.inserir(funcao);
	}
	
	public void alterarFuncao(Funcao funcao) throws Exception {
		if(	funcao.getCodigo()==null||funcao.getCodigo().equals("")||
				funcao.getDescricao()==null||funcao.getDescricao().equals("")||
						funcao.getSituacao()==null||funcao.getSituacao().equals("")||
							funcao.getDataUltimaAtualizacao()==null||funcao.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionFuncao("Campos inv�lidos");
				}
				
		Funcao f = daoFuncao.consultarPorId(funcao.getCodigo());
		if(f==null){
			throw new NegocioExceptionFuncao("Funcao n�o cadastrada");
		}
		
		daoFuncao.alterar(f);
	}
	
	
	public void removerFuncao(Funcao funcao) throws Exception {
		// TODO Auto-generated method stub
		Funcao f = daoFuncao.consultarPorId(funcao.getCodigo());
		if(f==null){
			throw new NegocioExceptionFuncao("Departamento n�o encontrado!");
		}
		daoFuncao.remover(f);
	}

	public List<Funcao> pesquisarFuncao(Funcao funcao) throws Exception{
		// TODO Auto-generated method stub
		return daoFuncao.pesquisar(funcao);
	}
	
	public List<Funcao> listarFuncoes() throws Exception{
		return daoFuncao.consultarTodos();
	}
	
	
	//*************************  D E P A R T A M E N T O  **************************************
	public void inserirDepartamento(Departamento departamento) throws Exception{
		// TODO Auto-generated method stub
		if(		departamento.getCodigo()==null||departamento.getCodigo().equals("")||
					departamento.getNome()==null||departamento.getNome().equals("")||
						departamento.getSituacao()==null||departamento.getSituacao().equals("")||
							departamento.getDepartamentoSuperior()==null||departamento.getDepartamentoSuperior().equals("")||
								departamento.getGestor()==null||departamento.getGestor().equals("")||
									departamento.getDataUltimaAtualizacao()==null||departamento.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionDepartamento("Campos inv�lidos");
				}
				
		daoDepto.inserir(departamento);
	}
	
	public void alterarDepartamento(Departamento departamento) throws Exception{
		// TODO Auto-generated method stub
		if(	departamento.getCodigo()==null||departamento.getCodigo().equals("")||
				departamento.getNome()==null||departamento.getNome().equals("")||
					departamento.getSituacao()==null||departamento.getSituacao().equals("")||
						departamento.getDepartamentoSuperior()==null||departamento.getDepartamentoSuperior().equals("")||
							departamento.getGestor()==null||departamento.getGestor().equals("")||
								departamento.getDataUltimaAtualizacao()==null||departamento.getDataUltimaAtualizacao().equals(""))
			{
				throw new NegocioExceptionDepartamento("Campos inv�lidos");
			}
							
	
		/*Departamento d = daoDepto.pesquisarNomeDepartamento(departamento.getNome());
		if(d==null){
			throw new NegocioExceptionDepartamento("Departamento n�o cadastrado");
		}
		
		daoDepto.inserir(d);
		*/
	}
	
	public void removerDepartamento(Departamento departamento) throws Exception{
		// TODO Auto-generated method stub
		List<Departamento> lista;
		lista = daoDepto.pesquisarNomeDepartamento(departamento.getNome());
		if(lista == null){
			throw new NegocioExceptionDepartamento("Departamento n�o encontrado!");
		}
		else
			daoDepto.remover(lista.get(0));
	}

	public List<Departamento> pesquisarDepartamento(Departamento departamento) throws NegocioExceptionDepartamento {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//*************************  F U N C I O N � R I O  ******************************************
	public void inserirFuncionario (Funcionario funcionario) throws Exception {
		// TODO Auto-generated method stub
		if(	funcionario.getCodigo()==null||funcionario.getCodigo().equals("")||
				funcionario.getNome()==null||funcionario.getNome().equals("")||
						funcionario.getSituacao()==null||funcionario.getSituacao().equals("")||
								funcionario.getDataUltimaAtualizacao()==null||funcionario.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionFuncionario ("Campos inv�lidos");
				}
				
		daoFuncionario.inserir(funcionario);
	}
	
	public void alterarFuncionario(Funcionario funcionario) throws Exception{
		if(funcionario.getCodigo()==null||funcionario.getCodigo().equals("")||
				funcionario.getNome()==null||funcionario.getNome().equals("")||
						funcionario.getSituacao()==null||funcionario.getSituacao().equals("")||
								funcionario.getDataUltimaAtualizacao()==null||funcionario.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionFuncionario("Campos inv�lidos");
				}
				
		Funcionario func = daoFuncionario.consultarPorId(funcionario.getCodigo());
		if(func==null){
			throw new NegocioExceptionFuncionario("Funcion�rio n�o cadastrado");
		}
		
		daoFuncionario.alterar(func);
		}
	
	public void removerFuncionario(Funcionario funcionario) throws Exception{
		// TODO Auto-generated method stub
		Funcionario func = daoFuncionario.consultarPorId(funcionario.getCodigo());
		if(func==null){
			throw new NegocioExceptionFuncionario("Funcionario n�o encontrado!");
		}
		daoFuncionario.remover(func);
	}

	public List<Funcionario> pesquisarFuncionario(Funcionario funcionario) throws NegocioExceptionFuncionario {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//*********************************  G E S T O R  *******************************************
	public void inserirGestor (Gestor gestor) throws Exception {
		// TODO Auto-generated method stub
		if(	gestor.getCodigo()==null||gestor.getCodigo().equals("")||
				gestor.getNome()==null||gestor.getNome().equals("")||
						gestor.getSituacao()==null||gestor.getSituacao().equals("")||
								gestor.getDataUltimaAtualizacao()==null||gestor.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionGestor ("Campos inv�lidos");
				}
				
		daoGestor.inserir(gestor);
	}
	
	public void alterarGestor (Gestor gestor) throws Exception {
		if(	gestor.getCodigo()==null||gestor.getCodigo().equals("")||
				gestor.getNome()==null||gestor.getNome().equals("")||
						gestor.getSituacao()==null||gestor.getSituacao().equals("")||
								gestor.getDataUltimaAtualizacao()==null||gestor.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionGestor ("Campos inv�lidos");
				}
				
		Gestor g = daoGestor.consultarPorId(gestor.getCodigo());
		if(g==null){
			throw new NegocioExceptionGestor("Gestor n�o cadastrado");
		}
		
		daoGestor.alterar(g);
	}


	public void removerGestor(Gestor gestor) throws Exception {
		// TODO Auto-generated method stub
		Gestor g = daoGestor.consultarPorId(gestor.getCodigo());
		if(g==null){
			throw new NegocioExceptionGestor("Gestor n�o encontrado!");
		}
		daoGestor.remover(g);
	}

	public List<Gestor> pesquisarGestor(Gestor gestor) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}
	
	//*********************************  C E N T R O  *********************************************
	public boolean centroExiste(Centro centro) throws Exception{
		Centro obj = null;
		if (centro.getCodigo() == null)
			return false;
		obj = daoCentro.consultarPorId(centro.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == centro.getCodigo())
				return true;
		return false;
	}
	
	public void inserirCentro(Centro centro) throws Exception{
		Cidade cid;
		TipoLogradouro tpLog;
		
		centro.setDataUltimaAtualizacao(Calendar.getInstance());
		if (centro.getSituacao() == null)
			centro.setSituacao(Situacao.ATIVO);
		
		EntityTransaction et = entityManager.getTransaction();
		try{
			et.begin();
			if (centro.getDadosPJ() != null){
				if (centro.getDadosPJ().getEndereco().getCidade() != null){
					cid = daoCidade.consultarPorId(centro.getDadosPJ().getEndereco().getCidade().getCodigo());
					centro.getDadosPJ().getEndereco().setCidade(cid);
					tpLog = daoTipoLogradouro.consultarPorId(centro.getDadosPJ().getEndereco().getTipoLogradouro().getCodigo());
					centro.getDadosPJ().getEndereco().setTipoLogradouro(tpLog);
				}
				String cnpj = centro.getDadosPJ().getCnpj().replace(".", "");
				//System.out.println(cnpj);
				cnpj = cnpj.replace("-", "").replace("/", "");
				//System.out.println(cnpj);
				centro.getDadosPJ().setCnpj(cnpj);
			}
			daoCentro.inserirSemTratamento(centro);
			et.commit();
		}catch(Exception ex){
			et.rollback();
			throw ex;
		}
	}
	
	public void alterarCentro(Centro centro) throws Exception{
		String cnpj = centro.getDadosPJ().getCnpj().replace(".", "");
		cnpj = cnpj.replace("-", "").replace("/", "");
		centro.getDadosPJ().setCnpj(cnpj);
		centro.setDataUltimaAtualizacao(Calendar.getInstance());
		if (centro.getSituacao() == null)
			centro.setSituacao(Situacao.ATIVO);
		EntityTransaction et = entityManager.getTransaction();
		try{
			et.begin();
			daoCentro.alterarSemTratamento(centro);
			et.commit();
		}catch(Exception ex){
			et.rollback();
			throw ex;
		}
	}
	
	public void removerCentro(Centro centro) throws Exception{
		EntityTransaction et = entityManager.getTransaction();
		try{
			et.begin();
			daoPJ.removerSemTratamento(centro.getDadosPJ());
			daoCentro.removerSemTratamento(centro);
			et.commit();
		}catch(Exception ex){
			et.rollback();
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public List<Centro> consultarCentro(Centro centro) throws Exception{
		return daoCentro.pesquisar(centro);
	}
	
	public List<Centro> listarCentros() throws Exception{
		return daoCentro.consultarTodos();
	}
	
	//************************* T I P O S   L O G R A D O U R O S  *********************
	public boolean tipoLogradouroExiste(TipoLogradouro tipoLogradouro) throws Exception{
		TipoLogradouro obj = null;
		if (tipoLogradouro.getCodigo() == null)
			return false;
		obj = daoTipoLogradouro.consultarPorId(tipoLogradouro.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == tipoLogradouro.getCodigo())
				return true;
		return false;
	}
	
	public void inserirTipoLogradouro(TipoLogradouro tipoLogradouro) throws Exception{
		daoTipoLogradouro.inserir(tipoLogradouro);
	}
	
	public void alterarTipoLogradouro(TipoLogradouro tipoLogradouro) throws Exception{
		daoTipoLogradouro.alterar(tipoLogradouro);
	}
	
	public List<TipoLogradouro> listarTiposLogradouros() throws Exception{
		return daoTipoLogradouro.consultarTodos();
	}
	
	//****************************  C I D A D E  *****************************************
	public boolean cidadeExiste(Cidade cidade) throws Exception{
		Cidade obj = null;
		if (cidade.getCodigo() == null)
			return false;
		obj = daoCidade.consultarPorId(cidade.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == cidade.getCodigo())
				return true;
		return false;
	}
	
	public void inserirCidade(Cidade cidade) throws Exception{
		List<Cidade> lista = daoCidade.pesquisar(cidade);
		if (lista.size() > 0)
			throw new Exception("A cidade " + cidade.getNome()
					+ "/" +cidade.getUnidadeFederativa().getSigla() + ", j� existe!");
		daoCidade.inserir(cidade);
	}
	
	public void alterarCidade(Cidade cidade) throws Exception{
		daoCidade.alterar(cidade);
	}
	
	public List<Cidade> listarCidades() throws Exception{
		return daoCidade.consultarTodos();
	}
	
	//*******************  U N I D A D E   F E D E R A T I V A  ****************************
	public List<UnidadeFederativa> listarUFs() throws Exception{
		return daoUF.consultarTodos();
	}
}
