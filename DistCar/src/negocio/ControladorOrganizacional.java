package negocio;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import classesBasicas.Departamento;
import classesBasicas.Funcao;
import classesBasicas.Funcionario;
import classesBasicas.Gestor;
import classesBasicas.Situacao;
import dao.DAOCentro;
import dao.DAODepartamento;
import dao.DAOFuncao;
import dao.DAOFuncionario;
import dao.DAOGestor;
import dao.DAOPessoaJuridica;
import dao.IDAOCentro;
import dao.IDAODepartamento;
import dao.IDAOFuncao;
import dao.IDAOFuncionario;
import dao.IDAOGestor;
import dao.IDAOPessoaJuridica;
import erro.NegocioExceptionDepartamento;
import erro.NegocioExceptionFuncao;
import erro.NegocioExceptionFuncionario;
import erro.NegocioExceptionGestor;

public class ControladorOrganizacional {
	private EntityManagerFactory emf;
	private EntityManager entityManager;
	private IDAOFuncao daoFuncao;
	private IDAOFuncionario daoFuncionario;
	private IDAOCentro daoCentro;
	private IDAOGestor daoGestor;
	private IDAODepartamento daoDepto;
	private IDAOPessoaJuridica daoPJ;
	
	public ControladorOrganizacional(){
		inicializarDAO();
	}
	
	public ControladorOrganizacional(EntityManagerFactory emf){
		this.emf = emf;
		this.entityManager = emf.createEntityManager();
		inicializarDAO();
	}
	
	private void inicializarDAO(){
		daoFuncao = new DAOFuncao();
		daoFuncionario = new DAOFuncionario();
		daoCentro = new DAOCentro();
		daoDepto = new DAODepartamento();
		daoGestor = new DAOGestor();
		daoPJ = new DAOPessoaJuridica();
	}
	
	
	//*****************************  F U N � � O  *****************************************
	public boolean funcaoExiste(Funcao funcao){
		Funcao obj = null;
		if (funcao.getCodigo() == null)
			return false;
		obj = daoFuncao.consultarPorId(funcao.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == funcao.getCodigo())
				return true;
		return false;
	}

	public void inserirFuncao (Funcao funcao) throws NegocioExceptionFuncao {
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
	
	public void alterarFuncao(Funcao funcao) throws NegocioExceptionFuncao {
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
	
	
	public void removerFuncao(Funcao funcao) throws NegocioExceptionFuncao {
		// TODO Auto-generated method stub
		Funcao f = daoFuncao.consultarPorId(funcao.getCodigo());
		if(f==null){
			throw new NegocioExceptionFuncao("Departamento n�o encontrado!");
		}
		daoFuncao.remover(f);
	}

	public List<Funcao> pesquisarFuncao(Funcao funcao) throws NegocioExceptionFuncao {
		// TODO Auto-generated method stub
		return daoFuncao.pesquisar(funcao);
	}
	
	public List<Funcao> listarFuncoes() throws NegocioExceptionFuncao{
		return daoFuncao.consultarTodos();
	}
	
	
	//*************************  D E P A R T A M E N T O  **************************************
	public void inserirDepartamento(Departamento departamento) throws NegocioExceptionDepartamento {
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
	
	public void alterarDepartamento(Departamento departamento) throws NegocioExceptionDepartamento {
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
	
	public void removerDepartamento(Departamento departamento) throws NegocioExceptionDepartamento {
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
	public void inserirFuncionario (Funcionario funcionario) throws NegocioExceptionFuncionario {
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
	
	public void alterarFuncionario(Funcionario funcionario) throws NegocioExceptionFuncionario {
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
		
		daoFuncionario.inserir(func);
		}
	
	public void removerFuncionario(Funcionario funcionario) throws NegocioExceptionFuncionario {
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
	public void inserirGestor (Gestor gestor) throws NegocioExceptionGestor {
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
	
	public void alterarGestor (Gestor gestor) throws NegocioExceptionGestor {
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
		
		daoGestor.inserir(g);
	}


	public void removerGestor(Gestor gestor) throws NegocioExceptionGestor {
		// TODO Auto-generated method stub
		Gestor g = daoGestor.consultarPorId(gestor.getCodigo());
		if(g==null){
			throw new NegocioExceptionGestor("Gestor n�o encontrado!");
		}
		daoGestor.remover(g);
	}

	public List<Gestor> pesquisarGestor(Gestor gestor) throws NegocioExceptionGestor {
		// TODO Auto-generated method stub
		return null;
	}
	
}
