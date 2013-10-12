package dao;

import java.util.List;



public interface IFuncionarioDAO<Entidade, Funcionario> extends IDAOGenerico<Entidade> {

		public List<Funcionario> pesquisarFuncionario(Funcionario f);

}

	
