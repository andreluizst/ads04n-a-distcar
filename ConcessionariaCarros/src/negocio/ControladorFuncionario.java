package negocio;

import java.util.List;
import classesBasicas.Funcionario;
import dao.FuncionarioDAO;
import dao.IFuncionarioDAO;
import erro.NegocioExceptionFuncionario;

public class ControladorFuncionario {
	private IFuncionarioDAO<Funcionario> funcionarioDAO;
	
	public ControladorFuncionario() {
		// TODO Auto-generated constructor stub
			super();
		
		this.setFuncionarioDAO ( new FuncionarioDAO<Funcionario>());
		}
	
	public void inserirFuncionario (Funcionario funcionario) throws NegocioExceptionFuncionario {
		// TODO Auto-generated method stub
		if(	funcionario.getCodigo()==null||funcionario.getCodigo().equals("")||
				funcionario.getNome()==null||funcionario.getNome().equals("")||
						funcionario.getSituacao()==null||funcionario.getSituacao().equals("")||
								funcionario.getDataUltimaAtualizacao()==null||funcionario.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionFuncionario ("Campos inválidos");
				}
				
		funcionarioDAO.inserir(funcionario);
	}
	
	public void alterarFuncionario(Funcionario funcionario) throws NegocioExceptionFuncionario {
		if(funcionario.getCodigo()==null||funcionario.getCodigo().equals("")||
				funcionario.getNome()==null||funcionario.getNome().equals("")||
						funcionario.getSituacao()==null||funcionario.getSituacao().equals("")||
								funcionario.getDataUltimaAtualizacao()==null||funcionario.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionFuncionario("Campos inválidos");
				}
				
		Funcionario func = funcionarioDAO.consultarPorId(funcionario.getCodigo());
		if(func==null){
			throw new NegocioExceptionFuncionario("Funcionário não cadastrado");
		}
		
		funcionarioDAO.inserir(func);
		}
	
	public void removerFuncionario(Funcionario funcionario) throws NegocioExceptionFuncionario {
		// TODO Auto-generated method stub
		Funcionario func = funcionarioDAO.consultarPorId(funcionario.getCodigo());
		if(func==null){
			throw new NegocioExceptionFuncionario("Funcionario não encontrado!");
		}
		funcionarioDAO.remover(func);
	}

	public List<Funcionario> pesquisarFuncionario(Funcionario funcionario) throws NegocioExceptionFuncionario {
		// TODO Auto-generated method stub
		return null;
	}

	public IFuncionarioDAO<Funcionario> getFuncionarioDAO() {
		return funcionarioDAO;
	}

	public void setFuncionarioDAO(IFuncionarioDAO<Funcionario> funcionarioDAO) {
		this.funcionarioDAO = funcionarioDAO;
	}

	
	}

