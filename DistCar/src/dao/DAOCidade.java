package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import classesBasicas.Cidade;

public class DAOCidade extends DAOGenerico<Cidade> implements IDAOCidade{

	@Override
	public List<Cidade> pesquisarCidade(Cidade cidade) {
		String jpql = "from Cidade c wehere c.nome like :nome";
		String nome = "";
		if (cidade.getNome() != null)
			nome = cidade.getNome();
		if (cidade.getUnidadeFederativa() != null)
			jpql+= " and c.unidadeFederativa.sigla like :sigla";
		TypedQuery<Cidade> tqry = entityManager.createQuery(jpql, Cidade.class);
		tqry.setParameter("nome", "%" + nome + "%");
		if (cidade.getUnidadeFederativa() != null)
			tqry.setParameter("nome", cidade.getUnidadeFederativa().getSigla());
		return tqry.getResultList();
	}

}
