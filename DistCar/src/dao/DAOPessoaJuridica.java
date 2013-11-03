package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import classesBasicas.PessoaJuridica;

public class DAOPessoaJuridica extends DAOGenerico<PessoaJuridica> implements IDAOPessoaJuridica {

	@Override
	public List<PessoaJuridica> pesquisarPJ(PessoaJuridica pj) {
		String jpql = "from PessoaFisica p where p.nome like :nome";
		String nome = "%";
		if (pj.getNome() != null)
			nome = "%" + pj.getNome() + "%";
		if (pj.getCnpj() != null)
			jpql+= " and p.cnpj like :cnpj";
		if (pj.getInscricaoEstadual() != null)
			jpql+= " and p.inscricaoEstadual like :inscEst";
		if (pj.getEndereco().getCidade() != null){
			jpql+= " and p.endereco.cidade.codigo = :cidade";
			if (pj.getEndereco().getCidade().getUnidadeFederativa().getSigla() != null)
				jpql+= " and p.endereco.cidade.unidadeFederativa.sigla like :uf";
		}
		TypedQuery<PessoaJuridica> tqry = entityManager.createQuery(jpql, PessoaJuridica.class);
		tqry.setParameter("nome", nome);
		if (pj.getCnpj() != null)
			tqry.setParameter("cnpj", pj.getCnpj());
		if (pj.getInscricaoEstadual() != null)
			tqry.setParameter("inscEst", pj.getInscricaoEstadual());
		if (pj.getEndereco().getCidade() != null){
			tqry.setParameter("cidade", pj.getEndereco().getCidade().getCodigo());
			if (pj.getEndereco().getCidade().getUnidadeFederativa().getSigla() != null)
				tqry.setParameter("sigla", pj.getEndereco().getCidade().getUnidadeFederativa().getSigla());
		}
		return tqry.getResultList();
	}

}
