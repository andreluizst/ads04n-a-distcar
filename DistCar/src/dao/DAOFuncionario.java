package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import classesBasicas.Funcionario;

public class DAOFuncionario extends DAOGenerico<Funcionario> implements
		IDAOFuncionario {

	
	public DAOFuncionario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOFuncionario(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Funcionario> pesquisarFuncionario(Funcionario f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funcionario> consultar(Funcionario funcionario) throws Exception {
		String jpql = "Select f from Funcionario f where f.nome like :nome";
		String nome = "%";
		boolean temCodigo = false;
		//boolean temNome = false;
		boolean temCpf = false;
		boolean temCidade = false;
		boolean temDepto = false;
		boolean temFuncao = false;
		if (funcionario.getCodigo() != null && funcionario.getCodigo() > 0){
			jpql+= " and f.codigo = :codigo";
			temCodigo = true;
		}else{
			if (funcionario.getCpf() != null){
				jpql+= " and funcionario.cpf like :cpf";
				temCpf = true;
			}
			if (funcionario.getDepartamento() != null && funcionario.getDepartamento().getNome() != null){
				jpql+= " and funcionario.departamento.nome like :nomeDepto";
				temDepto = true;
			}
			if (funcionario.getEndereco() != null && funcionario.getEndereco().getCidade() != null
					&& funcionario.getEndereco().getCidade().getNome() != null){
				jpql+= " and funcionario.endereco.cidade.nome like :nomeCidade";
				temCidade = true;
			}
			if (funcionario.getFuncao() != null && funcionario.getFuncao().getDescricao() != null){
				jpql+= " and funcionario.funcao.descricao like :nomeFuncao";
				temFuncao = true;
			}
			if (funcionario.getDepartamento() != null && funcionario.getDepartamento().getNome() != null){
				jpql+= " and funcionario.departamento.nome like :nomeDepto";
				temDepto = true;
			}
		}
		TypedQuery<Funcionario> tqry = entityManager.createQuery(jpql, Funcionario.class);
		tqry.setParameter("nome", nome);
		if (temCodigo)
			tqry.setParameter("codigo", funcionario.getCodigo());
		if (temCidade)
			tqry.setParameter("nomeCidade", "%" + funcionario.getEndereco().getCidade().getNome() + "%");
		if (temFuncao)
			tqry.setParameter("nomeFuncao", "%" + funcionario.getFuncao().getDescricao() + "%");
		if (temDepto)
			tqry.setParameter("nomeDepto", "%" + funcionario.getDepartamento().getNome() + "%");
		if (temCpf)
			tqry.setParameter("cpf", "%" + funcionario.getCpf() + "%");
		return tqry.getResultList();
	}

}
