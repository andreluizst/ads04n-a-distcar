package dao;

import java.util.List;
import classesBasicas.Funcionario;

public abstract class DAOFuncionario<Entidade> extends DAOGenerico <Entidade> implements
		IFuncionarioDAO<Entidade, Funcionario> {

	public DAOFuncionario() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Funcionario> pesquisarFuncionario(Funcionario f) {
		// TODO Auto-generated method stub
		return null;
	}

}
