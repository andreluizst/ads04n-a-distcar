package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import classesBasicas.Fabricante;

public class DAOFabricante<Entidade> extends DAOGenerico<Entidade> implements IDAOFabricante<Entidade>{

	@Override
	public List<Fabricante> pesquisarFabricante(Fabricante f) {
		String jpql = "from Fabricante f where fabricante.pj.nome like :nome";
		String nome = "%";
		if (f.getPj() != null){
			nome = "%" + f.getPj().getNome() + "%";
			if (f.getPj().getCnpj() != null)
				jpql+= " and f.pj.cnpj like :cnpj";
		}
		TypedQuery<Fabricante> tqry = entityManager.createQuery(jpql, Fabricante.class);
		tqry.setParameter("nome", nome);
		if (f.getPj().getCnpj() != null)
			tqry.setParameter("cnpj", f.getPj().getCnpj());
		return tqry.getResultList();
	}

}
