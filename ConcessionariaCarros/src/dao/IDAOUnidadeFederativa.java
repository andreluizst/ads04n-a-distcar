package dao;

import java.util.List;

import classesBasicas.UnidadeFederativa;

public interface IDAOUnidadeFederativa extends IDAOGenerico<UnidadeFederativa> {
	public List<UnidadeFederativa> pesquisarUF(UnidadeFederativa uf);
}
