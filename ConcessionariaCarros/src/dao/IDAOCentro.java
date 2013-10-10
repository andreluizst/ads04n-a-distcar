package dao;

import java.util.List;

import classesBasicas.Centro;

public interface IDAOCentro<Entidade> extends IDAOGenerico<Entidade> {
	public List<Centro> pesquisarCentro(Centro centro);
}
