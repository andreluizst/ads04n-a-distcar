package negocio;

import java.util.List;
import classesBasicas.Funcionario;
import dao.DAOFuncionario;
import dao.IFuncionarioDAO;
import erro.NegocioExceptionFuncionario;

public class ControladorFuncionario {
	private IFuncionarioDAO funcionarioDAO;
	
	public ControladorFuncionario() {
		// TODO Auto-generated constructor stub
			super();
		this.funcionarioDAO = new DAOFuncionario();
		//this.setFuncionarioDAO ( new DAOFuncionario());
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

	public IFuncionarioDAO getFuncionarioDAO() {
		return funcionarioDAO;
	}

	public void setFuncionarioDAO(IFuncionarioDAO funcionarioDAO) {
		this.funcionarioDAO = funcionarioDAO;
	}

	
	}

