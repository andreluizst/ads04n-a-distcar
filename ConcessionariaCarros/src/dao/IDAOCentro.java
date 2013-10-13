package dao;

import java.util.List;

import classesBasicas.Centro;

public interface IDAOCentro extends IDAOGenerico<Centro> {
	public List<Centro> pesquisarCentro(Centro centro);
}
