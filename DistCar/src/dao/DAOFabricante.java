package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import classesBasicas.Fabricante;

public class DAOFabricante extends DAOGenerico<Fabricante> implements IDAOFabricante{
	
	public DAOFabricante() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOFabricante(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

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
