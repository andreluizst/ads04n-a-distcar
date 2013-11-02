package dao;

import java.util.List;

import classesBasicas.Funcionario;



public interface IDAOFuncionario extends IDAOGenerico<Funcionario> {

		public List<Funcionario> pesquisarFuncionario(Funcionario f);

}

	
