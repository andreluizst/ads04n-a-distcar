package dao;

import java.util.List;

import classesBasicas.Cidade;

public interface IDAOCidade extends IDAOGenerico<Cidade> {
	public List<Cidade> pesquisarCidade(Cidade cidade);
}
