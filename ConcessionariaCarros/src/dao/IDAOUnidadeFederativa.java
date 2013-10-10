package dao;

import java.util.List;

import classesBasicas.UnidadeFederativa;

public interface IDAOUnidadeFederativa<Entidade> extends IDAOGenerico<Entidade> {
	public List<UnidadeFederativa> pesquisarUF(UnidadeFederativa uf);
}
