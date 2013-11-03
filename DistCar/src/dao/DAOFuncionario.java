package dao;

import java.util.List;

import javax.persistence.EntityManager;

import classesBasicas.Funcionario;

public class DAOFuncionario extends DAOGenerico<Funcionario> implements
		IDAOFuncionario {

	
	public DAOFuncionario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOFuncionario(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Funcionario> pesquisarFuncionario(Funcionario f) {
		// TODO Auto-generated method stub
		return null;
	}

}
