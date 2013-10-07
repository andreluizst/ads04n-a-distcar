package negocio;

import classesBasicas.Funcao;
import classesBasicas.Funcionario;
import dao.FuncionarioDAO;
import dao.IFuncionarioDAO;
import erro.NegocioExceptionFuncao;


public class ControladorFuncionario {
private IFuncionarioDAO funcaoDAO;

	public ControladorFuncionario() {
		// TODO Auto-generated constructor stub
super();
		
		this.setFuncionarioDAO( new FuncionarioDAO());
	}
	
		

	public IFuncionarioDAO getFuncaoDAO() {
		return funcaoDAO;
	}

	public void setFuncaoDAO(IFuncionarioDAO funcaoDAO) {
		this.funcaoDAO = funcaoDAO;
	}

}
