package dao;

import javax.persistence.EntityManager;
import classesBasicas.Cliente;


public class DAOCliente extends DAOGenerico<Cliente> implements IDAOCliente {

	public DAOCliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOCliente(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
}
