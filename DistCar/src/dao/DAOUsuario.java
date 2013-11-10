package dao;

import javax.persistence.EntityManager;

import seguranca.Usuario;

public class DAOUsuario extends DAOGenerico<Usuario> implements IDAOUsuario {

	public DAOUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOUsuario(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
}
