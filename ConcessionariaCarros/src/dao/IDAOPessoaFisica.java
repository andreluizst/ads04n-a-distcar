package dao;

import java.util.List;

import classesBasicas.PessoaFisica;

public interface IDAOPessoaFisica<Entidade> extends IDAOGenerico<Entidade> {
	public List<PessoaFisica> pesquisarPF(PessoaFisica pf);
}
