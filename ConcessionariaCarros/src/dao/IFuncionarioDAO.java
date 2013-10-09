package dao;

import classesBasicas.Funcionario;

public interface IFuncionarioDAO {

	 void inserirFuncionario(Funcionario funcionario);

	Funcionario pesquisarCodigoFuncionario(Integer codigo);

	void removerFuncionario(Integer codigo);

}

	
