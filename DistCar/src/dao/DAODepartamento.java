package dao;

import java.util.List;

import javax.persistence.EntityManager;

import classesBasicas.Departamento;

public class DAODepartamento extends DAOGenerico<Departamento> implements
		IDAODepartamento {
	
	public DAODepartamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAODepartamento(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Departamento> pesquisarNomeDepartamento(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

}
