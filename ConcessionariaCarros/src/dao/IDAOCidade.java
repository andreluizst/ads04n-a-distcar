package dao;

import java.util.List;

import classesBasicas.Cidade;

public interface IDAOCidade<Entidade> extends IDAOGenerico<Entidade> {
	public List<Cidade> pesquisarCidade(Cidade cidade);
}
