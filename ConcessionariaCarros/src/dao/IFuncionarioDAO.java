package dao;

import java.util.List;

import classesBasicas.Funcionario;



public interface IFuncionarioDAO extends IDAOGenerico<Funcionario> {

		public List<Funcionario> pesquisarFuncionario(Funcionario f);

}

	
