package dao;

import java.util.List;

import classesBasicas.Fabricante;

public interface IDAOFabricante<Entidade> extends IDAOGenerico<Entidade> {
	public List<Fabricante> pesquisarFabricante(Fabricante f);
}
