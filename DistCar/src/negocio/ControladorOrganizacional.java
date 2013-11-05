package negocio;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import util.Parametros;
import classesBasicas.Centro;
import classesBasicas.Cidade;
import classesBasicas.Departamento;
import classesBasicas.Escolaridade;
import classesBasicas.Funcao;
import classesBasicas.Funcionario;
import classesBasicas.Gestor;
import classesBasicas.PessoaJuridica;
import classesBasicas.Situacao;
import classesBasicas.TipoGerencia;
import classesBasicas.TipoLogradouro;
import classesBasicas.UnidadeFederativa;
import dao.DAOCentro;
import dao.DAOCidade;
import dao.DAODepartamento;
import dao.DAOEscolaridade;
import dao.DAOFuncao;
import dao.DAOFuncionario;
import dao.DAOGestor;
import dao.DAOPessoaJuridica;
import dao.DAOTipoGerencia;
import dao.DAOTipoLogradouro;
import dao.DAOUnidadeFederativa;
import dao.IDAOCentro;
import dao.IDAOCidade;
import dao.IDAODepartamento;
import dao.IDAOEscolaridade;
import dao.IDAOFuncao;
import dao.IDAOFuncionario;
import dao.IDAOGestor;
import dao.IDAOPessoaJuridica;
import dao.IDAOTipoGerencia;
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
	private IDAOTipoGerencia daoTipoGerencia;
	private IDAODepartamento daoDepto;
	private IDAOPessoaJuridica daoPJ;
	private IDAOTipoLogradouro daoTipoLogradouro;
	private IDAOCidade daoCidade;
	private IDAOUnidadeFederativa daoUF;
	private IDAOEscolaridade daoEscolaridade;
	
	public ControladorOrganizacional(){
		emf = Parametros.EMF_Default;
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
		daoFuncionario = new DAOFuncionario(entityManager);
		daoEscolaridade = new DAOEscolaridade(entityManager);
		daoCentro = new DAOCentro(entityManager);
		daoDepto = new DAODepartamento(entityManager);
		daoGestor = new DAOGestor(entityManager);
		daoPJ = new DAOPessoaJuridica(entityManager);
		daoTipoLogradouro = new DAOTipoLogradouro(entityManager);
		daoCidade = new DAOCidade(entityManager);
		daoUF = new DAOUnidadeFederativa(entityManager);
		daoTipoGerencia = new DAOTipoGerencia(entityManager);
	}
	
	
	//*****************************  F U N Ç Ã O  *****************************************
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
					throw new Exception("inserirFuncao: Campos inválidos");
				}
		daoFuncao.inserir(funcao);
	}
	
	public void alterarFuncao(Funcao funcao) throws Exception {
		funcao.setDataUltimaAtualizacao(Calendar.getInstance());
		if (funcao.getSituacao() == null)
			funcao.setSituacao(Situacao.ATIVO);
		if(	funcao.getCodigo()==null||funcao.getCodigo().equals("")||
				funcao.getDescricao()==null||funcao.getDescricao().equals("")||
						funcao.getSituacao()==null||funcao.getSituacao().equals("")||
							funcao.getDataUltimaAtualizacao()==null||funcao.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionFuncao("Campos inválidos");
				}
				
		Funcao f = daoFuncao.consultarPorId(funcao.getCodigo());
		if(f==null){
			throw new Exception("Funcao não cadastrada");
		}
		
		daoFuncao.alterar(f);
	}
	
	
	public void removerFuncao(Funcao funcao) throws Exception {
		// TODO Auto-generated method stub
		Funcao f = daoFuncao.consultarPorId(funcao.getCodigo());
		if(f==null){
			throw new NegocioExceptionFuncao("Departamento não encontrado!");
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
	public boolean departamentoExiste(Departamento obj) throws Exception{
		Departamento outroObj = null;
		if (obj.getCodigo() == null)
			return false;
		outroObj = daoDepto.consultarPorId(obj.getCodigo());
		if (outroObj != null)
			if (outroObj.getCodigo() == obj.getCodigo())
				return true;
		return false;
	}
	
	private void sincronizarChavesEstrangeirasDoDepto(Departamento departamento) throws Exception{
		Departamento deptoSup;
		Gestor gestor;
		
		System.out.println("Chamando: daoCentro.consultarPorId(departamento.getCentro().getCodigo())....");
		Centro centro = daoCentro.consultarPorId(departamento.getCentro().getCodigo());
		UnidadeFederativa uf = daoUF.consultarPorId(centro.getDadosPJ().getEndereco().getCidade().getUnidadeFederativa().getCodigo());
		centro.getDadosPJ().getEndereco().getCidade().setUnidadeFederativa(uf);
		System.out.println("prox linha: departamento.setCentro(centro);");
		System.out.println("Centro: " + centro + " - " + centro.getDadosPJ().getEndereco() 
				+ " UF.nome = " + centro.getDadosPJ().getEndereco().getCidade().getUnidadeFederativa().getNome());
		departamento.setCentro(centro);
		System.out.println("prox linha: verificando gestor...");
		if (departamento.getGestor() != null){
			if ((departamento.getGestor().getCodigo()!=null) && (departamento.getGestor().getCodigo() > 0)){
				gestor = daoGestor.consultarPorId(departamento.getGestor().getCodigo());
				departamento.setGestor(gestor);
				System.out.println("---- Gestor: " + gestor.toString());
			}
		}
		System.out.println("---- Gestor verificado!!!!");
		System.out.println("prox linha: verificando depto superior...");
		if (departamento.getDepartamentoSuperior() != null){
			if ((departamento.getDepartamentoSuperior().getCodigo()!=null) && (departamento.getDepartamentoSuperior().getCodigo() > 0)){
				deptoSup = daoDepto.consultarPorId(departamento.getDepartamentoSuperior().getCodigo());
				departamento.setDepartamentoSuperior(deptoSup);
			}
		}
		System.out.println("---- Departamento superior verificado!!!");
	}
	
	public void inserirDepartamento(Departamento departamento) throws Exception{
		EntityManager em = emf.createEntityManager();
        Departamento deptoSup;
        Gestor gestor;
       
        departamento.setDataUltimaAtualizacao(Calendar.getInstance());
        if (departamento.getSituacao() == null)
                departamento.setSituacao(Situacao.ATIVO);
        if(departamento.getNome()==null||departamento.getNome().equals(""))
                        {
                                throw new NegocioExceptionDepartamento("Inserir depto: Campos inválidos");
                        }
        System.out.println("iniciando transação de insersão de departamento...........");
        System.out.println("iniciando transação de insersão de departamento...........");
        System.out.println("iniciando transação de insersão de departamento...........");
       
        daoDepto.setEntityManager(em);
        daoCentro.setEntityManager(em);
        daoGestor.setEntityManager(em);
        EntityTransaction et = em.getTransaction();
        try{
                et.begin();
                Centro centro = daoCentro.consultarPorId(departamento.getCentro().getCodigo());
                departamento.setCentro(centro);
                if (departamento.getGestor() != null){
                        if (departamento.getGestor().getCodigo() > 0){
                                gestor = daoGestor.consultarPorId(departamento.getGestor().getCodigo());
                                departamento.setGestor(gestor);
                        }
                }
                if (departamento.getDepartamentoSuperior() != null){
                        if (departamento.getDepartamentoSuperior().getCodigo() > 0){
                                deptoSup = daoDepto.consultarPorId(departamento.getDepartamentoSuperior().getCodigo());
                                departamento.setDepartamentoSuperior(deptoSup);
                        }
                }
                daoDepto.inserirSemTratamento(departamento);
                et.commit();
        }catch(Exception ex){
                et.rollback();
                throw new Exception(ex.getMessage());
        }
        em.close();
        daoCentro.setEntityManager(entityManager);
        daoDepto.setEntityManager(entityManager);
        daoGestor.setEntityManager(entityManager);

	}
	
	public void alterarDepartamento(Departamento departamento) throws Exception{
		EntityManager em = emf.createEntityManager();
		
		departamento.setDataUltimaAtualizacao(Calendar.getInstance());
		if (departamento.getSituacao() == null)
			departamento.setSituacao(Situacao.ATIVO);
		
		if(	departamento.getCodigo()==null||departamento.getCodigo().equals("")||
				departamento.getNome()==null||departamento.getNome().equals(""))
			{
				throw new NegocioExceptionDepartamento("Alterar depto: Campos inválidos");
		}
							
		daoDepto.setEntityManager(em);
		daoCentro.setEntityManager(em);
		daoGestor.setEntityManager(em);
		EntityTransaction et = em.getTransaction();
		try{
			et.begin();
			System.out.println("Iniciando sincronizarChavesEstrangeirasDoDepto(departamento)....");
			//sincronizarChavesEstrangeirasDoDepto(departamento);
			
			Departamento deptoSup;
			Gestor gestor;
			System.out.println("Chamando: daoCentro.consultarPorId(departamento.getCentro().getCodigo())....");
			Centro centro = daoCentro.consultarPorId(departamento.getCentro().getCodigo());
			System.out.println("prox linha: departamento.setCentro(centro);");
			departamento.setCentro(centro);
			System.out.println("prox linha: verificando gestor...");
			if (departamento.getGestor() != null){
				if ((departamento.getGestor().getCodigo()!=null) && (departamento.getGestor().getCodigo() > 0)){
					System.out.println("##### gestor = " + departamento.getGestor());
					gestor = daoGestor.consultarPorId(departamento.getGestor().getCodigo());
					departamento.setGestor(gestor);
				}
			}
			System.out.println("prox linha: verificando depto superior...");
			if (departamento.getDepartamentoSuperior() != null){
				if ((departamento.getDepartamentoSuperior().getCodigo()!=null) && (departamento.getDepartamentoSuperior().getCodigo() > 0)){
					deptoSup = daoDepto.consultarPorId(departamento.getDepartamentoSuperior().getCodigo());
					departamento.setDepartamentoSuperior(deptoSup);
				}
			}
			
			///*********************
			System.out.println("Finalizando sincronizarChavesEstrangeirasDoDepto(departamento)....");
			System.out.println("Alterando departamento....");
			daoDepto.alterarSemTratamento(departamento);
			et.commit();
			System.out.println("Departamento ALTERADO.");
		}catch(Exception ex){
			et.rollback();
			throw new Exception("Aletrar depto: " + ex.getMessage());
		}
		em.close();
		daoCentro.setEntityManager(entityManager);
		daoDepto.setEntityManager(entityManager);
		daoGestor.setEntityManager(entityManager);
	}
	
	public void removerDepartamento(Departamento departamento) throws Exception{
		List<Departamento> lista;
		lista = daoDepto.pesquisarNomeDepartamento(departamento.getNome());
		if(lista == null){
			throw new NegocioExceptionDepartamento("Departamento não encontrado!");
		}
		else{
			Funcionario fun = new Funcionario();
			fun.setDepartamento(departamento);
			
			List<Funcionario> funcionarios = pesquisarFuncionario(fun);
			if (funcionarios.size() > 0)
				throw new Exception("Não é possível excluir o departamento selecionado porque existe um ou mais funcionarios nesse departamento!");
			EntityTransaction et = entityManager.getTransaction();
			try{
				et.begin();
				daoDepto.removerSemTratamento(lista.get(0));
				et.commit();
			}catch(Exception ex){
				et.rollback();
				throw new Exception(ex.getMessage());
			}
		}
	}

	public List<Departamento> pesquisarDepartamento(Departamento departamento) throws NegocioExceptionDepartamento {
		return daoDepto.pesquisar(departamento);
	}
	
	public List<Departamento> listarDepartamentos(){
		return daoDepto.consultarTodos();
	}
	
	
	//*************************  F U N C I O N Á R I O  ******************************************
	public boolean funcionarioExiste(Funcionario obj) throws Exception{
		Funcionario outroObj = null;
		if (obj.getCodigo() == null)
			return false;
		outroObj = daoFuncionario.consultarPorId(obj.getCodigo());
		if (outroObj != null)
			if (outroObj.getCodigo() == obj.getCodigo())
				return true;
		return false;
	}
	
	private void sincronizarChavesEstrangeirasDoFuncionario(Funcionario funcionario) throws Exception{
		Departamento depto;
		Escolaridade escolaridade;
		Funcao funcao;
		//Atributo obtido num comboBox na view da web. Só contém o código da entidade
		if (funcionario.getDepartamento() != null){
			if (funcionario.getDepartamento().getCodigo() > 0){
				depto = daoDepto.consultarPorId(funcionario.getDepartamento().getCodigo());
				funcionario.setDepartamento(depto);
			}
		}
		//Atributo obtido num comboBox na view da web. Só contém o código da entidade
		if (funcionario.getEscolaridade() != null){
			if (funcionario.getEscolaridade().getCodigo() > 0){
				escolaridade = daoEscolaridade.consultarPorId(funcionario.getEscolaridade().getCodigo());
				funcionario.setEscolaridade(escolaridade);
			}
		}
		//Atributo obtido num comboBox na view da web. Só contém o código da entidade
		if (funcionario.getFuncao() != null){
			if (funcionario.getFuncao().getCodigo() > 0){
				funcao = daoFuncao.consultarPorId(funcionario.getFuncao().getCodigo());
				funcionario.setFuncao(funcao);
			}
		}
	}
	
	public void inserirFuncionario (Funcionario funcionario) throws Exception {
		EntityManager em = emf.createEntityManager();
		
		
		funcionario.setDataUltimaAtualizacao(Calendar.getInstance());
		if (funcionario.getSituacao() == null){
			funcionario.setSituacao(Situacao.ATIVO);
		}
		if(	funcionario.getNome()==null||funcionario.getNome().equals(""))
				{
					throw new NegocioExceptionFuncionario ("Campos inválidos");
				}
		daoDepto.setEntityManager(em);
		//daoGestor.setEntityManager(em);
		daoEscolaridade.setEntityManager(em);
		daoFuncao.setEntityManager(em);
		daoFuncionario.setEntityManager(entityManager);
		EntityTransaction et = em.getTransaction();
		try{
			et.begin();
			sincronizarChavesEstrangeirasDoFuncionario(funcionario);
			daoFuncionario.inserirSemTratamento(funcionario);
			et.commit();
		}catch(Exception ex){
			et.rollback();
			throw ex;
		}
		em.close();
		daoFuncionario.setEntityManager(entityManager);
		daoEscolaridade.setEntityManager(entityManager);
		daoDepto.setEntityManager(entityManager);
		daoFuncao.setEntityManager(entityManager);
		//daoGestor.setEntityManager(entityManager);
	}
	
	public void alterarFuncionario(Funcionario funcionario) throws Exception{
		EntityManager em = emf.createEntityManager();
		
		funcionario.setDataUltimaAtualizacao(Calendar.getInstance());
		if (funcionario.getSituacao() == null){
			funcionario.setSituacao(Situacao.ATIVO);
		}
		if(funcionario.getCodigo()==null||funcionario.getCodigo().equals("")||
				funcionario.getNome()==null||funcionario.getNome().equals("")||
						funcionario.getSituacao()==null||funcionario.getSituacao().equals("")||
								funcionario.getDataUltimaAtualizacao()==null||
								funcionario.getDataUltimaAtualizacao().equals("")){
			throw new NegocioExceptionFuncionario("Campos inválidos");
		}
				
		Funcionario func = daoFuncionario.consultarPorId(funcionario.getCodigo());
		if(func==null){
			throw new NegocioExceptionFuncionario("Funcionário não cadastrado");
		}
		daoDepto.setEntityManager(em);
		//daoGestor.setEntityManager(em);
		daoEscolaridade.setEntityManager(em);
		daoFuncao.setEntityManager(em);
		daoFuncionario.setEntityManager(entityManager);
		EntityTransaction et = em.getTransaction();
		try{
			et.begin();
			sincronizarChavesEstrangeirasDoFuncionario(funcionario);
			daoFuncionario.alterarSemTratamento(funcionario);
			et.commit();
		}catch(Exception ex){
			et.rollback();
			throw ex;
		}
		em.close();
		daoFuncionario.setEntityManager(entityManager);
		daoEscolaridade.setEntityManager(entityManager);
		daoDepto.setEntityManager(entityManager);
		daoFuncao.setEntityManager(entityManager);
		//daoGestor.setEntityManager(entityManager);
	}
	
	public void removerFuncionario(Funcionario funcionario) throws Exception{
		// TODO Auto-generated method stub
		Funcionario func = daoFuncionario.consultarPorId(funcionario.getCodigo());
		if(func==null){
			throw new NegocioExceptionFuncionario("Funcionario não encontrado!");
		}
		daoFuncionario.remover(func);
	}

	public List<Funcionario> pesquisarFuncionario(Funcionario funcionario) throws NegocioExceptionFuncionario {
		return daoFuncionario.pesquisar(funcionario);
	}
	
	
	//*********************************  G E S T O R  *******************************************
	public boolean gestorExiste(Gestor obj) throws Exception{
		Gestor outroObj = null;
		if (obj.getCodigo() == null)
			return false;
		outroObj = daoGestor.consultarPorId(obj.getCodigo());
		if (outroObj != null)
			if (outroObj.getCodigo() == obj.getCodigo())
				return true;
		return false;
	}
	
	public void inserirGestor (Gestor gestor) throws Exception {
		// TODO Auto-generated method stub
		if(	gestor.getCodigo()==null||gestor.getCodigo().equals("")||
				gestor.getNome()==null||gestor.getNome().equals("")||
						gestor.getSituacao()==null||gestor.getSituacao().equals("")||
								gestor.getDataUltimaAtualizacao()==null||gestor.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionGestor ("Campos inválidos");
				}
				
		daoGestor.inserir(gestor);
	}
	
	public void alterarGestor (Gestor gestor) throws Exception {
		if(	gestor.getCodigo()==null||gestor.getCodigo().equals("")||
				gestor.getNome()==null||gestor.getNome().equals("")||
						gestor.getSituacao()==null||gestor.getSituacao().equals("")||
								gestor.getDataUltimaAtualizacao()==null||gestor.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionGestor ("Campos inválidos");
				}
				
		Gestor g = daoGestor.consultarPorId(gestor.getCodigo());
		if(g==null){
			throw new NegocioExceptionGestor("Gestor não cadastrado");
		}
		
		daoGestor.alterar(g);
	}


	public void removerGestor(Gestor gestor) throws Exception {
		// TODO Auto-generated method stub
		Gestor g = daoGestor.consultarPorId(gestor.getCodigo());
		if(g==null){
			throw new NegocioExceptionGestor("Gestor não encontrado!");
		}
		daoGestor.remover(g);
	}

	public List<Gestor> pesquisarGestor(Gestor gestor) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Gestor> listarGestores(){
		return daoGestor.consultarTodos();
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
		/*if (entityManager.isOpen())
			entityManager.close();
		entityManager = emf.createEntityManager();*/
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
				cnpj = cnpj.replace("-", "").replace("/", "");
				centro.getDadosPJ().setCnpj(cnpj);
				String cep = centro.getDadosPJ().getEndereco().getCep().replace("-", "");
				centro.getDadosPJ().getEndereco().setCep(cep);
				centro.getDadosPJ().setDataUltimaAtualizacao(centro.getDataUltimaAtualizacao());
				centro.getDadosPJ().setSituacao(centro.getSituacao());
			}
			daoCentro.inserirSemTratamento(centro);
			et.commit();
		}catch(Exception ex){
			et.rollback();
			throw new Exception(ex.getMessage());
		}
	}
	
	public void alterarCentro(Centro centro) throws Exception{
		/*if (entityManager.isOpen())
			entityManager.close();
		entityManager = emf.createEntityManager();*/
		
		System.out.println("Iniciando alterarCentro(Centro centro)..........");
		System.out.println("Iniciando alterarCentro(Centro centro)..........");
		System.out.println("Iniciando alterarCentro(Centro centro)..........");
		System.out.println("Iniciando alterarCentro(Centro centro)..........");
		
		
		String cnpj = centro.getDadosPJ().getCnpj();
		cnpj = cnpj.replace(".", "").replace("-", "").replace("/", "");
		String cep = centro.getDadosPJ().getEndereco().getCep();
		cep = cep.replace("-", "");
		
		PessoaJuridica pj = daoPJ.pegarPJ(cnpj);
		
		System.out.println("PJ do banco: " + pj);
		System.out.println("centro.getDadosPJ = " + centro.getDadosPJ());
		
		//EntityManager em = emf.createEntityManager();
		
		EntityTransaction et = entityManager.getTransaction();
		try{
			et.begin();
			centro.getDadosPJ().getEndereco().setCep(cep);
			centro.getDadosPJ().setCnpj(cnpj);
			centro.setDataUltimaAtualizacao(Calendar.getInstance());
			if (centro.getSituacao() == null)
				centro.setSituacao(Situacao.ATIVO);
			
			if (!centro.getDadosPJ().equals(pj)){
				System.out.println("Dados de PJ foram modificados!!");
				centro.getDadosPJ().setDataUltimaAtualizacao(centro.getDataUltimaAtualizacao());
				centro.getDadosPJ().setSituacao(centro.getSituacao());
			}
			daoCentro.alterarSemTratamento(centro);
			et.commit();
		}catch(Exception ex){
			et.rollback();
			throw new Exception(ex.getMessage());
		}
	}
	
	public void removerCentro(Centro centro) throws Exception{
		EntityTransaction et = entityManager.getTransaction();
		try{
			et.begin();
			daoCentro.removerSemTratamento(centro);
			daoPJ.removerSemTratamento(centro.getDadosPJ());
			
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
					+ "/" +cidade.getUnidadeFederativa().getSigla() + ", já existe!");
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
	
	//************************  T I P O   G E R E N C I A  **********************************
	public boolean tipoGerenciaExiste(TipoGerencia obj) throws Exception{
		TipoGerencia outroObj = null;
		if (obj.getCodigo() == null)
			return false;
		outroObj = daoTipoGerencia.consultarPorId(obj.getCodigo());
		if (outroObj != null)
			if (outroObj.getCodigo() == obj.getCodigo())
				return true;
		return false;
	}
	
	public void inserirTipoGerencia(TipoGerencia tipoGerencia) throws Exception{
		daoTipoGerencia.inserir(tipoGerencia);
	}
	
	public void alterarTipoGerencia(TipoGerencia tipoGerencia) throws Exception{
		daoTipoGerencia.alterar(tipoGerencia);
	}
	
	public void removerTipoGerencia(TipoGerencia tipoGerencia) throws Exception{
		daoTipoGerencia.remover(tipoGerencia);
	}
	
	public List<TipoGerencia> consultarTipoGerencia(TipoGerencia tipoGerencia) throws Exception{
		return daoTipoGerencia.pesquisar(tipoGerencia);
	}
	
	public List<TipoGerencia> listarTiposGerencia() throws Exception{
		return daoTipoGerencia.consultarTodos();
	}
}
