package dao;

import java.util.List;

import classesBasicas.PessoaJuridica;

public interface IDAOPessoaJuridica<Entidade> extends IDAOGenerico<Entidade> {
	public List<PessoaJuridica> pesquisarPJ(PessoaJuridica pj);
}
